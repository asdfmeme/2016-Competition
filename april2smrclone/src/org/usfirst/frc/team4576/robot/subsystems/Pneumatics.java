package org.usfirst.frc.team4576.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	public Pneumatics() {
		// psensor = new AnalogInput(0);
		s0 = new Solenoid(1);/* Shifting */
		s1 = new Solenoid(0);/* Tomahawk Control */
		
		// s_ = new Solenoid(_); /*Left tomahawk */
		// s_ = new Solenoid(_); /*Small pistons for climbing */

	}

	private Compressor c;
	// public AnalogInput psensor;
	private Solenoid s0;
	private Solenoid s1;

	// private Solenoid s(_);
	// private Solenoid s(_);

	@Override
	protected void initDefaultCommand() {
		c = new Compressor();
		// sensor0 = new AnalogPressure (0,5);
	}

	public void setAutoEnabled() {

		c.setClosedLoopControl(true);
	}

	public void setShift(boolean closed) {
		s0.set(closed);
	}

	public void shift() {
		s0.set(!s0.get());

	}

	public void setTomahawks(boolean closed) {
		s1.set(closed);

	}

	public void tomahawks() {
		s1.set(!s1.get());

	}

	

	// public void toggleComp() {
	// if (c.enabled())
	// c.stop();
	// else
	// c.start();
	// public void setPush(boolean closed) {
	// s3.set(closed);
	// }

	// public void push() {
	// s3.set(!s3.get());

	/* Check This */

	// private AnalogInput input;
	/**
	 * please remember, when you call a method, it runs the code in the method,
	 * so since all of this code was outside of the method, in the class
	 * instead, it ran once on startup, probably before the analog channel was
	 * even close to being initialized
	 * 
	 * you want to read the pressure value every time you call this, not once on
	 * startup
	 * 
	 * also this is a getter method, it has "get" in the name, it should return
	 * the value it is getting, not void
	 */
	// public double getPressure(){
	/**
	 * The input voltage provided to the sensor
	 */
	// double inputVoltage = 5;

	/**
	 * What to use if we are no provided with another input voltage
	 */
	// private final static double DEFAULT_VOLTAGE = 5.0;

	// double pressure = (250 * (psensor.getVoltage()/inputVoltage) - 25);

	// SmartDashboard.putNumber("Pressure:", pressure);
	// return pressure;
}
