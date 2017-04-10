/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import com.diozero.api.DigitalOutputDevice;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * Class to write digital outputs. This class will write digital outputs. Other devices that are
 * implemented elsewhere will automatically allocate digital inputs and outputs as required.
 */
public class DigitalOutput extends DigitalSource implements LiveWindowSendable {

  private int m_channel = 0;
  private DigitalOutputDevice m_device;

  /**
   * Create an instance of a digital output. Create an instance of a digital output given a
   * channel.
   *
   * @param channel the GPIO channel to use for the digital output.
   */
  public DigitalOutput(int channel) {
    checkGPIOChannel(channel);
    m_channel = channel;

    m_device = new DigitalOutputDevice(channel);

    HAL.report(tResourceType.kResourceType_DigitalOutput, channel);
  }

  /**
   * Free the resources associated with a digital output.
   */
  @Override
  public void free() {
    // disable the pwm only if we have allocated it
    m_device.close();
  }

  /**
   * Set the value of a digital output.
   *
   * @param value true is on, off is false
   */
  public void set(boolean value) {
		m_device.setOn(value);
  }

  /**
   * Gets the value being output from the Digital Output.
   *
   * @return the state of the digital output.
   */
  public boolean get() {
    return m_device.isOn();
  }

  /**
   * @return The GPIO channel number that this object represents.
   */
  @Override
  public int getChannel() {
    return m_channel;
  }

  /**
   * Generate a single pulse. There can only be a single pulse going at any time.
   *
   * @param pulseLength The length of the pulse.
   */
  public void pulse(final double pulseLength) {
	  m_device.onOffLoop((float) pulseLength, (float) pulseLength, 1, true, null);
  }

  /**
   * Generate a single pulse. Write a pulse to the specified digital output channel. There can only
   * be a single pulse going at any time.
   *
   * @param channel     Unused
   * @param pulseLength The length of the pulse.
   * @deprecated Use {@link #pulse(double)} instead.
   */
  @Deprecated
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void pulse(final int channel, final double pulseLength) {
	  if(m_channel != channel){
		  throw new IllegalArgumentException("Cannot pulse a different digital output");
	  }
	  pulse(pulseLength);
  }

  /**
   * Determine if the pulse is still going. Determine if a previously started pulse is still going.
   *
   * @return true if pulsing
   */
  public boolean isPulsing() {
    return false;
  }

  /**
   * Is this an analog trigger.
   *
   * @return true if this is an analog trigger
   */
  @Override
  public boolean isAnalogTrigger() {
    return false;
  }

  /*
   * Live Window code, only does anything if live window is activated.
   */
  @Override
  public String getSmartDashboardType() {
    return "Digital Output";
  }

  private ITable m_table;
  private ITableListener m_tableListener;


  @Override
  public void initTable(ITable subtable) {
    m_table = subtable;
    updateTable();
  }


  @Override
  public ITable getTable() {
    return m_table;
  }


  @Override
  public void updateTable() {
    // TODO: Put current value.
  }


  @Override
  public void startLiveWindowMode() {
    m_tableListener = new ITableListener() {
      @Override
      public void valueChanged(ITable itable, String key, Object value, boolean bln) {
        set((Boolean) value);
      }
    };
    m_table.addTableListener("Value", m_tableListener, true);
  }


  @Override
  public void stopLiveWindowMode() {
    // TODO: Broken, should only remove the listener from "Value" only.
    m_table.removeTableListener(m_tableListener);
  }
}
