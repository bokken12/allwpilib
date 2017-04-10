/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.diozero.api.AnalogInputDevice;
import com.diozero.api.AnalogInputEvent;
import com.diozero.api.InputEventListener;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Analog channel class.
 *
 * <p>
 * Each analog channel is read from hardware as a 12-bit number representing 0V
 * to 5V.
 *
 * <p>
 * Connected to each analog channel is an averaging and oversampling engine.
 * This engine accumulates the specified ( by setAverageBits() and
 * setOversampleBits() ) number of samples before returning a new value. This is
 * not a sliding window average. The only difference between the oversampled
 * samples and the averaged samples is that the oversampled samples are simply
 * accumulated effectively increasing the resolution, while the averaged samples
 * are divided by the number of samples to retain the resolution, but get more
 * stable values.
 */
public class AnalogInput extends SensorBase implements PIDSource,
		LiveWindowSendable {
	
	private static final int kAccumulatorSlot = 1;
	private int m_gpio;
	private static final int[] kAccumulatorChannels = { 0, 1 };
	private long m_accumulatorOffset;
	protected PIDSourceType m_pidSource = PIDSourceType.kDisplacement;
	private AnalogInputDevice m_device;
	private double average;
	private int times;
	
	/**
	 * Construct an analog channel with default scale
	 * 
	 * @param channel
	 *            The gpio number to represent.
	 */
	public AnalogInput(final int gpio) {
		this(gpio, 1);
	}
	
	/**
	 * Construct an analog channel.
	 *
	 * @param channel
	 *            The gpio number to represent.
	 */
	public AnalogInput(final int gpio, int scale) {
		m_gpio = gpio;
		average = 0;
		times = 0;
		
		SensorBase.checkGPIOChannel(gpio);
		
		m_device = new AnalogInputDevice(gpio, scale);
		m_device.addListener(new InputEventListener<AnalogInputEvent>() {
			
			@Override
			public void valueChanged(AnalogInputEvent e) {
				average = (average * times + e.getScaledValue()) / (times + 1);
				times++;
			}
			
		});
		
		LiveWindow.addSensor("AnalogInput", gpio, this);
		HAL.report(tResourceType.kResourceType_AnalogChannel, gpio);
	}
	
	/**
	 * Channel destructor.
	 */
	public void free() {
		m_device.close();
		m_gpio = 0;
		m_accumulatorOffset = 0;
	}
	
	/**
	 * Gets a scaled value representing the analog input
	 *
	 * @return A sample straight from this channel.
	 */
	public double getValue() {
		return m_device.getScaledValue();
	}
	
	/**
	 * Gets the average scaled value of the analog input
	 *
	 * @return A sample from the oversample and average engine for this channel.
	 */
	public double getAverageValue() {
		return average;
	}
	
	/**
	 * Get a scaled sample straight from this channel. The value is scaled to
	 * units of Volts using the calibrated scaling data from getLSBWeight() and
	 * getOffset().
	 *
	 * @return A scaled sample straight from this channel.
	 */
	@Deprecated
	public double getVoltage() {
		return getValue();
	}
	
	/**
	 * Get a scaled sample from the output of the oversample and average engine
	 * for this channel. The value is scaled to units of Volts using the
	 * calibrated scaling data from getLSBWeight() and getOffset(). Using
	 * oversampling will cause this value to be higher resolution, but it will
	 * update more slowly. Using averaging will cause this value to be more
	 * stable, but it will update more slowly.
	 *
	 * @return A scaled sample from the output of the oversample and average
	 *         engine for this channel.
	 */
	@Deprecated
	public double getAverageVoltage() {
		return getAverageValue();
	}
	
	/**
	 * Get the channel number.
	 *
	 * @return The channel number.
	 */
	public int getChannel() {
		return m_gpio;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		m_pidSource = pidSource;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return m_pidSource;
	}
	
	/**
	 * Get the average voltage for use with PIDController.
	 *
	 * @return the average voltage
	 */
	@Override
	public double pidGet() {
		return getValue();
	}
	
	/**
	 * Live Window code, only does anything if live window is activated.
	 */
	@Override
	public String getSmartDashboardType() {
		return "Analog Input";
	}
	
	private ITable m_table;
	
	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}
	
	@Override
	public void updateTable() {
		if(m_table != null) {
			m_table.putNumber("Value", getAverageVoltage());
		}
	}
	
	@Override
	public ITable getTable() {
		return m_table;
	}
	
	/**
	 * Analog Channels don't have to do anything special when entering the
	 * LiveWindow. {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode() {
	}
	
	/**
	 * Analog Channels don't have to do anything special when exiting the
	 * LiveWindow. {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode() {
	}
}
