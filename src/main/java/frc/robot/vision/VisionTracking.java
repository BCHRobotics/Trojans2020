/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class VisionTracking {

    private double tv,tx,thor,spin;
    private double range = 1.75;

    private Shooter mShooter;

    public VisionTracking(Shooter mShooter){

        this.mShooter = mShooter;

    }
    
    public void turretFun(double speed){

        if(tv == 1){
            if(tx > range || tx < -range){
                mShooter.turretSpeed(tx * spin);
            }else{
                mShooter.turretSpeed(0);
                mShooter.wheelSpeed(1/thor * 27.5);
            }
        }else{
            mShooter.turretSpeed(0);
        }
    }
    public void periodic(){

        tv = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tv").getDouble(0);
        tx = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tx").getDouble(0);
        thor = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("thor").getDouble(0);

        SmartDashboard.putNumber("tv",tv);
        SmartDashboard.putNumber("tx",tx);
        SmartDashboard.putNumber("thor",thor);
    }

}
