/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

/**
 * Test Auto for systems 
 */
public class TestAuto {

    private AutoCommands mAutoCommands;
    private AHRS ahrs;
    private Drivetrain mDrivetrain;

    public TestAuto(AutoCommands mAutoCommands, AHRS ahrs, Drivetrain mDrivetrain){
        this.mAutoCommands = mAutoCommands;
        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;
    }

    public void encoderTest(){
        SmartDashboard.putBoolean("startEncoderAuto", false);
        SmartDashboard.putBoolean("startGyroAuto", false);
        //mAutoCommands.encoderDrive(120, -0.004, 2, 250, 10000);
        mAutoCommands.straightDrive(120, 2, 250, 10000);
    }

    public void gyroTest(){
        SmartDashboard.putBoolean("startEncoderAuto", false);
        SmartDashboard.putBoolean("startGyroAuto", false);
        //mAutoCommands.gyroTurn(90, 0.005, 2, 250, 10000);

        mDrivetrain.resetEncoders();
        ahrs.reset();

        mAutoCommands.gyroTurn(0, 0, 0, 10);

        mAutoCommands.straightDrive(120, 2, 250, 10000);

        mDrivetrain.resetEncoders();
        ahrs.reset();

        mAutoCommands.gyroTurn(45, 2, 250, 10000);

        mDrivetrain.resetEncoders();
        ahrs.reset();

        mAutoCommands.straightDrive(120, 2, 250, 10000);
    }

}
