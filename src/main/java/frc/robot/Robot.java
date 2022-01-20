// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj2.command.RunCommand;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  //private final DifferentialDrive diffDrive;
  private RobotContainer m_robotContainer;
  private XboxController m_driverController;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    //accidently deleted some objects such as drivetraina and shooter but robotcontainer should have it for me
    m_robotContainer = RobotContainer.getInstance();
        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Joystick X value", RobotContainer.m_driverController.getRightX());
    SmartDashboard.putNumber("Joystick Y value", RobotContainer.m_driverController.getLeftY());
    SmartDashboard.putNumber("Joystick rTrigger", RobotContainer.m_driverController.getRightTriggerAxis());
    SmartDashboard.putNumber("Joystick lTrigger", RobotContainer.m_driverController.getLeftTriggerAxis());
    SmartDashboard.putData("DriveTrain", RobotContainer.m_drivetrain);
    SmartDashboard.putNumber("Intake", RobotContainer.m_intake.m_intakeMotor.getBusVoltage());
    SmartDashboard.putNumber("Shooter", RobotContainer.m_shooter.m_master.getBusVoltage());
    SmartDashboard.putNumber("Hopper", RobotContainer.m_hopper.m_hopperMotor.getBusVoltage());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    
    //m_drivetrain.setDefaultCommand(m_robotContainer.getDrive());
    //m_shooter.setDefaultCommand(m_robotContainer.getShoot());
  }
  }
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();

    //m_diffDrive.arcadeDrive(m_driverController.getLeftY(), m_driverController.getRightY());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}