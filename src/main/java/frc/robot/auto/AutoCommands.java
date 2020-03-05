/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;

/**
 * Collection of all PID commands to run autonomous
 */
public class AutoCommands {

    private AHRS ahrs;
    private Drivetrain mDrivetrain;
    private BallHandler mBallHandler;
    private Retriever mRetriever;

    private double intakeSpeed = 1;
    private double handleSpeed = 0.75;

    public AutoCommands(AHRS ahrs, Drivetrain mDrivetrain, BallHandler mBallHandler, Retriever mRetriever){

        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;

    }

    /**
     * Drive the robot forward till setpoint
     * 
     * @param setpoint the distance of travel forward
     * @param speed speed of the robot [0-1]
     */
    public void forwardDrive(double setpoint, double speed){

        mDrivetrain.resetEncoders();
        
        if(setpoint >= mDrivetrain.getEncoderBL()) speed = 0;

        while(mDrivetrain.getEncoderBL() < setpoint){
            mDrivetrain.arcade(speed, 0);
            //mBallHandler.load(handleSpeed);
            //mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

    /**
     * Drive the robot forward till setpoint
     * 
     * @param setpoint the distance of travel backward
     * @param speed speed of the robot [0-1]
     */
    public void backwardDrive(double setpoint, double speed){

        mDrivetrain.resetEncoders();

        if(speed <= 0 || setpoint <= mDrivetrain.getEncoderBL()) speed = 0;

        while(mDrivetrain.getEncoderBL() > -setpoint){
            mDrivetrain.arcade(-speed, 0);
            mBallHandler.load(handleSpeed);
            mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

    public void straightForwardDrive(double enocderSetpoint, double gyroSetpoint, double encoderSpeed, double gyroSpeed){

        mDrivetrain.resetEncoders();
        
        if(encoderSpeed <= 0 || enocderSetpoint >= mDrivetrain.getEncoderBL()) encoderSpeed = 0;

        while(mDrivetrain.getEncoderBL() < enocderSetpoint){
            mDrivetrain.arcade(encoderSpeed, (ahrs.getAngle() - gyroSetpoint) * gyroSpeed);
            mBallHandler.load(handleSpeed);
            mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

    public void striaghtBackDrive(double enocderSetpoint, double gyroSetpoint, double encoderSpeed, double gyroSpeed){

        mDrivetrain.resetEncoders();

        if(encoderSpeed <= 0 || enocderSetpoint <= mDrivetrain.getEncoderBL()) encoderSpeed = 0;

        while(mDrivetrain.getEncoderBL() > -enocderSetpoint){
            mDrivetrain.arcade(-encoderSpeed, (ahrs.getAngle() - gyroSetpoint) * gyroSpeed);
            mBallHandler.load(handleSpeed);
            mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

    public void turnRight(double setpoint, double speed){

        if(speed <= 0 || setpoint >= ahrs.getAngle()) speed = 0;

        while(ahrs.getAngle() > setpoint){
            mDrivetrain.arcade(0, -speed);
            mBallHandler.load(handleSpeed);
            mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

    public void turnLeft(double setpoint, double speed){

        if(speed <= 0 || setpoint <= ahrs.getAngle()) speed = 0;

        while(mDrivetrain.getEncoderBL() > -setpoint){
            mDrivetrain.arcade(0, -speed);
            mBallHandler.load(handleSpeed);
            mRetriever.intake(intakeSpeed);
        }
        mDrivetrain.arcade(0, 0);

    }

}
