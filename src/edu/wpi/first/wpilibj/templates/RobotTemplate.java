/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Relay;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot{
    Jaguar leftMotor;
    Jaguar rightMotor;
    
    Joystick leftJoystick;
    Joystick rightJoystick;
    
    RobotDrive drive;
    
    Relay lamp;
    
    boolean driveMode = false;
    boolean lampState = false;
    boolean lampRisingEdge1 = false;
    boolean lampRisingEdge2 = false;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        leftMotor = new Jaguar(1);
        rightMotor = new Jaguar(2);
        
        leftJoystick = new Joystick(2);
        rightJoystick = new Joystick(1);
        
        drive = new RobotDrive(leftMotor, rightMotor);
        
        lamp = new Relay(1);;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        if (rightJoystick.getRawButton(4)) {
            driveMode = !driveMode;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "                  ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "                  ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "                  ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "                  ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "                  ");
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, "                  ");
        if (driveMode) {
            drive.arcadeDrive(rightJoystick);
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "Arcade Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "Arcade Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Arcade Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "Arcade Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "Arcade Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, "Arcade Mode Active");
        }
        else {
            drive.tankDrive(leftJoystick, rightJoystick);
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "Tank Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "Tank Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Tank Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "Tank Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "Tank Mode Active");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, "Tank Mode Active");
        }
        DriverStationLCD.getInstance().updateLCD();
        lampRisingEdge1 = lampRisingEdge2;
        lampRisingEdge2 = rightJoystick.getRawButton(5);
        if (lampRisingEdge2 && !lampRisingEdge1) {
            lampState = !lampState;
        }
        if (lampState) {
            lamp.set(Relay.Value.kForward);
        }
        else {
            lamp.set(Relay.Value.kOff);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}