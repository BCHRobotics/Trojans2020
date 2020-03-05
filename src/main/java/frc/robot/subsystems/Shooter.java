/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.auto.PID;

/**
 * Shoots the balls into the goal
 * 
 * @author Luc Suzuki, Kyla Rowan, Noah Tomkins
 */
public class Shooter extends SubsystemBase {

  private CANSparkMax SPARK_SHOOTERTURRET = new CANSparkMax(RobotMap.SPARK_SHOOTERTURRET, MotorType.kBrushless);
  private CANSparkMax SPARK_SHOOTERWHEEL = new CANSparkMax(RobotMap.SPARK_SHOOTERWHEEL, MotorType.kBrushless);

  private CANPIDController wheelPIDcontroller = new CANPIDController(SPARK_SHOOTERWHEEL);
 
  private CANEncoder encoderTurret = new CANEncoder(SPARK_SHOOTERTURRET);
  private CANEncoder encoderWheel = new CANEncoder(SPARK_SHOOTERWHEEL);
 
  private final double encoderCalTurret = 1.162325;
  private final double encoderCalWheel = 1;
  
  private final int minTurret = -75;
  private final int minSafeTurret = -45;
  private final int minVerySafeTurret = -15;
  private final int maxTurret = 190;
  private final int maxSafeTurret = 160;
  private final int maxVerySafeTurret = 130;

  //private final double[] deathSpeeds = {0,0};

  private PID turretPID = new PID(RobotMap.P_TURRET, RobotMap.I_TURRET, RobotMap.D_TURRET, "turretPID");

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    
  }

  /**
   * Sets the speed of the turret
   * 
   * @param speed The speed at which the turret spins at
   */
  public void turretSpeed(double speed){

    if((getEncoderTurret() <= minTurret && speed < 0) || (getEncoderTurret() >= maxTurret && speed > 0)){
      SPARK_SHOOTERTURRET.set(0);
    } else if((getEncoderTurret() <= minSafeTurret && speed < 0) || (getEncoderTurret() >= maxSafeTurret && speed > 0)){
      SPARK_SHOOTERTURRET.set(speed * 0.1);
    } else if((getEncoderTurret() <= minVerySafeTurret && speed < 0) || (getEncoderTurret() >= maxVerySafeTurret && speed > 0)){
      SPARK_SHOOTERTURRET.set(speed * 0.2);
    } else{
      SPARK_SHOOTERTURRET.set(speed);
    }

  }

  /**
   * Sets the speed of the wheel
   * 
   * @param speed The speed of the shooter wheel 
   */
  public void wheelSpeed(double speed){

    if(speed >= 0.4){
      SPARK_SHOOTERWHEEL.set(speed);
    } else {
      SPARK_SHOOTERWHEEL.set(0);
    }

    SmartDashboard.putNumber("Shooter speed:", speed);

  }

 
  /**
   * Sets the angle of the turret WIP
   *  
   * @param degrees Angle of the turret
   * @param speed The speed of the turret
   */
  public void setTurret(double degrees, double speed){

    double rcw = turretPID.run(degrees, getEncoderTurret());
    turretSpeed(rcw * speed);

  }

 
  /**
   * Sets the speed of the wheel
   * 
   * @param rpm The speed of the wheel
   */
  public void setWheelSpeed(double rpm){

    wheelPIDcontroller.setReference(rpm, ControlType.kVelocity);

  }

  /**
   * The speed of the wheel
   * 
   * @return Rpm of the motor
   */
  public double getWheelRpm(){
    return encoderWheel.getVelocity();
  }

  /**
   * gets the value of calabrate value of the turret encoder 
   * 
   * @return the angle of the turret 
   */
  public double getEncoderTurret(){
    return encoderTurret.getPosition() * encoderCalTurret;
  }

  /**
   * gets the value of calabrate value of the wheel encoder 
   * 
   * @return the encoder value of wheel 
   */
  public double getEncoderWheel(){
    return encoderWheel.getPosition() * encoderCalWheel;
  }

  /**
   * reset the encoder for the turret
   */
  public void resetEncoderTurret(){
    encoderTurret.setPosition(0);
  }

  /**
   * reset the encoder for the wheel
   */
  public void resetEncoderWheel(){
    encoderWheel.setPosition(0);
  }

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Shooter: encoderTurret", getEncoderTurret());
    SmartDashboard.putNumber("Shooter: encoderWheel", getEncoderWheel());
    SmartDashboard.putNumber("Shooter: rpmWheel", getWheelRpm());

  }
}
