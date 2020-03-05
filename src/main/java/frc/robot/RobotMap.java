/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

    //MOTORS

    //Drive Train
    public static int SPARK_FRONTLEFT = 11;
    public static int SPARK_FRONTRIGHT = 15;
    public static int SPARK_BACKLEFT = 12;
    public static int SPARK_BACKRIGHT = 16;

    //Shooter
    public static int SPARK_SHOOTERTURRET = 21;
    public static int SPARK_SHOOTERWHEEL = 22;

    //BallHandler
    public static int TALON_BALLHANDLE0 = 30;
    public static int TALON_BALLHANDLE1 = 31;
    public static int TALON_BALLHANDLE2 = 32;
    public static int TALON_BALLHANDLE3 = 33;

    //Retriever
    public static int SPARK_ARM = 25;
    public static int TALON_BAR = 26;

    //Climber
    public static int LIFT_SOLENOID = 0;
    public static int LIFT_RATCHET_SOLENOID = 0;
    public static int SPARK_LIFT = 41;

    //Color Wheel
    public static int SPARK_COLOREXTEND = 51;
    public static int SPARK_COLORSPINNER = 52;

    /********************************** */
    
    //SENSORS
   
    //Ballhandler
    public static int DIO_BALLSENSE0 = 0;
    public static int DIO_BALLSENSE1 = 2;
    public static int DIO_BALLSENSE2 = 4;
    public static int DIO_BALLSENSE3 = 3;
    public static int DIO_BALLSENSE4 = 1;

    /********************************** */

    //Solinoids 
    public static int PCM_LEFTLOCK = 0;
    public static int PCM_RIGHTLOCK = 1;
    public static int PCM_RATCHET = 2;

    /********************************** */

    //CONTROLERS

    /**
     * AXIS
     * 
     * Left X: 0
     * Left Y: 1
     * Left Trigger: 2
     * Right Trigger: 3
     * Right X: 4
     * Right Y: 5
     * 
     * 
     * Buttons
     * 
     * A: 1
     * B: 2
     * X: 3
     * Y: 4
     * Left Trigger: 5
     * Right Trigger: 6
     * Back: 7
     * Start: 8
     * Left Stick: 9
     * Right Stick: 10
     * 
     */

    public static int OI_DRIVESTICK_USB = 0;
    public static int OI_FUNSTICK_USB = 1;
    public static int OI_PROSTICK_USB = 2;
    public static int OI_TESTSTICK_USB = 3;

    //DriveStick Axis
    public static int OI_DRIVESTICK_MOVEY = 1; //Left Y
    public static int OI_DRIVESTICK_TURN = 0; //Left X

    //DriveStick Buttons
    public static int BTN_DRIVESTICK_SNAIL = 5; //Left Trigger
    public static int BTN_DRIVESTICK_TURBO = 6; //Right Trigger
    
    //FunStick Axis
    public static int OI_FUNSTICK_TURRETTURN = 0; //Left X
    public static int OI_FUNSTICK_SHOOTSPEED = 1; //Left Y

    public static int OI_FUNSTICK_INTAKEIN = 3; //Right Trigger
    public static int OI_FUNSTICK_INTAKEOUT = 2; //Left Trigger

    public static int OI_FUNSTICK_MANUALWHEEL = 4; //Right X

    public static int OI_FUNSTICK_LIFT = 1; //Left Y

    //funstick buttons regular
    public static int BTN_FUNSTICK_VISION = 1; //A
    public static int BTN_FUNSTICK_SHOOT = 4; //Y
    public static int BTN_FUNSTICK_STOP_SHOOTER = 9; //Left Stick

    public static int BTN_FUNSTICK_AUTOWHEEL = 10; //Right Stick
    public static int BTN_FUNSTICK_WHEELIN = 3; //X
    public static int BTN_FUNSTICK_WHEELOUT = 2; //B

    public static int BTN_FUNSTICK_RETRIVER_UP = 5; //Left Trigger
    public static int BTN_FUNSTICK_RETRIVER_DOWN = 6; //Right Trigger

    //Funstick buttons climb
    public static int BTN_FUNSTICK_CHANGEMODEA = 7; //Back
    public static int BTN_FUNSTICK_CHANGEMODEB = 8; //Start

    public static int BTN_FUNSTICK_CLIMBLOCK = 3; //X
    public static int BTN_FUNSTICK_RATCHETLOCK = 1; //A

    //prostick button
    public static int BTN_PROSTICK_UNLOAD = 1; //Temp mapped 

    /********************************** */

    //PID
    public static double P_DRIVETRAIN = 1;
    public static double I_DRIVETRAIN = 0;
    public static double D_DRIVETRAIN = 0;

    public static double P_NAVX= 1;
    public static double I_NAVX = 0;
    public static double D_NAVX = 0;

    public static double P_TURRET = 1;
    public static double I_TURRET = 0;
    public static double D_TURRET = 0;

    public static double P_WHEEL = 1;
    public static double I_WHEEL = 0;
    public static double D_WHEEL = 0;






}
 