/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class Ryan {

    private AutoCommands mAutoCommands;
    private AHRS ahrs;
    private Drivetrain mDrivetrain;
    
    public Ryan (AutoCommands mAutoCommands, AHRS ahrs, Drivetrain mDrivetrain){
        this.mAutoCommands = mAutoCommands;
        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;

        }
    

    public void S1HighHome(){

        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 190"
        mAutoCommands.straightDrive(190, 2, 2000, 5000);

        ahrs.reset();
        mDrivetrain.resetEncoders();
        //turn 45
        mAutoCommands.gyroTurn(45, 2, 1000, 3000); 
        
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 123"
        mAutoCommands.straightDrive(123, 2, 1000, 3000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //turn 45
        mAutoCommands.gyroTurn(45, 2, 1000, 3000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 36"
        mAutoCommands.straightDrive(36, 2, 500, 1500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //Pickup ball
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //drive 12" backwards
        mAutoCommands.straightDrive(-12, 2, 500, 1500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //Shoot Balls
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //drive 240"
        mAutoCommands.straightDrive(240, 2, 2000, 5000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //turn 44 
        mAutoCommands.gyroTurn(44, 2, 1000, 1500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 80"
        mAutoCommands.straightDrive(80, 2, 2000, 5000);
    }

    public void S1highRendezvous(){
        ahrs.reset();
        mDrivetrain.resetEncoders();

        //turn 10
        mAutoCommands.gyroTurn(10, 2, 1000, 1500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 320
        mAutoCommands.straightDrive(320, 2, 2500, 5000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //Shoot Balls
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //turn 12.65
        mAutoCommands.gyroTurn(-12.65, 2, 1500, 3000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 134
        mAutoCommands.straightDrive(134, 2, 2000, 5000);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //turn 90
        mAutoCommands.gyroTurn(90, 2, 1000, 2500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
        //drive 30
        mAutoCommands.straightDrive(30, 2, 1000, 2500);
        ahrs.reset();
        mDrivetrain.resetEncoders();
    }
}