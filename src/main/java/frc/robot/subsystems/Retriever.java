/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Picks up the ball from the ground and devlivers them to the ball handler.
 * 
 * @author Luc Suzuki, Noah Tomkins, Kyla Rowan
 */
public class Retriever extends SubsystemBase {

  private CANSparkMax SPARK_ARM = new CANSparkMax(RobotMap.SPARK_ARM, MotorType.kBrushless);
  private CANEncoder encoderArm = new CANEncoder(SPARK_ARM);

  private TalonSRX TALON_BAR = new TalonSRX(RobotMap.TALON_BAR);

  private double encoderCal = 1;

  //Max is 100%
  private int min = -1000000, max = 1000000;
  private double rampRate = 1;

  /**
   * Creates a new Retriever.
   */
  public Retriever() {

    TALON_BAR.setInverted(false);
    TALON_BAR.configClosedloopRamp(rampRate);

  }

  /**
   *Sets the speed of the intake
   * 
   * @param speed The speed of the intake
   */
  public void intake(double speed){

    TALON_BAR.set(ControlMode.PercentOutput, speed);

  }

  /**
   * Sets the retriever to the lower position
   * 
   * @param speed sets the speed of the arm motor when lowering
   */
  public void lower(double speed){

    if(getEncoder() <= max){
      SPARK_ARM.set(speed);
    } else {
      SPARK_ARM.set(0);
    }

  }

  /**
   * Sets the retriever to the raised position
   * 
   * @param speed sets the speed of the arm on the way up
   */
  public void raise(double speed){

    if(getEncoder() >= min){
      SPARK_ARM.set(-speed);
    } else {
      SPARK_ARM.set(0);
    }

  }

  public void arm(double speed){
    SPARK_ARM.set(speed);
  }

  public double getEncoder(){
    return encoderArm.getPosition() * encoderCal;
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Retriever: Arm Encoder", getEncoder());

  }
}
