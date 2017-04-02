/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.hal.DIOJNI;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.PWMJNI;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

import com.diozero.api.PwmOutputDevice;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Class implements the PWM generation in the FPGA.
 *
 * <p>
 * The values supplied as arguments for PWM outputs range from -1.0 to 1.0. They
 * are mapped to the hardware dependent values, in this case 0-2000 for the
 * FPGA. Changes are immediately sent to the FPGA, and the update occurs at the
 * next FPGA cycle. There is no delay.
 *
 * <p>
 * As of revision 0.1.10 of the FPGA, the FPGA interprets the 0-2000 values as
 * follows: - 2000 = maximum pulse width - 1999 to 1001 = linear scaling from
 * "full forward" to "center" - 1000 = center value - 999 to 2 = linear scaling
 * from "center" to "full reverse" - 1 = minimum pulse width (currently .5ms) -
 * 0 = disabled (i.e. PWM output is held low)
 */
public class PWM extends SensorBase implements LiveWindowSendable {
	/**
	 * Represents the amount to multiply the minimum servo-pulse pwm period by.
	 */
	public enum PeriodMultiplier {
		/**
		 * Period Multiplier: don't skip pulses.
		 */
		k1X,
		/**
		 * Period Multiplier: skip every other pulse.
		 */
		k2X,
		/**
		 * Period Multiplier: skip three out of four pulses.
		 */
		k4X
	}
	
	public enum StoppingMode {
		BREAK, COAST
	}
	
	private static final int RIGHT = 0xFFFF;
	
	/**
	 * One of two hardware-based PWMs using GPIO pins 12 and 13
	 */
	public static final int PWM0 = packInt(12, 13);
	/**
	 * One of two hardware-based PWMs using GPIO pins 12 and 13
	 */
	public static final int PWM1 = packInt(18, 19);
	
	public static final int DEFAULT_DEADBAND = 10;
	
	private int channel;
	
	private PwmOutputDevice forward;
	private PwmOutputDevice backward;
	
	private java.util.Timer deadbandTimer;
	private int deadband;
	
	private StoppingMode stoppingMode;
	
	/**
	 * Allocate a PWM given a channel.
	 *
	 * @param channel
	 *            One integer containing two shorts for the GPIO pins used.
	 *            Note PWM.PWM0 and PWM.PWM1 for more efficient pins that
	 *            allow use of the hardware
	 */
	public PWM(final int channel) {
		this.channel = channel;
		int forw = leftShort(channel);
		int back = rightShort(channel);
		
		checkPWMChannel(forw);
		checkPWMChannel(back);
		
		forward = new PwmOutputDevice(forw);
		backward = new PwmOutputDevice(back);
		
		deadbandTimer = new java.util.Timer();
		deadband = DEFAULT_DEADBAND;
		
		stoppingMode = StoppingMode.COAST;
		
		setDisabled();
		
		HAL.report(tResourceType.kResourceType_PWM, channel);
	}
	
	/**
	 * Free the PWM channel.
	 *
	 * <p>
	 * Free the resource associated with the PWM channel and set the value to 0.
	 */
	public void free() {
		setDisabled();
		forward.close();
		backward.close();
	}
	
	/**
	 * Optionally eliminate the deadband from a speed controller.
	 *
	 * @param eliminateDeadband
	 *            If true, set the motor curve on the Jaguar to eliminate the
	 *            deadband in the middle of the range. Otherwise, keep the full
	 *            range without modifying any values.
	 */
	public void enableDeadbandElimination(boolean eliminateDeadband) {
		deadband = eliminateDeadband ? 0 : DEFAULT_DEADBAND;
	}
	
	/**
	 * Set the bounds on the PWM values. This sets the bounds on the PWM values
	 * for a particular each type of controller. The values determine the upper
	 * and lower speeds as well as the deadband bracket.
	 *
	 * @param max
	 *            The Minimum pwm value
	 * @param deadbandMax
	 *            The high end of the deadband range
	 * @param center
	 *            The center speed (off)
	 * @param deadbandMin
	 *            The low end of the deadband range
	 * @param min
	 *            The minimum pwm value
	 * @deprecated Recommended to set bounds in ms using
	 *             {@link #setBounds(double, double, double, double, double)}
	 */
	@Deprecated
	public void setRawBounds(final int max, final int deadbandMax,
			final int center, final int deadbandMin, final int min) {
		throw new UnsupportedOperationException("Only implemented for RoboRIO");
	}
	
	/**
	 * Set the bounds on the PWM pulse widths. This sets the bounds on the PWM
	 * values for a particular type of controller. The values determine the
	 * upper and lower speeds as well as the deadband bracket.
	 *
	 * @param max
	 *            The max PWM pulse width in ms
	 * @param deadbandMax
	 *            The high end of the deadband range pulse width in ms
	 * @param center
	 *            The center (off) pulse width in ms
	 * @param deadbandMin
	 *            The low end of the deadband pulse width in ms
	 * @param min
	 *            The minimum pulse width in ms
	 */
	@Deprecated
	public void setBounds(double max, double deadbandMax, double center,
			double deadbandMin, double min) {
		throw new UnsupportedOperationException("Only implemented for RoboRIO");
	}
	
	/**
	 * Gets the bounds on the PWM pulse widths. This Gets the bounds on the PWM
	 * values for a particular type of controller. The values determine the
	 * upper and lower speeds as well as the deadband bracket.
	 */
	@Deprecated
	public PWMConfigDataResult getRawBounds() {
		throw new UnsupportedOperationException("Only implemented for RoboRIO");
	}
	
