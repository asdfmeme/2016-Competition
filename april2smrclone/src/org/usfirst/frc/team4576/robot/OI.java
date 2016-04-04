package org.usfirst.frc.team4576.robot;

import org.usfirst.frc.team4576.robot.commands.ShooterWheel;
import org.usfirst.frc.team4576.robot.commands.TomahawkControl;
import org.usfirst.frc.team4576.robot.commands.Shift;
import org.usfirst.frc.team4576.robot.commands.ShooterStop;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//Button Values:
//
//1: A
//2: B
//3: X
//4: Y
//5: Left Bumper
//6: Right Bumper
//7: Back
//8: Start
//9: Left Joystick pressed down
//10: Right Joystick pressed down
//
//
//Axis values:
//
//1 - LeftX
//2 - LeftY
//3 - Triggers (Each trigger = 0 to 1, axis value = right - left)
//4 - RightX
//5 - RightY
//6 - DPad Left/Right
//
public class OI {
	Button ssRB = new JoystickButton(Robot.shooterStick, 5);
	Button ssLB = new JoystickButton(Robot.shooterStick, 6);
	Button ssR3 = new JoystickButton(Robot.shooterStick, 10);
	
	Button dsX = new JoystickButton(Robot.driveStick, 3);
	Button dsY = new JoystickButton(Robot.driveStick, 4);
	Button dsRB = new JoystickButton(Robot.driveStick, 6);
	Button dsB = new JoystickButton(Robot.driveStick, 2);


	public OI() {
		ssRB.whenPressed(new ShooterWheel(true));
		ssLB.whenPressed(new ShooterWheel(false));
		ssRB.whenReleased(new ShooterStop());
		ssLB.whenReleased(new ShooterStop());
		
		dsX.whenPressed(new Shift());
		dsRB.whenPressed(new TomahawkControl());
		
	}
}