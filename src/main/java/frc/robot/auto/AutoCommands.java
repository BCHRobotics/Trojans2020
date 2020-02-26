/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

/**
 * Collection of all PID commands to run autonomous
 */
public class AutoCommands {

    private AHRS ahrs;
    private Drivetrain mDrivetrain;

    private double speedENCO = -1, speedGYRO = 1;

    private double endTime = 0, inRange = 0, inRangeSet = 0;

    private PID drivePID;
    private PID gyroPID;

    public AutoCommands(AHRS ahrs, Drivetrain mDrivetrain, boolean newBot){

        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;

        // Set PID to the RobotMap varibales
        if(newBot){
            drivePID = new PID(RobotMap.P_DRIVETRAIN, RobotMap.I_DRIVETRAIN, RobotMap.D_DRIVETRAIN);
            gyroPID = new PID(RobotMap.P_NAVX, RobotMap.I_NAVX, RobotMap.D_NAVX);
        } else{ 
            drivePID = new PID(RobotMap.P_DRIVETRAIN_OLD, RobotMap.I_DRIVETRAIN_OLD, RobotMap.D_DRIVETRAIN_OLD);
            gyroPID = new PID(RobotMap.P_NAVX_OLD, RobotMap.I_NAVX_OLD, RobotMap.D_NAVX_OLD);
        }

    }

    /**
     * Drive the robot only using the encoders
     * 
     * @param setpoint the point at which we want the encoders to be at
     * @param range The range we want the value to be between 
     * @param rangeTimeMs How long you want to be in the range
     * @param timeoutMs Max time you want this command to run
     */
    public void encoderDrive(double setpoint, double range, double rangeTimeMs, double timeoutMs){

        this.endTime = System.currentTimeMillis() + timeoutMs;
        this.inRange = 0;
    
        boolean runLoop = true;

        //Run until you reach the timeout or are within the range for a set amount of time.
        while(runLoop){

            if(System.currentTimeMillis() > this.endTime) runLoop = false;
            if(this.inRange > this.inRangeSet) runLoop = false;

            /**
            * when the error is within the range of the setpoint then start counting 
            * the inRange until it catches up to the inRangeSet.
            */
            if((mDrivetrain.getEncoderBL() < (setpoint + range)) && (mDrivetrain.getEncoderBL() > (setpoint - range))){
                this.inRange = System.currentTimeMillis();
            } else {
                this.inRangeSet = System.currentTimeMillis() + rangeTimeMs;
            }

            mDrivetrain.arcade(drivePID.run(setpoint, mDrivetrain.getEncoderBL()) * speedENCO, 0);

            SmartDashboard.putNumber("endTime", this.endTime);
            SmartDashboard.putNumber("currentTime", System.currentTimeMillis());

            SmartDashboard.putNumber("encoderBL", mDrivetrain.getEncoderBL());
            SmartDashboard.putNumber("gyro", ahrs.getAngle());

        }

        mDrivetrain.arcade(0, 0);
        drivePID.resetPID();

    }

    /**
     * Drive the robot only using the robot gyro
     * 
     * @param setpoint the angle we want the gyro to be at
     * @param range the range we want the angle to be between
     * @param rangeTimeMs How long you want to be in the range
     * @param timeoutMs Max time you want to run this command
     */
    public void gyroTurn(double setpoint, double range, double rangeTimeMs, double timeoutMs){

        this.endTime = System.currentTimeMillis() + timeoutMs;
        this.inRange = 0;
    
        boolean runLoop = true;

        //Run until you reach the timeout or are within the range for a set amount of time.
        while(runLoop){

            if(System.currentTimeMillis() > this.endTime) runLoop = false;
            if(this.inRange > this.inRangeSet) runLoop = false;

            /**
            * when the error is within the range of the setpoint then start counting 
            * the inRange until it catches up to the inRangeSet.
            */
            if((ahrs.getAngle() < (setpoint + range)) && (ahrs.getAngle() > (setpoint - range))){
                this.inRange = System.currentTimeMillis();
            } else {
                this.inRangeSet = System.currentTimeMillis() + rangeTimeMs;
            }

            mDrivetrain.arcade(0, gyroPID.run(setpoint, ahrs.getAngle()) * speedGYRO);

            SmartDashboard.putNumber("endTime", this.endTime);
            SmartDashboard.putNumber("currentTime", System.currentTimeMillis());

            SmartDashboard.putNumber("encoderBL", mDrivetrain.getEncoderBL());
            SmartDashboard.putNumber("gyro", ahrs.getAngle());

        }

        mDrivetrain.arcade(0, 0);
        gyroPID.resetPID();
        SmartDashboard.putBoolean("ENDLOOP", true);
    }

    /**
     * Drives the robot straight
     * 
     * @param setpoint How far we want the robot to move
     * @param range The range we want the value to be between for the encoder
     * @param rangeTimeMs How long you want to be in the range
     * @param timeoutMs Max time you want to run this command
     */
    public void straightDrive(double setpoint, double range, double rangeTimeMs, double timeoutMs){

        this.endTime = System.currentTimeMillis() + timeoutMs;
        this.inRange = 0;
    
        boolean runLoop = true;

        //Run until you reach the timeout or are within the range for a set amount of time.
        while(runLoop){

            if(System.currentTimeMillis() > this.endTime) runLoop = false;
            if(this.inRange > this.inRangeSet) runLoop = false;

            /**
            * when the error is within the range of the setpoint then start counting 
            * the inRange until it catches up to the inRangeSet.
            */
            if((mDrivetrain.getEncoderBL() < (setpoint + range)) && (mDrivetrain.getEncoderBL() > (setpoint - range))){
                this.inRange = System.currentTimeMillis();
            } else {
                this.inRangeSet = System.currentTimeMillis() + rangeTimeMs;
            }

            mDrivetrain.arcade(drivePID.run(setpoint, mDrivetrain.getEncoderBL()) * speedENCO, 
                                gyroPID.run(0, ahrs.getAngle()) * speedGYRO);

            SmartDashboard.putNumber("endTime", this.endTime);
            SmartDashboard.putNumber("currentTime", System.currentTimeMillis());

            SmartDashboard.putNumber("encoderBL", mDrivetrain.getEncoderBL());
            SmartDashboard.putNumber("gyro", ahrs.getAngle());

        }

        mDrivetrain.arcade(0, 0);
        drivePID.resetPID();
        gyroPID.resetPID();
    }

    public void printPID(){

        SmartDashboard.putNumber("DriveP", drivePID.getkP());
        SmartDashboard.putNumber("DriveI", drivePID.getkI());
        SmartDashboard.putNumber("DriveD", drivePID.getkP());

        SmartDashboard.putNumber("GyroP", gyroPID.getkP());
        SmartDashboard.putNumber("GyroI", gyroPID.getkI());
        SmartDashboard.putNumber("GyroD", gyroPID.getkD());
    }

    public void setPID(){

        drivePID.setkP(SmartDashboard.getNumber("DrivePSet", 0));
        drivePID.setkI(SmartDashboard.getNumber("DriveISet", 0));
        drivePID.setkD(SmartDashboard.getNumber("DriveDSet", 0)); 

        gyroPID.setkP(SmartDashboard.getNumber("GyroPSet", 0));
        gyroPID.setkI(SmartDashboard.getNumber("GyroISet", 0));
        gyroPID.setkD(SmartDashboard.getNumber("GyroDSet", 0));
    }

}