	/**
	 * Set the PWM value based on a position.
	 *
	 * <p>
	 * This is intended to be used by servos.
	 *
	 * @param pos
	 *            The position to set the servo between 0.0 and 1.0.
	 * @pre SetMaxPositivePwm() called.
	 * @pre SetMinNegativePwm() called.
	 */
	@Deprecated
	public void setPosition(double pos) {
		throw new UnsupportedOperationException(
				"Servos are represented seperately from pwms on Raspberry Pi");
	}
	
	/**
	 * Get the PWM value in terms of a position.
	 *
	 * <p>
	 * This is intended to be used by servos.
	 *
	 * @return The position the servo is set to between 0.0 and 1.0.
	 * @pre SetMaxPositivePwm() called.
	 * @pre SetMinNegativePwm() called.
	 */
	public double getPosition() {
		throw new UnsupportedOperationException(
				"Servos are represented seperately from pwms on Raspberry Pi");
	}
	
	/**
	 * Set the PWM value based on a speed.
	 *
	 * <p>
	 * This is intended to be used by speed controllers.
	 *
	 * @param speed
	 *            The speed to set the speed controller between -1.0 and 1.0.
	 * @pre SetMaxPositivePwm() called.
	 * @pre SetMinPositivePwm() called.
	 * @pre SetCenterPwm() called.
	 * @pre SetMaxNegativePwm() called.
	 * @pre SetMinNegativePwm() called.
	 */
	public void setSpeed(double speed) {
		forward.setValue(0);
		backward.setValue(0);
		deadbandTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(speed == 0) {
					switch(stoppingMode){
						case COAST:
							return;
						case BREAK:
							forward.setValue(1);
							backward.setValue(1);
							return;
					}
				} else if(speed > 0) {
					forward.setValue((float) speed);
				} else if(speed < 0) {
					backward.setValue((float) speed);
				}
			}
			
		}, deadband);
	}
	
	/**
	 * Get the PWM value in terms of speed.
	 *
	 * <p>
	 * This is intended to be used by speed controllers.
	 *
	 * @return The most recently set speed between -1.0 and 1.0.
	 * @pre SetMaxPositivePwm() called.
	 * @pre SetMinPositivePwm() called.
	 * @pre SetMaxNegativePwm() called.
	 * @pre SetMinNegativePwm() called.
	 */
	public double getSpeed() {
		if(forward.getValue() != 0 && backward.getValue() != 0){
			return 0;
		}
		return forward.getValue() + backward.getValue();
	}
	
	/**
	 * Set the PWM value directly to the hardware.
	 *
	 * <p>
	 * Write a raw value to a PWM channel.
	 *
	 * @param value
	 *            Raw PWM value. Range 0 - 255.
	 */
	@Deprecated
	public void setRaw(int value) {
		throw new UnsupportedOperationException(
				"GPIO pins do not have analog output");
	}
	
	/**
	 * Get the PWM value directly from the hardware.
	 *
	 * <p>
	 * Read a raw value from a PWM channel.
	 *
	 * @return Raw PWM control value. Range: 0 - 255.
	 */
	public int getRaw() {
		throw new UnsupportedOperationException(
				"GPIO pins do not have raw read");
	}
	
	/**
	 * Temporarily disables the PWM output. The next set call will reenable the
	 * output.
	 */
	public void setDisabled() {
		forward.off();
		backward.off();
	}
	
	/**
	 * Slow down the PWM signal for old devices.
	 *
	 * @param mult
	 *            The period multiplier to apply to this channel
	 */
	public void setPeriodMultiplier(PeriodMultiplier mult) {
		throw new UnsupportedOperationException("Only one speed implemented");
	}
	
	protected void setZeroLatch() {
		throw new UnsupportedOperationException("No zero latch");
	}
	
	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	@Override
	public String getSmartDashboardType() {
		return "Speed Controller";
	}
	
	private ITable m_table;
	private ITableListener m_tableListener;
	
	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}
	
	@Override
	public void updateTable() {
		if(m_table != null) {
			m_table.putNumber("Value", getSpeed());
		}
	}
	
	@Override
	public ITable getTable() {
		return m_table;
	}
	
	@Override
	public void startLiveWindowMode() {
		setSpeed(0); // Stop for safety
		m_tableListener = new ITableListener() {
			public void valueChanged(ITable itable, String key, Object value,
					boolean bln) {
				setSpeed((Double) value);
			}
		};
		m_table.addTableListener("Value", m_tableListener, true);
	}
	
	@Override
	public void stopLiveWindowMode() {
		setSpeed(0); // Stop for safety
		// TODO: Broken, should only remove the listener from "Value" only.
		m_table.removeTableListener(m_tableListener);
	}
	
	/**
	 * @return the deadband
	 */
	public int getDeadband() {
		return deadband;
	}
	
	/**
	 * @return the stoppingMode
	 */
	public StoppingMode getStoppingMode() {
		return stoppingMode;
	}
	
	/**
	 * @param deadband
	 *            the deadband to set
	 */
	public void setDeadband(int deadband) {
		this.deadband = deadband;
	}
	
	/**
	 * @param stoppingMode
	 *            the stoppingMode to set
	 */
	public void setStoppingMode(StoppingMode stoppingMode) {
		this.stoppingMode = stoppingMode;
	}
	
	public int getChannel(){
		return channel;
	}
	
	public static int packInt(int left, int right){
		return (left << 16) | (right & RIGHT);
	}
	
	public static int leftShort(int source){
		return source >> 16;
	}
	
	public static int rightShort(int source){
		return (short) (source & RIGHT);
	}
}
