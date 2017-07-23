package edu.wpi.first.wpilibj;

/**
 * @author joelmanning
 *
 */
public class TestingRobot extends RobotBase {
	
	private DCMotor right = new DCMotor(10, 9);
	private DCMotor left = new DCMotor(8, 7);
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.RobotBase#startCompetition()
	 */
	@Override
	public void startCompetition() {
		left.set(1);
		right.set(-1);
	}
	
	public static void main(String... args){
		(new TestingRobot()).startCompetition();
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
