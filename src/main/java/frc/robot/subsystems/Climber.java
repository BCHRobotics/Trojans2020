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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


/**
 * Raises the robot at the end of the game.
 * 
 * @author Luc Suzuki, Noah Tomkins, Kyla Rowan
 */
public class Climber extends SubsystemBase {

  private Solenoid PCM_LEFTLOCK = new Solenoid(RobotMap.PCM_LEFTLOCK);
  private Solenoid PCM_RIGHTLOCK = new Solenoid(RobotMap.PCM_RIGHTLOCK);
  private Solenoid PCM_RATCHET = new Solenoid(RobotMap.PCM_RATCHET);

  private CANSparkMax SPARK_LIFT = new CANSparkMax(RobotMap.SPARK_LIFT, MotorType.kBrushless);
  private CANEncoder encoderLift = new CANEncoder(SPARK_LIFT);

  private double encoderCal = 1;

  private double min = 0, max = 0;
  
  /**
   * Creates a new Climber.
   */
  public Climber() {

    SPARK_LIFT.setInverted(false);

  }

  /**
   * Sets the speed of the lift, limited by the min and max encoder value.
   * 
   * @param speed sets the speed of the lift mech.
   */
  public void lift(double speed){
   
    if((getEncoder() <= min && speed < 0) || (getEncoder() >= max && speed > 0)){
      SPARK_LIFT.set(0);
    } else {
      SPARK_LIFT.set(speed);
    }

  }

  /**
   * Gets the encoder value of the lift motor
   * 
   * @return the calibrated encoder position
   */
  public double getEncoder(){
    return encoderLift.getPosition() * encoderCal;
  }

  public void resetEncoder(){
    encoderLift.setPosition(0);
  }

  /**
   * Applies power to retract the solenoid
   */
  public void lockClimber(){
    PCM_LEFTLOCK.set(true);
    PCM_RIGHTLOCK.set(true);
  }

  /**
   * Turns off power to extend the solenoid
   */
  public void unlockClimber(){
    PCM_LEFTLOCK.set(false);
    PCM_RIGHTLOCK.set(false);
  }

  /**
   * Applies power to retract the solenoid
   */
  public void lockRatchet(){
    PCM_RATCHET.set(true);
  }

  /**
   * Turns off power to extend the solenoid
   */
  public void unlockRatchet(){
    PCM_RATCHET.set(false);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Lift Encoder", getEncoder());
  }
}
