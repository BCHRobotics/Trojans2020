/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.AutoCommands;
import frc.robot.auto.Autonomous;
import frc.robot.auto.Humber;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;
import frc.robot.subsystems.Shooter;
import frc.robot.vision.VisionTracking;

/**
 * This is FRC team 2386 code 2500
 */
public class Robot extends TimedRobot {

  public static AHRS ahrs = new AHRS(Port.kUSB); // NavX Gyro

  public static Drivetrain mDrivetrain = new Drivetrain();
  public static OI mOi = new OI();
  public static BallHandler mBallHandler = new BallHandler();
  public static Shooter mShooter = new Shooter();
  public static Retriever mRetriever = new Retriever();
  public static Climber mClimber = new Climber();
  // public static ColorWheel mColorWheel = new ColorWheel();

  public static VisionTracking mVisionTracking = new VisionTracking(mShooter, mBallHandler);
  public static Teleop mTeleop = new Teleop(mOi, mDrivetrain, mShooter, mBallHandler, mVisionTracking, mClimber, mRetriever);
  public static AutoCommands mAutoCommands = new AutoCommands(ahrs, mDrivetrain, mBallHandler, mRetriever);
  public static Autonomous mAutonomous = new Autonomous(mDrivetrain, mAutoCommands, ahrs, mVisionTracking, mRetriever, mShooter);
  public static Humber mHumber = new Humber(mDrivetrain, mVisionTracking, mRetriever, mShooter, mBallHandler);


  @Override
  public void robotInit() {

    CameraServer.getInstance().startAutomaticCapture();

    SmartDashboard.putNumber("DrivePSet", 0);
    SmartDashboard.putNumber("DriveISet", 0);
    SmartDashboard.putNumber("DriveDSet", 0);

    SmartDashboard.putNumber("GyroPSet", 0);
    SmartDashboard.putNumber("GyroISet", 0);
    SmartDashboard.putNumber("GyroDSet", 0);

    SmartDashboard.putBoolean("CLIMB MODE", false);
    SmartDashboard.putNumber("Tele: Shooter Speed", -1);
    SmartDashboard.putNumber("INTAKE:", -1);
    SmartDashboard.putNumber("Shooter speed:", -1);
    SmartDashboard.putNumber("liftSpeedTele", -1);
    SmartDashboard.putNumber("wheel rpm set", -1);
    SmartDashboard.putNumber("unloading", -1);
    SmartDashboard.putNumber("thorMulti", 0);
    SmartDashboard.putNumber("tuneWheelSpeedTest", 0);
    SmartDashboard.putBoolean("WHEEELLLELLELELEL", false);

    SmartDashboard.putBoolean("loop1", false);
    SmartDashboard.putBoolean("loop2", false);
    SmartDashboard.putBoolean("loop3", false);
    SmartDashboard.putBoolean("loop4", false);
    SmartDashboard.putBoolean("loop5", false);
    SmartDashboard.putNumber("HUMBER AUTO TIME", -1);

    // Resets all devices
    mClimber.resetEncoder();
    // mColorWheel.resetEncoderExtend();
    // mColorWheel.resetEncoderSpinner();
    mDrivetrain.resetEncoders();
    mShooter.resetEncoderWheel();
    mShooter.resetEncoderTurret();
    ahrs.reset();
    mRetriever.resetEncoder();
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }

  @Override
  public void disabledPeriodic() {

    // Resets all devices
    // mClimber.resetEncoder();
    // mColorWheel.resetEncoderExtend();
    // mColorWheel.resetEncoderSpinner();
    mDrivetrain.resetEncoders();
    mShooter.resetEncoderWheel();
    ahrs.reset();
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

    SmartDashboard.putBoolean("ENDLOOP", false);

    SmartDashboard.putBoolean("loop1", false);
    SmartDashboard.putBoolean("loop2", false);
    SmartDashboard.putBoolean("loop3", false);
  }

  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("gyro", ahrs.getAngle());

    if (SmartDashboard.getBoolean("resetEncoders", false))
      mDrivetrain.resetEncoders();
    if (SmartDashboard.getBoolean("resetNavX", false))
      ahrs.reset();

    mBallHandler.periodic();
    mDrivetrain.periodic();
    mShooter.periodic();
    mRetriever.periodic();
    mClimber.periodic();
    // mColorWheel.periodic();
    mVisionTracking.periodic();
  }

  @Override
  public void autonomousInit() {
    //mAutonomous.init();

    mHumber.init();
  }

  @Override
  public void autonomousPeriodic() {
    //mAutonomous.periodic();
    //mAutoCommands.forwardDrive(120, 0.5);
    
    mShooter.setWheelSpeed(5000);
    //mHumber.sixBall();
    mHumber.threeBallMiddle();
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    mTeleop.drivestick();
    mTeleop.funstick();
    mTeleop.teststick();
  }

  @Override
  public void testPeriodic() {

    if(mOi.teststick.getRawButton(1)){
      mBallHandler.unload(new double[]{1,1,1});
      mShooter.setWheelSpeed(SmartDashboard.getNumber("tuneWheelSpeedTest", 0));
    } else {
      mBallHandler.unload(0);
      mShooter.wheelSpeed(0.5);
    }
    
    
  }
}