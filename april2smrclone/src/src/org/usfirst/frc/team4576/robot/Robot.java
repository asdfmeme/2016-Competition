package org.usfirst.frc.team4576.robot;

import org.usfirst.frc.team4576.robot.commands.AutoDefence;
import org.usfirst.frc.team4576.robot.commands.AutoEmergencyLowBar;
import org.usfirst.frc.team4576.robot.commands.AutoEnableCompressor;
import org.usfirst.frc.team4576.robot.commands.AutoExperimentalLowGoal;
import org.usfirst.frc.team4576.robot.commands.AutoLowBar;
import org.usfirst.frc.team4576.robot.commands.AutoRTerrain;
import org.usfirst.frc.team4576.robot.commands.AutoRockWall;
import org.usfirst.frc.team4576.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team4576.robot.subsystems.Chassis;
import org.usfirst.frc.team4576.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4576.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Chassis chassis = new Chassis();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Shooter shooter = new Shooter();

	public static OI oi;
	public static Joystick driveStick = new Joystick(0);
	public static Joystick shooterStick = new Joystick(1);

	Command teleopCommand;
	Command autonomousCommand;
	Command compressorStart;
	CameraServer server;
	SendableChooser chooser;

	String VERSION = "2.9";

	public void robotInit() {
		System.out.println("RNR 2016 Competition Code Version " + VERSION + " is loading...");
		oi = new OI();
		chooser = new SendableChooser();
		chooser.addDefault("(#1) AUTO: Roll to Defence(1.5s,50%) then stop", new AutoDefence());
		chooser.addObject("(#2) AUTO: Cross LowBar(3.5s,50%) then stop. :^) ", new AutoLowBar());
		chooser.addObject("(#3) AUTO: Cross RoughTerrain (4.0s,50%) then stop", new AutoRTerrain());
		chooser.addObject("(#4) AUTO: Cross Rockwall Or Ramparts (5.0s,65%) then stop", new AutoRockWall());

		chooser.addObject("EMERGENCY AUTO: Will go backwards(3s,65%) With Tomahawks down", new AutoEmergencyLowBar());
		chooser.addObject("CAUTION!! Experimental Auto: Cross LowBar(_s), Shoot LowGoal then stop", new AutoExperimentalLowGoal());
		
		SmartDashboard.putData("Auto mode", chooser);

		teleopCommand = new DriveWithJoysticks();
		compressorStart = new  AutoEnableCompressor();
		autonomousCommand = new AutoDefence();
		autonomousCommand = new AutoLowBar();
		autonomousCommand = new AutoRTerrain();
		autonomousCommand = new AutoRockWall();
		autonomousCommand = new AutoExperimentalLowGoal();

		server = CameraServer.getInstance();
		server.setQuality(15);
		server.startAutomaticCapture("cam0");

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		teleopCommand.start();
		compressorStart.start();

	}

	public void disabledInit() {

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
