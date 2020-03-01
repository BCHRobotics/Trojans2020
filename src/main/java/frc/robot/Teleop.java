/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;
import frc.robot.subsystems.Shooter;
import frc.robot.vision.VisionTracking;

/**
 * Takes the user input from the controllers and applies it to each of the subsystems
 */
public class Teleop {

    private OI mOi;
    private Drivetrain mDrivetrain;
    private Shooter mShooter;
    private Retriever mRetriever;
    private Climber mClimber;
    private BallHandler mBallHandler;
    //private ColorWheel mColorWheel;
    private VisionTracking mVisionTracking;

    /**
     * Creates a new Teleop
     * 
     * @param mOi Robot.java mOi instance
     * @param mDrivetrain Robot.java mDrivetrain instance
     */
    public Teleop(OI mOi, Drivetrain mDrivetrain, Shooter mShooter, BallHandler mBallHandler, VisionTracking mVisionTracking, Climber mClimber, Retriever mRetriever){
        this.mOi = mOi;
        this.mDrivetrain = mDrivetrain;
        this.mShooter = mShooter;
        this.mRetriever = mRetriever;
        this.mClimber = mClimber;
        this.mBallHandler = mBallHandler;
        //this.mColorWheel = mColorWheel;
        this.mVisionTracking = mVisionTracking;
    }

    // initiate drive stick variables
    private double y = 0, turn = 0, speed = 0;
    private double intakeSpeed = 0;
    private double shooterSpeed = 0;

    private double[] shooterSpeeds = {0,50,55,60,65,70,75,80};

    /**
     * Drivestick teleop control. Once called it will let you drive.
     */
    public void drivestick(){

        // sets the default speed to 75%
        speed = 0.75;
        
        // Change the speed depending on snail 50%, turbo 100%
        if(mOi.buttonSnail.get()) speed = 0.5;
        if(mOi.buttonTurbo.get()) speed = 1.0;

        //Get the y and turn speed after applying the deadzone
        y = deadzone(mOi.drivestick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY), 0.07, 0.07);
        turn = deadzone(mOi.drivestick.getRawAxis(RobotMap.OI_DRIVESTICK_TURN), 0.07, 0.07);
        
        mDrivetrain.arcade(y * speed, turn * speed * 0.7);

    }

    boolean climbMode = false;

    public void funstick(){

        if(climbMode){

            /**
             * NOTE!!!!!!!!!!!!!!!!!
             * 
             * MAKE THE SHOOTER HOME BEFORE CLIMBING
             */

            if(mOi.buttonChangeModeA.get() || mOi.buttonChangeModeB.get()){

                mOi.funstick.setRumble(RumbleType.kLeftRumble, 1);
                mOi.funstick.setRumble(RumbleType.kRightRumble, 1);

                //PLEASE DO NOT USE THIS FOR FINAL CODE (It is trash, stops code for 250 mills)
                try{
                    Thread.sleep(250);
                } catch(Exception e) {
                    System.out.println("UNABLE TO PAUSE FOR MODE CHANGE");
                }
                climbMode = false;

                mOi.funstick.setRumble(RumbleType.kLeftRumble, 0);
                mOi.funstick.setRumble(RumbleType.kRightRumble, 0);
            }

            //Lock Climber
            /*if(mOi.buttonClimberLock.get()){
                mClimber.lock();
            } else {
                mClimber.unlock();
            }*/

            //Sets the speed of the lift winch motor
            //mClimber.lift(deadzone(mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT), 0.07, 0.07));

            //Turn off eveything else
            mRetriever.intake(0);
            mShooter.turretSpeed(0);
            mShooter.wheelSpeed(0);

        } else {
            if(mOi.buttonChangeModeA.get() || mOi.buttonChangeModeB.get()){

                mOi.funstick.setRumble(RumbleType.kLeftRumble, 1);
                mOi.funstick.setRumble(RumbleType.kRightRumble, 1);

                //PLEASE DO NOT USE THIS FOR FINAL CODE (It is trash, stops code for 250 mills)
                try{
                    Thread.sleep(250);
                } catch(Exception e) {
                    System.out.println("UNABLE TO PAUSE FOR MODE CHANGE");
                }
                climbMode = true;

                mOi.funstick.setRumble(RumbleType.kLeftRumble, 0);
                mOi.funstick.setRumble(RumbleType.kRightRumble, 0);
            }

            if(mOi.buttonVision.get()){
                //Run vision code
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
                mVisionTracking.shoot(0.75);
                
            } else {
            
                //Manual shooter control
                //mShooter.turretSpeed(mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_TURRETTURN));
               
                //limelight LED turn off - 3 = force On 1 = Force Off
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

                mShooter.turretSpeed(mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_TURRETTURN));

                if(mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_SHOOTSPEED) <= 0.2){
                    mShooter.wheelSpeed(-mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_SHOOTSPEED));
                } else {

                    //Shooter speeds arround dpad
                    switch(mOi.funstick.getPOV()){
                        case 0:
                            shooterSpeed = shooterSpeeds[0];
                        break;
                        case 45:
                            shooterSpeed = shooterSpeeds[1];
                        break;
                        case 90:
                            shooterSpeed = shooterSpeeds[2];
                        break;
                        case 135:
                            shooterSpeed = shooterSpeeds[3];
                        break;
                        case 180:
                            shooterSpeed = shooterSpeeds[4];
                        break;
                        case 225:
                            shooterSpeed = shooterSpeeds[5];
                        break;
                        case 270:
                            shooterSpeed = shooterSpeeds[6];
                        break;
                        case 315:
                            shooterSpeed = shooterSpeeds[7];
                        break;
                    }

                    if(mOi.buttonStopShooter.get()){
                        shooterSpeed = 0;
                    }
    
                    mShooter.wheelSpeed(shooterSpeed);

                } 
                
                if(mOi.buttonShoot.get()){
                    mBallHandler.unload(0.75);
                } else {
                    mBallHandler.load(0);
                }
            }

            //Intake Control
            intakeSpeed = mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_INTAKEOUT) - mOi.funstick.getRawAxis(RobotMap.OI_FUNSTICK_INTAKEIN);
            SmartDashboard.putNumber("INTAKE:", intakeSpeed);
            mRetriever.intake(intakeSpeed);

            //Intake up/down
            /* DISABLED FOR SAFTEY
            if(mOi.buttonRetriverDown.get()){
                mRetriever.lower(0.25);
            } else if(mOi.buttonRetriverUp.get()){
                mRetriever.raise(0.25);
            } else {
                mRetriever.arm(0);
            }*/

            //Stop Climbing
            //mClimber.unlock();
            //mClimber.lift(0);
            
        }

        //Print out modes
        SmartDashboard.putBoolean("CLIMB MODE", climbMode);

        //Print shooter speed for drivers
        SmartDashboard.putNumber("Tele: Shooter Speed", shooterSpeed);
    }

    public void teststick(){

        mRetriever.arm(mOi.teststick.getRawAxis(1) * 0.25);

    }

    /**
     * If the value is within the deazone then the robot will not move
     * 
     * @param input Raw joystick value
     * @param deadzonePos positive deadzone of the joystick
     * @param deadzoneNeg negative deadzone of the joystick
     * @return Corrected value of the input so there is no false negatives
     */
    private double deadzone(double input, double deadzonePos, double deadzoneNeg){
        if(input < deadzonePos && input > -deadzoneNeg) input = 0;
        return input;
    }

}