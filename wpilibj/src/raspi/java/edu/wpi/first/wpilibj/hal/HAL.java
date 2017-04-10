/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * JNI Wrapper for HAL<br>
 * .
 */
@SuppressWarnings({ "AbbreviationAsWordInName", "MethodName" })
public class HAL {
	public static void waitForDSData() {
		System.out.println("HAL: waitForDSData");
	}
	
	public static int initialize(int mode) {
		System.out.println("HAL: initialize (" + mode + ")");
		return 0;
	}
	
	public static void observeUserProgramStarting() {
		System.out.println("HAL: observeUserProgramStarting");
	}
	
	public static void observeUserProgramDisabled() {
		System.out.println("HAL: observeUserProgramDisabled");
	}
	
	public static void observeUserProgramAutonomous() {
		System.out.println("HAL: observeUserProgramAutonomous");
	}
	
	public static void observeUserProgramTeleop() {
		System.out.println("HAL: observeUserProgramTeleop");
	}
	
	public static void observeUserProgramTest() {
		System.out.println("HAL: observeUserProgramTest");
	}
	
	public static void report(int resource, int instanceNumber) {
		report(resource, instanceNumber, 0, "");
	}
	
	public static void report(int resource, int instanceNumber, int context) {
		report(resource, instanceNumber, context, "");
	}
	
	/**
	 * Report the usage of a resource of interest. <br>
	 *
	 * <p>
	 * Original signature:
	 * <code>uint32_t report(tResourceType, uint8_t, uint8_t, const
	 * char*)</code>
	 *
	 * @param resource
	 *            one of the values in the tResourceType above (max value 51). <br>
	 * @param instanceNumber
	 *            an index that identifies the resource instance. <br>
	 * @param context
	 *            an optional additional context number for some cases (such as
	 *            module number). Set to 0 to omit. <br>
	 * @param feature
	 *            a string to be included describing features in use on a
	 *            specific resource. Setting the same resource more than once
	 *            allows you to change the feature string.
	 */
	public static int report(int resource, int instanceNumber, int context,
			String feature) {
		System.out.println("HAL: report(" + resource + ", " + instanceNumber
				+ ", " + context + ", " + feature + ")");
		return 0;
	}
	
	private static int nativeGetControlWord() {
		System.out.println("HAL: nativeGetControlWord");
		return 0;
	}
	
	@SuppressWarnings("JavadocMethod")
	public static void getControlWord(ControlWord controlWord) {
		int word = nativeGetControlWord();
		controlWord.update((word & 1) != 0, ((word >> 1) & 1) != 0,
				((word >> 2) & 1) != 0, ((word >> 3) & 1) != 0,
				((word >> 4) & 1) != 0, ((word >> 5) & 1) != 0);
	}
	
	private static int nativeGetAllianceStation() {
		System.out.println("HAL: nativeGetAllianceStation");
		return 0;
	}
	
	@SuppressWarnings("JavadocMethod")
	public static AllianceStationID getAllianceStation() {
		switch(nativeGetAllianceStation()){
			case 0:
				return AllianceStationID.Red1;
			case 1:
				return AllianceStationID.Red2;
			case 2:
				return AllianceStationID.Red3;
			case 3:
				return AllianceStationID.Blue1;
			case 4:
				return AllianceStationID.Blue2;
			case 5:
				return AllianceStationID.Blue3;
			default:
				return null;
		}
	}
	
	public static int kMaxJoystickAxes = 12;
	public static int kMaxJoystickPOVs = 12;
	
	public static short getJoystickAxes(byte joystickNum, float[] axesArray) {
		System.out.println("HAL: getJoystickAxes(" + joystickNum + ", "
				+ Arrays.toString(axesArray) + ")");
		return 0;
	}
	
	public static short getJoystickPOVs(byte joystickNum, short[] povsArray) {
		System.out.println("HAL: getJoystickPOV(" + joystickNum + ", "
				+ Arrays.toString(povsArray) + ")");
		return 0;
	}
	
	public static int getJoystickButtons(byte joystickNum, ByteBuffer count) {
		System.out.println("HAL: getJoystickButtons(" + joystickNum + ", "
				+ count + ")");
		return 0;
	}
	
	public static int setJoystickOutputs(byte joystickNum, int outputs,
			short leftRumble, short rightRumble) {
		System.out.println("HAL: setJoystickOutputs(" + joystickNum + ", "
				+ outputs + ", " + leftRumble + ", " + rightRumble + ")");
		return 0;
	}
	
	public static int getJoystickIsXbox(byte joystickNum) {
		System.out.println("HAL: getJoystickIsXbox(" + joystickNum + ")");
		return 0;
	}
	
	public static int getJoystickType(byte joystickNum) {
		System.out.println("HAL: getJoystickType(" + joystickNum + ")");
		return 0;
	}
	
	public static String getJoystickName(byte joystickNum) {
		System.out.println("HAL: getJoystickName(" + joystickNum + ")");
		return "";
	}
	
	public static int getJoystickAxisType(byte joystickNum, byte axis) {
		System.out.println("HAL: getJoystickAxisType(" + joystickNum + ", "
				+ axis + ")");
		return 0;
	}
	
	public static double getMatchTime() {
		System.out.println("HAL: getMatchTime");
		return 0;
	}
	
	public static boolean getSystemActive() {
		System.out.println("HAL: getSystemActive");
		return false;
	}
	
	public static boolean getBrownedOut() {
		System.out.println("HAL: getBrownedOut");
		return false;
	}
	
	public static int setErrorData(String error) {
		System.err.println("HAL: setErrorData(" + error + ")");
		return 0;
	}
	
	public static int sendError(boolean isError, int errorCode,
			boolean isLVCode, String details, String location,
			String callStack, boolean printMsg) {
		System.err.println("HAL: sendError(" + isError + ", " + errorCode
				+ ", " + isLVCode + ", " + details + ", " + location + ", "
				+ callStack + ", " + printMsg + ")");
		return 0;
	}
}
