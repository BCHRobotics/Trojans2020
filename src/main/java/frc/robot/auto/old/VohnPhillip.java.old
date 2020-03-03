/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

/*****
 * 
 * 
 * @author Vohn and Phillip 
 */
public class VohnPhillip {
    private AutoCommands mAutoCommands;

    public VohnPhillip(AutoCommands mAutoCommands){
        this.mAutoCommands = mAutoCommands;
    }

    /**
     * start position 1, shooting low, go to trench run
     * 1. reset everything
     * 2. move 1 foot forward
     * 3. turn -20.36 degrees to face the  point from which we shoot
     * 4. reset the gyros
     * 5. drive 242.19 inches forward
     * 6. reset the encoders
     * 7. turn -70.71 degrees to face the target
     * 8. reset the gyros
     * 9. shoot the balls (try to sleep)
     * 10. turn 156.6 degrees to face the trench
     * 11. reset gyros
     * 12. drive 168.49 inches and stop one foot in front of the trench
     * 13. reset the encoders
     * 14. turn 33.4 degrees to face the trench
     * 15. reset gyros
     */
    public void S1LowTrench(){

        //reseting everything
        
        
        //move forward 1 foot
        mAutoCommands.straightDrive(12, 2, 500, 500);
        //turn towards shooting position
        mAutoCommands.gyroTurn(-20.36, 2, 500, 1000);
        //reseting gyros
        
        //drive to shooting position
        mAutoCommands.straightDrive(230, 2, 500, 5000);
        //reset encoders
        
        //turn towards target
        mAutoCommands.gyroTurn(-70, 2, 500, 1000);
        //reseting gyros
        
        //shoot balls
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Dont sleep
        }



        //turn to trench run
        mAutoCommands.gyroTurn(156.6, 2, 500, 2000);
        //reseting gyros
        
        //drive to trench run (we stop 1 foot in front of trench)
        mAutoCommands.straightDrive(168.49, 0.5, 500, 3000);
        //reset encoders
        
