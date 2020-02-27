/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.Drivetrain;
import frc.robot.vision.VisionTracking;

    /**
    * @author Ayrton Mercer. 
    */
    public class Ayrton {

    private AutoCommands mAutoCommands;
    private AHRS ahrs;
    private Drivetrain mDrivetrain;
    private VisionTracking mVisionTracking;

    public Ayrton(AutoCommands mAutoCommands, AHRS ahrs, Drivetrain mDrivetrain, VisionTracking mVisionTracking){
        this.mAutoCommands = mAutoCommands;
        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;
        this.mVisionTracking = mVisionTracking;
    }
 
    /**
    * 1: Drive straight 12 inches.
    * 2: Turn 12 degrees. 
    * 3: Drive straight 221.8 inches.
    * 4: Turn 78 degrees 
    * 5: Shoot the 3 balls.
    * 6: Turn 156.6 degrees.
    * 7: Drive straight 48 inches.
    * 8:Turn another 28 degrees.
    * 9: Drive straight 240 inches.
    * 10: Turn 41.9 degrees.
    * 11: Drive straight 80 inches.
    * 12: Turn 48 degrees.  
    */
    public void start1LowHome(){
        
        ahrs.reset();
        mDrivetrain.resetEncoders();

        //Pause robot for 1 sec
        try{
            Thread.sleep(1000);
        }  catch(Exception e){
            //Do Nothing
        }

        mAutoCommands.straightDrive(12, 2, 200, 1500);
        mAutoCommands.gyroTurn(12, 2, 125, 1500);
        mAutoCommands.straightDrive(221.8, 2, 200, 1500);
        mAutoCommands.gyroTurn(78, 2, 125, 1500);
        
        //Shoot Now
        mVisionTracking.shoot(0.5, 1000);

        mAutoCommands.gyroTurn(156.6, 2, 125, 1500);
        mAutoCommands.straightDrive(48, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 1, 125, 500);
        mAutoCommands.straightDrive(240, 125, 200, 1000);
        mAutoCommands.gyroTurn(41.9, 2, 125, 1500);
        mAutoCommands.straightDrive(80, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 2, 125, 1000);
    }

    /**
    * 1: Drive straight -48 inches.
    * 2: Shoot the 3 balls.
    * 3: Turn 66 degrees.
    * 4: Drive straight 48 inches.
    * 5:Turn another 28 degrees.
    * 6: Drive straight 240 inches.
    * 7: Turn 41.9 degrees.
    * 8: Drive straight 80 inches.
    * 9: Turn 48 degrees. 
    */
    public void start2HighHome(){

        ahrs.reset();
        mDrivetrain.resetEncoders();
      
        mAutoCommands.straightDrive(-48, 2, 200, 2500);

        //Shoot Now
        mVisionTracking.shoot(0.5, 3000);

        mAutoCommands.gyroTurn(66, 2, 125, 1500);
        mAutoCommands.straightDrive(48, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 1, 125, 500);
        mAutoCommands.straightDrive(240, 125, 200, 1000);
        mAutoCommands.gyroTurn(41.9, 2, 125, 1500);
        mAutoCommands.straightDrive(80, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 2, 125, 1000);
    }

    /**
    * 1: Drive straight -80 inches.
    * 2: Shoot the 3 balls.  
    * 3: Turn 66.6 degrees.
    * 4: Drive straight 168.5 inches.
    * 5: Turn 23.4 degrees 
    */
    public void start2LowTrench(){

        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.straightDrive(-80, 2, 200, 2500);

        //Shoot Now
        mVisionTracking.shoot(0.5, 3000);
        
        mAutoCommands.gyroTurn(66.6, 2, 125, 2000);
        mAutoCommands.straightDrive(168.5, 2, 200, 2500);
        mAutoCommands.gyroTurn(23.4, 2, 100, 500);
    }

    /** 
    * 1: Drive straight -80 inches.
    * 2: Shoot the 3 balls.
    * 3: Turn 66.6 degrees.
    * 4: Drive straight 48 inches.
    * 5:Turn another 28 degrees.
    * 6: Drive straight 240 inches.
    * 7: Turn 41.9 degrees.
    * 8: Drive straight 80 inches.
    * 9: Turn 48 degrees. 
    */
    public void start2LowHome(){

        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.straightDrive(-80, 2, 200, 2500);

        //Shoot Now
        mVisionTracking.shoot(0.5, 3000);

        mAutoCommands.gyroTurn(66.6, 2, 125, 1500);
        mAutoCommands.straightDrive(48, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 1, 125, 500);
        mAutoCommands.straightDrive(240, 125, 200, 1000);
        mAutoCommands.gyroTurn(41.9, 2, 125, 1500);
        mAutoCommands.straightDrive(80, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 2, 125, 1000);
    }

    /** 
    * 1: Drive straight 12 inches.
    * 2: Turn 44 degrees.
    * 3: Drive straight 115 inches. 
    * 4: Turn 46 degrees.
    * 5: Shoot the 3 balls.
    * 6: Turn 61 degrees
    * 7: Drive straight 188 inches
    * 8: Turn 29 degrees
    */
    public void start3LowRendezVous(){
       
        ahrs.reset();
        mDrivetrain.resetEncoders();

        //Pause for 1 sec
        try{
            Thread.sleep(1000);
        }  catch(Exception e){
            //Do Nothing
        }
        
        mAutoCommands.straightDrive(12, 2, 200, 1500);
        mAutoCommands.gyroTurn(44, 0.5, 200, 1000);
        mAutoCommands.straightDrive(115, 2, 200, 1500);
        mAutoCommands.gyroTurn(46, 0.5, 200, 1000);

        //Shoot Now
        mVisionTracking.shoot(0.5, 3000);

        mAutoCommands.gyroTurn(61, 3, 200, 1000);
        mAutoCommands.straightDrive(188, 2, 200, 2000);
        mAutoCommands.gyroTurn(29, 3, 100, 500);
    }
}
