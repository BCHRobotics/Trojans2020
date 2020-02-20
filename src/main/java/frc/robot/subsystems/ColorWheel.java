/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ColorWheel extends SubsystemBase {

  private CANSparkMax SPARK_COLOREXTEND = new CANSparkMax(RobotMap.SPARK_COLOREXTEND, MotorType.kBrushless);
  private CANSparkMax SPARK_COLORSPINNER = new CANSparkMax(RobotMap.SPARK_COLORSPINNER, MotorType.kBrushless);

  private CANEncoder encoderExtend = new CANEncoder(SPARK_COLOREXTEND);
  private CANEncoder encoderSpinner = new CANEncoder(SPARK_COLORSPINNER);

  private ColorSensorV3 mColorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
  private ColorMatch mColorMatch = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private double encoderExtendCal = 1;
  private double encoderSpinnerCal = 1;

  private double extendMin = 0;
  private double extendMax = 1;

  private double spinThreeEncoder = 1;

  private String colorString;
  
  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel() {

    SPARK_COLOREXTEND.setInverted(false);
    SPARK_COLORSPINNER.setInverted(false);

    mColorMatch.addColorMatch(kBlueTarget);
    mColorMatch.addColorMatch(kGreenTarget);
    mColorMatch.addColorMatch(kRedTarget);
    mColorMatch.addColorMatch(kYellowTarget);
  
  }

  public void extend(double speed){

    if(getEncoderExtend() <= extendMax ){
      SPARK_COLOREXTEND.set(speed);
    } else{
      SPARK_COLOREXTEND.set(0);
    }

    resetEncoderSpinner();

  }

  public void retract(double speed){

    if(getEncoderExtend() >= extendMin ){
      SPARK_COLOREXTEND.set(speed);
    } else{
      SPARK_COLOREXTEND.set(0);
    }
  
  }

  public void manualSpin(double speed){
    SPARK_COLORSPINNER.set(speed);
  }

  public void automaticSpinColor(double speed){
  
    //COME BACK TO THIS

  }

  public void automaticSpinRotations(double speed){
  
    if(getEncoderSpinner() < spinThreeEncoder){
      manualSpin(0.5);
    }

  }

  public double getEncoderExtend(){
    return encoderExtend.getPosition() / encoderExtendCal;
  }

  public double getEncoderSpinner(){
    return encoderSpinner.getPosition() / encoderSpinnerCal;
  }

  public void resetEncoderExtend(){
    encoderExtend.setPosition(0);
  }

  public void resetEncoderSpinner(){
    encoderSpinner.setPosition(0);
  }

  @Override
  public void periodic() {
    
    Color detectedColor = mColorSensorV3.getColor();
    ColorMatchResult match = mColorMatch.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

  }
}
