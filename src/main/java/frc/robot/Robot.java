/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.AutoCommands;
import frc.robot.auto.Autonomous;
import frc.robot.auto.PID;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;
import frc.robot.subsystems.Shooter;

/**
 * This is FRC team 2386 code
 * 
 * @author Noah Tomkins
 * @author Luc Suzuki
 */
public class Robot extends TimedRobot {

  private final boolean newBot = true; 

  private AHRS ahrs = new AHRS(Port.kUSB);

  private Drivetrain mDrivetrain = new Drivetrain(newBot);
  private OI mOi = new OI();
  private BallHandler mBallHandler = new BallHandler();
  private Shooter mShooter = new Shooter();
  private Retriever mRetriever = new Retriever();
  private Climber mClimber = new Climber();
  private ColorWheel mColorWheel = new ColorWheel();

  private Teleop mTeleop = new Teleop(mOi, mDrivetrain, mShooter, mRetriever, mClimber, mBallHandler, mColorWheel);
  private AutoCommands mAutoCommands = new AutoCommands(ahrs, mDrivetrain, newBot);
  private Autonomous mAutonomous = new Autonomous(mDrivetrain, mShooter, mRetriever, mClimber, mBallHandler, mColorWheel, mAutoCommands, ahrs);


  @Override
  public void robotInit() {

    SmartDashboard.putNumber("DrivePSet", 0);
    SmartDashboard.putNumber("DriveISet", 0);
    SmartDashboard.putNumber("DriveDSet", 0); 

    SmartDashboard.putNumber("GyroPSet", 0);
    SmartDashboard.putNumber("GyroISet", 0);
    SmartDashboard.putNumber("GyroDSet", 0);

    //Resets all devices
    mClimber.resetEncoder();
    mColorWheel.resetEncoderExtend();
    mColorWheel.resetEncoderSpinner();
    mDrivetrain.resetEncoders();
    mShooter.resetEncoderTurret();
    mShooter.resetEncoderWheel();
    ahrs.reset();
  }

  @Override
  public void disabledPeriodic() {

    //Resets all devices
    mClimber.resetEncoder();
    mColorWheel.resetEncoderExtend();
    mColorWheel.resetEncoderSpinner();
    mDrivetrain.resetEncoders();
    mShooter.resetEncoderTurret();
    mShooter.resetEncoderWheel();
    ahrs.reset();

    SmartDashboard.putBoolean("ENDLOOP", false);
  }


  @Override
  public void robotPeriodic() {
    mAutoCommands.setPID();
    mAutoCommands.printPID();

    SmartDashboard.putNumber("gyro", ahrs.getAngle());

    if(SmartDashboard.getBoolean("resetEncoders", false)) mDrivetrain.resetEncoders();
    if(SmartDashboard.getBoolean("resetNavX", false)) ahrs.reset();

    mBallHandler.periodic();
    mDrivetrain.periodic();
    mShooter.periodic();
    mRetriever.periodic();
    mClimber.periodic();
    mColorWheel.periodic();
  }


  @Override
  public void autonomousInit() {
    mAutonomous.init();
  }


  @Override
  public void autonomousPeriodic() {
    mAutonomous.periodic();
  }


  @Override
  public void teleopPeriodic() {
    mTeleop.drivestick();
    mTeleop.funstick();
    mTeleop.prostick();
  }

  PID testPID = new PID(0,0,0);

  @Override
  public void testPeriodic() {
    
    //mAutoCommands.gyroTurn(90, 2, 250, 2000);

    mDrivetrain.arcade(0, 0.25 * testPID.run(90, ahrs.getAngle()));

  }
}
