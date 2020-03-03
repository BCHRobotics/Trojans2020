/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

/**
 * Add your docs here.
 */
public class Ryan {

    private AutoCommands mAutoCommands;
    
    public Ryan (AutoCommands mAutoCommands){
        this.mAutoCommands = mAutoCommands;
        }
    

    public void S1HighHome(){

        
        
        //drive 190"
        mAutoCommands.straightDrive(190, 2, 2000, 5000);

        
        
        //turn 45
        mAutoCommands.gyroTurn(45, 2, 1000, 3000); 
        
        
        
        //drive 123"
        mAutoCommands.straightDrive(123, 2, 1000, 3000);
        
        
        //turn 45
        mAutoCommands.gyroTurn(45, 2, 1000, 3000);
        
        
        //drive 36"
        mAutoCommands.straightDrive(36, 2, 500, 1500);
        
        
        //Pickup ball
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //drive 12" backwards
        mAutoCommands.straightDrive(-12, 2, 500, 1500);
        
        
        //Shoot Balls
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //drive 240"
        mAutoCommands.straightDrive(240, 2, 2000, 5000);
        
        
        //turn 44 
        mAutoCommands.gyroTurn(44, 2, 1000, 1500);
        
        
        //drive 80"
        mAutoCommands.straightDrive(80, 2, 2000, 5000);
    }

    public void S1highRendezvous(){
        
        

        //turn 10
        mAutoCommands.gyroTurn(10, 2, 1000, 1500);
        
        
        //drive 320
        mAutoCommands.straightDrive(320, 2, 2500, 5000);
        
        
        //Shoot Balls
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Don't Sleep
        }
        //turn 12.65
        mAutoCommands.gyroTurn(-12.65, 2, 1500, 3000);
        
        
        //drive 134
        mAutoCommands.straightDrive(134, 2, 2000, 5000);
        
        
        //turn 90
        mAutoCommands.gyroTurn(90, 2, 1000, 2500);
        
        
        //drive 30
        mAutoCommands.straightDrive(30, 2, 1000, 2500);
        
        
    }
}