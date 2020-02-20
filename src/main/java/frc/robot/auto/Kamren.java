/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

public class Kamren {

    private AutoCommands mAutoCommands;

    public Kamren(AutoCommands mAutoCommands){
        this.mAutoCommands = mAutoCommands;
    }

    public void start3LowTrench(){
        mAutoCommands.straightDrive(12, 2, 250, 1700);
        mAutoCommands.gyroTurn(43, 3, 250, 1700);
        mAutoCommands.straightDrive(200, 2, 250, 1700);
        //shoot
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Dont Sleep
        }

        mAutoCommands.gyroTurn(71, 3, 250, 1700);
        mAutoCommands.straightDrive(211, 2, 250, 1700);
        
    }
    
    public void start2HighHome(){
        //drive towardsgoal
        mAutoCommands.straightDrive(12, 2, 250, 1700);
        mAutoCommands.gyroTurn(45, 3, 250, 1700);  
        mAutoCommands.straightDrive(87, 2, 250, 1700);
        mAutoCommands.gyroTurn(45, 3, 250, 3750); 
        mAutoCommands.straightDrive(48, 2, 250, 1700);  
        mAutoCommands.gyroTurn(180, 3, 250, 1700);
        //shoot
        try{
            Thread.sleep(1000);
        } catch(Exception e){
            //Dont Sleep
        }
        //turn towards trench
        mAutoCommands.gyroTurn(180, 3, 250, 1700);
        mAutoCommands.straightDrive(240, 2, 250, 1700);
        mAutoCommands.gyroTurn(42, 3, 250, 1700);
        mAutoCommands.straightDrive(80, 2, 250, 1400);
    }

    public void start3HighHome(){
        mAutoCommands.straightDrive(12, 2, 250, 1700);
        mAutoCommands.gyroTurn(82, 3, 250, 1700);
        mAutoCommands.straightDrive(111, 2, 250, 1700);
            //shoot
            try{
                Thread.sleep(1000);
            } catch(Exception e){
                //Dont Sleep
            }
            //turn towards trench
        mAutoCommands.gyroTurn(180, 3, 250, 1700);
        mAutoCommands.straightDrive(220, 2, 250, 1700);
        mAutoCommands.gyroTurn(42, 3, 250, 1700);
        mAutoCommands.straightDrive(80, 2, 250, 1400);
    }
}