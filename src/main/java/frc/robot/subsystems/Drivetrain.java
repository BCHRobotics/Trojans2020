/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Drivetrain of the robot with 2 motors per side going into gearboxes, with one gearbox per side.
 * 
 * @author Luc Suzuki, Kyla Rowan, Noah Tomkins
 */
public class Drivetrain extends SubsystemBase {

  //Setup CANSparkMax Devices
  private CANSparkMax SPARK_FRONTLEFT = new CANSparkMax(RobotMap.SPARK_FRONTLEFT, MotorType.kBrushless);
  private CANSparkMax SPARK_FRONTRIGHT = new CANSparkMax(RobotMap.SPARK_FRONTRIGHT, MotorType.kBrushless);
  private CANSparkMax SPARK_BACKLEFT = new CANSparkMax(RobotMap.SPARK_BACKLEFT, MotorType.kBrushless);
  private CANSparkMax SPARK_BACKRIGHT = new CANSparkMax(RobotMap.SPARK_BACKRIGHT, MotorType.kBrushless);

  //Setup CANSparkMax Encoders
  private CANEncoder encoderFL = new CANEncoder(SPARK_FRONTLEFT);
  private CANEncoder encoderFR = new CANEncoder(SPARK_FRONTRIGHT);
  private CANEncoder encoderBL = new CANEncoder(SPARK_BACKLEFT);
  private CANEncoder encoderBR = new CANEncoder(SPARK_BACKRIGHT);

  //private double rampRate = 1;
  private double encoderCal = 2.1628959;

  //For debugging
  double leftSpeed, rightSpeed;

  /**
   * Creates a new DriveTrain.
   */
  public Drivetrain() {

  }

  /**
   * Control the robot using one variable to drive forward and one to turn
   * 
   * @param speedY y-axis speed between -1.0 and 1.0
   * @param speedTurn turn speed bewteen -1.0 and 1.0
   */
  public void arcade(double speedY, double speedTurn){

    rightSpeed = speedY + speedTurn;
    leftSpeed = speedY - speedTurn;

    SPARK_FRONTLEFT.set(leftSpeed);
    SPARK_BACKLEFT.set(leftSpeed);

    SPARK_FRONTRIGHT.set(rightSpeed);
    SPARK_BACKRIGHT.set(rightSpeed);

    //Print the speeds of the motors for debuging
    SmartDashboard.putNumber("Drivetrain: rightSpeed", rightSpeed);
    SmartDashboard.putNumber("Drivetrain: leftSpeed", leftSpeed);
    SmartDashboard.putNumber("Drivetrain: rightVelosity", encoderFR.getVelocity());
    SmartDashboard.putNumber("Drivetrain: leftVelosity", encoderFL.getVelocity());
    
  }

  /**
   * Get's the encoder value of the front left drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderFL(){
    return encoderFL.getPosition() * -encoderCal;
  }

  /**
   * Get's the encoder value of the front right drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderFR(){
    return encoderFR.getPosition() * -encoderCal;
  }


  /**
   * Get's the encoder value of the back left drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderBL(){
    return encoderBL.getPosition() * -encoderCal;
  }

  /**
   * Get's the encoder value of the back right drive motor
   * 
   * @return Distance wheel moved in inches
   */
   public double getEncoderBR(){
    return encoderBR.getPosition() * -encoderCal;
  }

  /**
   * Reset all drive encoders to 0
   */
  public void resetEncoders(){
    encoderFL.setPosition(0);
    encoderFR.setPosition(0);
    encoderBL.setPosition(0);
    encoderBR.setPosition(0);
  }

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Drivetrain: encoderFL", getEncoderFL());
    SmartDashboard.putNumber("Drivetrain: encoderFR", getEncoderFR());
    SmartDashboard.putNumber("Drivetrain: encoderBL", getEncoderBL());
    SmartDashboard.putNumber("Drivetrain: encoderBR", getEncoderBR());

    SmartDashboard.putNumber("Drivetrain: rightSpeed", rightSpeed);
    SmartDashboard.putNumber("Drivetrain: leftSpeed", leftSpeed);

    SmartDashboard.putNumber("Drivetrain: rightSpeedSPARK", SPARK_BACKRIGHT.getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain: leftSpeedSPARK", SPARK_BACKLEFT.getAppliedOutput());

    SmartDashboard.putNumber("Drivetrain: rightVelosity", encoderFR.getVelocity());
    SmartDashboard.putNumber("Drivetrain: leftVelosity", encoderFL.getVelocity());

  }
}
