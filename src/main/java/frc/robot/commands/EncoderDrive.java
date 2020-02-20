/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.auto.PID;
import frc.robot.subsystems.Drivetrain;

public class EncoderDrive extends CommandBase {

  private final Drivetrain mDrivetrain;
  private final AHRS ahrs;
  private final PID pid;

  private double endTime = 0, inRange = 0, inRangeSet = 0;
  private boolean runLoop = true;

  /**
   * Creates a new EncoderDrive.
   */
  public EncoderDrive(Drivetrain mDrivetrain, AHRS ahrs) {
    this.mDrivetrain = mDrivetrain;
    this.ahrs = ahrs;
    this.pid = new PID(RobotMap.P_DRIVETRAIN, RobotMap.I_DRIVETRAIN, RobotMap.D_DRIVETRAIN);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    this.endTime = System.currentTimeMillis();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
