/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Ball handler controls the delivery of the balls from the retriever to the shooter
 * 
 * @author Luc Suzuki, Noah Tomkins, Kyla Rowan
 */
public class BallHandler extends SubsystemBase {

  private TalonSRX[] TALON_BALLS = {
    new TalonSRX(RobotMap.TALON_BALLHANDLE0),
    new TalonSRX(RobotMap.TALON_BALLHANDLE1),
    new TalonSRX(RobotMap.TALON_BALLHANDLE2),
  };

  private DigitalInput[] BALLSENSE = {
    new DigitalInput(RobotMap.DIO_BALLSENSE0),
    new DigitalInput(RobotMap.DIO_BALLSENSE1),
    new DigitalInput(RobotMap.DIO_BALLSENSE2),
    new DigitalInput(RobotMap.DIO_BALLSENSE3),
    new DigitalInput(RobotMap.DIO_BALLSENSE4)
  };

  private boolean latch = false;

  //Ball sensor states
  private boolean[] balls = {false, false, false, false, false};
  private double[] speedsLoad = {0.6, 0.6, 1};
  private double[] speedsUnload = {1, 1, 1};

  private double[] handlerDelayMs = {500, 500, 500};
  private double[] handleTime = {0, 0, 0};
  private int unloading = 0;

  /**
   * Creates a new BallHandler
   */
  public BallHandler() {

    for(int i = 0; i < 3; i++){
      TALON_BALLS[i].setInverted(true);
    } 

  }

  public void unlatch(){
    latch = false;
  }

  /**
   * intakes the ball into the ball handler
   * 
   * @param speed speed of the loading [0-1]
   */
  public void load(double speed){

    periodic();

    if((balls[3] || balls[4])){
      latch = true;
    }

    if(latch){
      TALON_BALLS[2].set(ControlMode.PercentOutput, 0);
    } else {
      TALON_BALLS[2].set(ControlMode.PercentOutput, speed * speedsLoad[2]);
    }

    if(balls[2] && balls[3]){
      TALON_BALLS[1].set(ControlMode.PercentOutput, 0);
    } else {
      TALON_BALLS[1].set(ControlMode.PercentOutput, speed * speedsLoad[1]);
    }

    if(balls[1] && balls[2] && balls[3]){
      TALON_BALLS[0].set(ControlMode.PercentOutput, 0);
    } else {
      TALON_BALLS[0].set(ControlMode.PercentOutput, speed * speedsLoad[0]);
    }

  }

  /**
   * Lets out the balls from the ball handler to the shooter
   * 
   * @param speed speed of unloading [0-1]
   */
  public void unload(double speed){

    for(int i = 0; i < 3; i++){
      TALON_BALLS[i].set(ControlMode.PercentOutput, speed  * speedsUnload[i]);
    }

  }

  /**
   * Lets out the balls from the ball handler to the shooter
   * 
   * @param speed speed of unloading [0-1]
   */
  public void unload(double[] speed){

    for(int i = 0; i < 3; i++){
      TALON_BALLS[i].set(ControlMode.PercentOutput, speed[i]  * speedsUnload[i]);
    }

  }

  public void unload(){

    for(int i = 0; i < 3; i++){
      TALON_BALLS[i].set(ControlMode.PercentOutput, 1);
    }

  }

  public void delayedUnloadSet(){
    handleTime[0] = System.currentTimeMillis() + handlerDelayMs[0];
    handleTime[1] = System.currentTimeMillis() + handlerDelayMs[0] + handlerDelayMs[1];
    handleTime[2] = System.currentTimeMillis() + handlerDelayMs[0] + handlerDelayMs[1] + handlerDelayMs[2];
  }

  public void delayedUnloadSet(int delay){
    handleTime[0] = System.currentTimeMillis() + handlerDelayMs[0] + delay;
    handleTime[1] = System.currentTimeMillis() + handlerDelayMs[0] + handlerDelayMs[1] + delay;
    handleTime[2] = System.currentTimeMillis() + handlerDelayMs[0] + handlerDelayMs[1] + handlerDelayMs[2] + delay;
  }

  public void delayedUnload(double unloadSpeed){
    if(handleTime[2] < System.currentTimeMillis()){
      unload(new double[]{unloadSpeed, unloadSpeed, unloadSpeed});
      unloading = 3;
    } else if(handleTime[1] < System.currentTimeMillis()){
      unload(new double[]{0, unloadSpeed, unloadSpeed});
      unloading = 2;
    } else if(handleTime[0] < System.currentTimeMillis()){
      unload(new double[]{0, 0, unloadSpeed});
      unloading = 1;
    } else {
      unload(0);
    }
  }



  /**
   * Lets out the balls from the ball handler
   * 
   * @param speed speed of reverse unloading [0-1]
   */
  public void reverseUnload(double speed){

    
    for(int i = 0; i < 3; i++){
      TALON_BALLS[i].set(ControlMode.PercentOutput, -speed);
    }

  }

  @Override
  public void periodic() {
    for(int i = 0; i < BALLSENSE.length; i++){
      balls[i] = !BALLSENSE[i].get();
      SmartDashboard.putBoolean("Ball Handler: Ball " + i, this.balls[i]);
    }
    SmartDashboard.putNumber("unloading", unloading);
    SmartDashboard.putNumber("handleTime0", handleTime[0]);
    SmartDashboard.putNumber("handleTime1", handleTime[1]);
    SmartDashboard.putNumber("handleTime0", handleTime[2]);
    SmartDashboard.putNumber("currentTime", System.currentTimeMillis());

    SmartDashboard.putNumber("motor0", TALON_BALLS[0].getMotorOutputPercent());
    SmartDashboard.putNumber("motor1", TALON_BALLS[1].getMotorOutputPercent());
    SmartDashboard.putNumber("motor2", TALON_BALLS[2].getMotorOutputPercent());

    SmartDashboard.putBoolean("latch", latch);
  }
}