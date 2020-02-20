/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Operator input for the robot
 */
public class OI {

    // Initiate the controllers to USB ports
    public Joystick drivestick = new Joystick(RobotMap.OI_DRIVESTICK_USB);
    public Joystick funstick = new Joystick(RobotMap.OI_FUNSTICK_USB);
    public Joystick prostick = new Joystick(RobotMap.OI_PROSTICK_USB);
    public Joystick teststick = new Joystick(RobotMap.OI_TESTSTICK_USB);

    // Drivestick Buttons
    public JoystickButton buttonTurbo = new JoystickButton(drivestick, RobotMap.BTN_DRIVESTICK_TURBO);
    public JoystickButton buttonSnail = new JoystickButton(drivestick, RobotMap.BTN_DRIVESTICK_SNAIL);

    // funStick Buttons
    public JoystickButton buttonVision = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_VISION);
    public JoystickButton buttonShoot = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_SHOOT);

    public JoystickButton buttonAutoWheel = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_AUTOWHEEL);
    public JoystickButton buttonWheelIn = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_WHEELIN);
    public JoystickButton buttonWheelOut = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_WHEELOUT);

    public JoystickButton buttonChangeMode = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_CHANGEMODE);

    public JoystickButton buttonClimbLock = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_CLIMBLOCK);
    public JoystickButton buttonClimbUnlock = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_CLIMBUNLOCK);

    public JoystickButton buttonRatchetLock = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_RATCHETLOCK);
    public JoystickButton buttonRatchetUnlock = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_RATCHETUNLOCK);

    public JoystickButton buttonRetriverUp = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_RETRIEVERUP);
    public JoystickButton buttonRetriverDown = new JoystickButton(funstick, RobotMap.BTN_FUNSTICK_RETRIEVERDOWN);

    // prostick buttons
    public JoystickButton buttonUnload = new JoystickButton(prostick, RobotMap.BTN_PROSTICK_UNLOAD);
}
