/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

public class Matthew {

    private AutoCommands mAutoCommands;

    public Matthew(AutoCommands mAutoCommands){
        this.mAutoCommands = mAutoCommands;
    }

    /**
     * 1: Turn 54.09 clockwise
     * 2: Drive 173.69 inches
     * 3: Pick up the balls
     * 4: Shoot
     * 5: Turn 15 degrees counter clockwise
     * 6: Drive backwards 15 inches
     */
    public void start2HighTrench(){

        
        

        mAutoCommands.gyroTurn(54.09, 3, 250, 1500);

        mAutoCommands.straightDrive(173.69, 3, 1000, 3000);

        //Pick up balls

        //shoot now 
        try{
            Thread.sleep(1000);
            } catch(Exception e){
            //dont sleep    
       }

        
        


        mAutoCommands.gyroTurn(-15, 3, 250, 1500);

        mAutoCommands.straightDrive(-12, 3, 1000, 3000);

    }
  /**
    1: Drive 1 foot
    2: Turn 22.54 degrees clockwise 
    3: Drive 319.93494 inches
    4: Pick up the balls
    5: turn 67.46 degrees counter clockwise
    6: Drive backwards 1 foot
    7: Shoot
     */
    public void start1HighTrench(){

        
        

        mAutoCommands.straightDrive(12, 3, 1000, 3000);

        mAutoCommands.gyroTurn(22.54, 3, 500, 1000);

        
        

        mAutoCommands.straightDrive(319.93494, 3, 4000, 7000);
        
        //Pick up ball 

        mAutoCommands.gyroTurn(-67.46, 3, 500, 1000);

        

        mAutoCommands.straightDrive(-12, 3, 3000, 5000);

        
        

        //shoot now 
     try{
        Thread.sleep(1000);
        } catch(Exception e){
        //dont sleep    
        }
       
    }

       /**
     * Start: Baseline facing the generator
     * 1: Drive 80 inches
     * 2: Shoot
     * 3: Rotate 122 degrees counter clockwise  
     * 4: Drive 170 inches
     * 5: Rotate 20 degrees clockwise 
     */
    public void start2LowRVP(){
        
        

        mAutoCommands.straightDrive(80, 3, 1000, 3000);

        
        

        //shoot now 
        try{
            Thread.sleep(1000);
            } catch(Exception e){
            //dont sleep    
            }

        mAutoCommands.gyroTurn(-122, 3, 500, 2000);

        
        

        mAutoCommands.straightDrive(170, 3, 1000, 3000);

        
        

        mAutoCommands.gyroTurn(20, 3, 250, 2000);                    
    }


  }


