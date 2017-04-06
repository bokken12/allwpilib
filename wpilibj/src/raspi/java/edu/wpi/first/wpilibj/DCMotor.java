/**
 * 
 */
package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.PWM.PeriodMultiplier;

/**
 * A speed controller for basic DC motors on a Raspberry Pi robot
 * 
 * @author joelmanning
 *
 */
public class DCMotor extends PWMSpeedController {
	
	/**
	 * Instantiates a new DCMotor given two GPIO pins. Note that pins 12, 13,
	 * 18, and 19 allow for hardware-based PWM control, while all other pins
	 * must use less powerful software-based PWMs
	 * 
	 * @param forward
	 *            - forward GPIO pin
	 * @param backward
	 *            - backward GPIO pin
	 */
	protected DCMotor(int forward, int backward) {
		super(PWM.packInt(forward, backward));
	}
	
}
