/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;
import frc.robot.subsystems.Shooter;
import frc.robot.vision.VisionTracking;

/**
 * Add your docs here.
 */
public class Humber {

    private Drivetrain mDrivetrain;
    private VisionTracking mVisionTracking;
    private Retriever mRetriever;
    private Shooter mShooter;
    private BallHandler mBallHandler;

    private long startTime = 0;

    public Humber(Drivetrain mDrivetrain, VisionTracking mVisionTracking, Retriever mRetriever, Shooter mShooter, BallHandler mBallHandler) {
        this.mDrivetrain = mDrivetrain;
        this.mVisionTracking = mVisionTracking;
        this.mRetriever = mRetriever;
        this.mShooter = mShooter;
        this.mBallHandler = mBallHandler;
    }

    /*
    public void initDrive() {

        endTime = System.currentTimeMillis() + 1250;
        while (endTime > System.currentTimeMillis()) {
            mShooter.turretSpeed(0.2);
            mShooter.wheelSpeed(0.80);
            mRetriever.arm(0.1);
            SmartDashboard.putBoolean("loop1", true);
        }

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {

        }

        endTime = System.currentTimeMillis() + 2000;
        while (endTime > System.currentTimeMillis()) {
            mShooter.wheelSpeed(0.60);
            mRetriever.arm(0.1);
            mVisionTracking.justTurret();
            SmartDashboard.putBoolean("loop2", true);
        }

        endTime = System.currentTimeMillis() + 2000;
        while (endTime > System.currentTimeMillis()) {
            mDrivetrain.arcade(0.15, 0);
            mRetriever.intake(-1);
            mVisionTracking.shoot(1);
            SmartDashboard.putBoolean("loop3", true);
        }

    }*/

    public void init(){
        startTime = System.currentTimeMillis();
    }

    public void sixBall() {

        if((System.currentTimeMillis() - startTime) < 1000 && (System.currentTimeMillis() - startTime) > 0){
            mShooter.turretSpeed(0.2);
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            SmartDashboard.putBoolean("loop1", true);
        }

        if((System.currentTimeMillis() - startTime) < 3000 && (System.currentTimeMillis() - startTime) > 1000){
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mVisionTracking.justTurret();
            SmartDashboard.putBoolean("loop2", true);
        }

        if((System.currentTimeMillis() - startTime) < 4000 && (System.currentTimeMillis() - startTime) > 3000){
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mRetriever.intake(-1);
            mVisionTracking.justTurret();
            mDrivetrain.arcade(-0.15,0);
            SmartDashboard.putBoolean("loop3", true);
        }

        if((System.currentTimeMillis() - startTime) < 6000 && (System.currentTimeMillis() - startTime) > 4000){
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mRetriever.intake(-1);
            mVisionTracking.justTurret();
            mDrivetrain.arcade(0,0);
            mBallHandler.delayedUnloadSet();
            SmartDashboard.putBoolean("loop4", true);
        }

        if((System.currentTimeMillis() - startTime) < 8500 && (System.currentTimeMillis() - startTime) > 6000){
            mDrivetrain.arcade(-0.15,0);
            mRetriever.intake(-1);
            mVisionTracking.shoot(1);
            mBallHandler.delayedUnload(1);
            SmartDashboard.putBoolean("loop5", true);
        }

        if((System.currentTimeMillis() - startTime) > 8500){
            mDrivetrain.arcade(0, 0);
            mRetriever.intake(0);
            mBallHandler.load(0);
            mShooter.turretSpeed(0);
            mShooter.wheelSpeed(0);
        }

        SmartDashboard.putNumber("HUMBER AUTO TIME", (System.currentTimeMillis() - startTime));
    }

    public void sixBallOLD(){

        /*
        endTime = System.currentTimeMillis() + 1000;
        while (endTime > System.currentTimeMillis() && RobotState.isAutonomous()) {
            mShooter.turretSpeed(0.2);
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            SmartDashboard.putBoolean("loop1", true);
        }

        endTime = System.currentTimeMillis() + 2000;
        while (endTime > System.currentTimeMillis() && RobotState.isAutonomous()) {
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mVisionTracking.justTurret();
            SmartDashboard.putBoolean("loop2", true);
        }

        endTime = System.currentTimeMillis() + 1000;
        while (endTime > System.currentTimeMillis() && RobotState.isAutonomous()) {
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mRetriever.intake(-1);
            mVisionTracking.justTurret();
            mDrivetrain.arcade(-0.15,0);
        }

        endTime = System.currentTimeMillis() + 2000;
        while (endTime > System.currentTimeMillis() && RobotState.isAutonomous()) {
            mShooter.wheelSpeed(0.90);
            mRetriever.arm(0.1);
            mRetriever.intake(-1);
            mVisionTracking.justTurret();
            mDrivetrain.arcade(0,0);
            mBallHandler.delayedUnloadSet();
        }

        endTime = System.currentTimeMillis() + 2500;
        while(endTime>System.currentTimeMillis() && RobotState.isAutonomous()){
            mDrivetrain.arcade(-0.15,0);
            mRetriever.intake(-1);
            mVisionTracking.shoot(1);
            mBallHandler.delayedUnload(1);
            SmartDashboard.putBoolean("loop3", true);
        }
        */
    }

}
