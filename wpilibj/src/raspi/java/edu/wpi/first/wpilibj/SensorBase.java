/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

/**
 * Base class for all sensors. Stores most recent status information as well as
 * containing utility functions for checking channels and error processing.
 */
public abstract class SensorBase {
	/**
	 * Ticks per microsecond.
	 */
	public static final int kSystemClockTicksPerMicrosecond = 1000;
	/**
	 * Number of general-purpose input/output channels per Raspberry Pi
	 */
	public static final int kGPIOChannels = 28;
	/**
	 * Number of 3v3 power channels per Raspberry Pi
	 */
	public static final int k3v3Channels = 2;
	/**
	 * Number of 5v power channels per Raspberry Pi
	 */
	public static final int k5vChannels = 2;
	/**
	 * Number of grounding channels per Raspberry Pi
	 */
	public static final int gkGroundChannels = 8;
	/**
	 * Number of PWM channels per roboRIO.
	 */
	public static final int kPwmChannels = 2;
	
	/**
	 * Creates an instance of the sensor base and gets an FPGA handle.
	 */
	public SensorBase() {
	}
	
	/**
	 * Check that the GPIO channel number is valid. Verify that the channel
	 * number is one of the legal channel numbers. Channel numbers are 0-based.
	 *
	 * @param channel
	 *            The channel number to check.
	 */
	protected static void checkGPIOChannel(final int channel) {
		if(channel < 0 || channel >= kGPIOChannels) {
			StringBuilder buf = new StringBuilder();
			buf.append(
					"Requested GPIO channel is out of range. Minimum: 0, Maximum: ")
					.append(kGPIOChannels).append(", Requested: ")
					.append(channel);
			throw new IndexOutOfBoundsException(buf.toString());
		}
	}
	
	/**
	 * Check that the digital channel number is valid. Verify that the channel
	 * number is one of the legal channel numbers. Channel numbers are 0-based.
	 *
	 * @param channel
	 *            The channel number to check.
	 */
	protected static void checkPWMChannel(final int channel) {
		if(channel < 0 || channel >= kPwmChannels) {
			StringBuilder buf = new StringBuilder();
			buf.append(
					"Requested PWM channel is out of range. Minimum: 0, Maximum: ")
					.append(kPwmChannels).append(", Requested: ")
					.append(channel);
			throw new IndexOutOfBoundsException(buf.toString());
		}
	}
	
	/**
	 * Free the resources used by this object.
	 */
	public void free() {
	}
}
