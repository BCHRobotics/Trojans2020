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

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


/**
 * Raises the robot at the end of the game.
 * 
 * @author Luc Suzuki, Noah Tomkins, Kyla Rowan
 */
public class Climber extends SubsystemBase {

  private CANSparkMax SPARK_LIFT = new CANSparkMax(RobotMap.SPARK_LIFT, MotorType.kBrushless);
  private CANEncoder encoderLift = new CANEncoder(SPARK_LIFT);

  private Relay SPIKE_RELAY = new Relay(0);

  private double encoderCal = 1;

  //Max extend 100%
  private double min = -1000000, max = 10000000; //Needs to be calibrated
  
  /**
   * Creates a new Climber.
   */
  public Climber() {

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

  public void lock(){
    SPIKE_RELAY.set(Value.kForward);
  }

  public void unlock(){
    SPIKE_RELAY.set(Value.kOff);
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


  @Override
  public void periodic() {
    SmartDashboard.putNumber("Climber: Lift Encoder", getEncoder());
  }
}