        //turn to face trench
        mAutoCommands.gyroTurn(33.4, 2, 500, 1000);
        //reseting gyros
        

    }

    /**
     * 1. reset gyros and encoders
     * 2. drive 1 foot forward
     * 3. reset encoders
     * 4. turn -11.86 degrees to face shooting position
     * 5. reset gyros
     * 6. drive 233.58 inches to the shooting position
     * 7. reset encoders
     * 8. turn 78.14 degrees towards target
     * 9. reset gyros
     * 10. shoot balls (try to sleep)
     * 11. turn -159.47 degrees to face the rvp
     * 12. reset gyros
     * 13. drive 187.75 inches towards the rvp and stop 1 foot away
     * 14. reset encoders
     * 15. rotatev-20.53 degrees to face the rvp
     * 16. reset gyros
     */
    public void S1LowRendezvous(){

        //reseting everything
        
        
        //drive forward 1 foot
        mAutoCommands.straightDrive(12, 0.5, 500, 1000);
        //reset encoders
        
        //turn towards shooting position
        mAutoCommands.gyroTurn(11.86, 2, 500, 1000);
        //reseting gyros
        
        //drive to shooting position
        mAutoCommands.straightDrive(233.58, 0.5, 500, 5000);
        //reset encoders
        
        //turn towards target
        mAutoCommands.gyroTurn(78.14, 2, 500, 1000);
        //reseting gyros
        
        //shoot balls
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Dont sleep
        }
        //turn to rvp
        mAutoCommands.gyroTurn(-159.47, 2, 500, 3000);
        //reseting gyros
        
        //drive to rvp (we stop 1 foot in front of rvp)
        mAutoCommands.straightDrive(187.75, 0.5, 500, 4000);
        //reset encoders
        
        //turn to face rvp
        mAutoCommands.gyroTurn(-20.53, 2, 500, 1000);
        //reseting gyros
        

    }

    /**
     * 1. reset encoders and gyros
     * 2. drive 80 inches to the point from which we shoot
     * 3. reset encoders
     * 4. shoot the balls (try to sleep)
     * 5. turn -160.13 degrees to face the rvp
     * 6. reset gyros
     * 7. drive 193.7 inches towards the rendezvous point
     * 8. reset encoders
     * 9. turn -19.87 degrees to face the RVP
     * 10. reset gyros
     */
    public void S2LowRVP(){

        //reseting everything
        
        
        //drive to shooting position
        mAutoCommands.straightDrive(80, 0.5, 500, 3000);
        //reset encoders
        
        //shoot balls
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Dont sleep
        }
        //turn to rvp
        mAutoCommands.gyroTurn(-160.13, 2, 500, 3000);
        //reseting gyros
        
        //drive to rvp (we stop 1 foot in front of rvp)
        mAutoCommands.straightDrive(193.7, 0.5, 500, 4000);
        //reset encoders
        
        //turn to face rvp
        mAutoCommands.gyroTurn(-19.87, 2, 500, 1000);
        //reseting gyros
        

    }

    /**
     * 1. reset encoders and gyros
     * 2. drive forward 1 foot so that we can turn.
     * 3. reset encoders
     * 4. turn 44.06 degrees to face the shooting position
     * 5. reset gyros
     * 6. drive 115 inches to stop at the shooting position
     * 7. reset encoders
     * 8. turn 55.84 degrees to face shooting position
     * 9. reset gyros
     * 10. shoot (try to sleep)
     * 11. turn 148.53 degrees to face the trench
     * 12. reset gyros
     * 13. drive 181.3 inches to the trench (we stop 1 foot away)
     * 14. reset encoders
     * 15. turn 31.47 degrees to face the trench
     * 16. reset gyros
     * 
     * *Will probably not be able to fit this part of the code into the Autonomous due to the 15 second limit*
     * 
     * 17. drive 240 inches to go through the trench
     * 18. reset encoders
     * 19. turn 41.88 degrees to face the loading bay
     * 20. reset gyros
     * 21. drive 80.23 inches towards home/loading bay. we aren't allowed to cross the initiation line
     * 22. reset encoders
     * 23. turn -41.88 degrees to face the loading bay
     * 24. reset gyros
     */
    public void S3LowHome(){

        //reseting everything
        
        
        //drive 1 foot forward so that we can turn
        mAutoCommands.straightDrive(12, 2, 500, 500);
        //reset encoders
        
        //face shooting position
        mAutoCommands.gyroTurn(44.06, 2, 500, 1000);
        //reseting gyros
        
        //drive to shooting position
        mAutoCommands.straightDrive(115, 2, 500, 1000);
        //reset encoders
        
        //face the target
        mAutoCommands.gyroTurn(55.84, 2, 500, 1000);
        //reseting gyros
        
        //shoot balls
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Dont sleep
        }
        //turn to trench
        mAutoCommands.gyroTurn(148.53, 2, 500, 2000);
        //reseting gyros
        
        //drive to trench (we stop 1 foot away)
        mAutoCommands.straightDrive(181.3, 2, 500, 2000);
        //reset encoders
        
        //face trench
        mAutoCommands.gyroTurn(31.47, 2, 500, 2000);
        //reseting gyros
        
        //drive through trench
       /* we might not be able to fit this 
        mAutoCommands.straightDrive(240, -0.004, 0.005, 2, 500, 4000);
        //reset encoders
        
        //turn towards home
        mAutoCommands.gyroTurn(41.88, 0.5, 2, 500, 1000);
        //reseting gyros
        
        //drive towards home (we arent allowed to cross initiation line)
        mAutoCommands.straightDrive(80.23, -0.004, 0.005, 2, 500, 3000);
        //reset encoders
        
        //turn towards loading bay
        mAutoCommands.gyroTurn(-41.88, 0.5, 2, 500, 1000);   
        //reseting gyros
         */
    }
    
    /**
     * 1. reset encoders and gyros
     * 2. drive 1 foot forward
     * 3. reset encoders
     * 4. turn -82.68 degrees to face the shooting position
     * 5. reset gyros
     * 6. drive 123.64 inches toward the shooting position
     * 7. reset encoders
     * 8. turn 151.21 degrees to face the target
     * 9. reset gyros
     * 10. shoot (try to sleep)
    */
    public void S3HighTrench(){

        //reseting everything
        
        
        //drive 1 foot  to be able to turn
        mAutoCommands.straightDrive(12, 2, 500, 500);
        //reset encoders
        
        //turn to shooting position
        mAutoCommands.gyroTurn(-82.68, 2, 500, 2000);
        //reseting gyros
        
        //drive to shooting position
        mAutoCommands.straightDrive(123.64, 2, 500, 4000);
        //reset encoders
        
        //turn towards target
        mAutoCommands.gyroTurn(155.21, 2, 500, 2000);
        //reset gryos
        
        //shoot balls
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Dont sleep
        }
    }
}