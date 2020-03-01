/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class VisionTracking {

    private double tv,tx,thor;
    private double range = 1.75;
    private double spin = 0.025;

    private boolean goodToShoot = false;

    private Shooter mShooter;
    private BallHandler mBallHandler;

    public VisionTracking(Shooter mShooter, BallHandler mBallHandler){
        this.mShooter = mShooter;
        this.mBallHandler = mBallHandler;
    }
    
    public void turretFun(){

        // limelight LED turn on
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        if(tv == 1){
            if(tx > range || tx < -range){
                mShooter.turretSpeed(tx * spin);
                mShooter.wheelSpeed(0.5);
                goodToShoot = false;
            }else{
                mShooter.turretSpeed(0);
                mShooter.wheelSpeed(1/thor * 32.5);// wheel spin rpm (1/60*210000)
                goodToShoot = true;
            }
        }else{
            mShooter.turretSpeed(0);
            mShooter.wheelSpeed(0);
            goodToShoot = false; 
        }
    }  

    public void shoot(double unloadSpeed, double shootTime){

        shootTime = shootTime + System.currentTimeMillis();

        while(shootTime < System.currentTimeMillis()){

            turretFun();

            if(goodToShoot){
                mBallHandler.unload(unloadSpeed);
            }
        }
    }

    public void shoot(double unloadSpeed){

        turretFun();

        if(goodToShoot){
                mBallHandler.unload(unloadSpeed);
        }
    }

    public void periodic(){

        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) + 1.87;
        thor = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0);

        SmartDashboard.putNumber("tv",tv);
        SmartDashboard.putNumber("tx",tx);
        SmartDashboard.putNumber("thor",thor);
    }

}
