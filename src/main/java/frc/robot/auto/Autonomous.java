/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallHandler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Retriever;
import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class Autonomous {

    private static final String kDefaultAuto = "Default";

    private static final String kstart1HighRDV = "Start 1, Shoot High, End Rendez-vous";
    private static final String kstart1HighTrench = "Start 1, Shoot High, End Trench";
    private static final String kstart1HighHome = "Start 1, Shoot High, End Home";
    private static final String kstart1LowRDV = "Start 1, Shoot Low, End Rendez-vous";
    private static final String kstart1LowTrench = "Start 1, Shoot Low, End Trench";
    private static final String kstart1LowHome = "Start 1, Shoot Low, End Home";

    private static final String kstart2HighRDV = "Start 2, Shoot High, End Rendez-vous";
    private static final String kstart2HighTrench = "Start 2, Shoot High, End Trench";
    private static final String kstart2HighHome = "Start 2, Shoot High, End Home";
    private static final String kstart2LowRDV = "Start 2, Shoot Low, End Rendez-vous";
    private static final String kstart2LowTrench = "Start 2, Shoot Low, End Trench";
    private static final String kstart2LowHome = "Start 2, Shoot Low, End Home";

    private static final String kstart3HighRDV = "Start 3, Shoot High, End Rendez-vous";
    private static final String kstart3HighTrench = "Start 3, Shoot High, End Trench";
    private static final String kstart3HighHome = "Start 3, Shoot High, End Home";
    private static final String kstart3LowRDV = "Start 3, Shoot Low, End Rendez-vous";
    private static final String kstart3LowTrench = "Start 3, Shoot Low, End Trench";
    private static final String kstart3LowHome = "Start 3, Shoot Low, End Home";

    private String mAutoSelected;
    private final SendableChooser<String> mChooser = new SendableChooser<>();

    private VohnPhillip mVohnPhillip;
    private Ryan mRyan;
    private Ayrton mAyrton;
    private Matthew mMatthew;
    private Kamren mKamren;

    public Autonomous(Drivetrain mDrivetrain, Shooter mShooter, Retriever mRetriever, Climber mClimber, BallHandler mBallHandler, ColorWheel mColorWheel, AutoCommands mAutoCommands, AHRS ahrs){

        mVohnPhillip = new VohnPhillip(mAutoCommands, ahrs, mDrivetrain);
        mRyan = new Ryan(mAutoCommands, ahrs, mDrivetrain);
        mAyrton = new Ayrton(mAutoCommands, ahrs, mDrivetrain);
        mMatthew = new Matthew(mAutoCommands, ahrs, mDrivetrain);
        mKamren = new Kamren(mAutoCommands); //MISSING RESETS???

        mChooser.setDefaultOption("Default Auto", kDefaultAuto);

        mChooser.addOption("Start 1, Shoot High, End Rendez-vous", kstart1HighRDV);
        mChooser.addOption("Start 1, Shoot High, End Trench", kstart1HighTrench);
        mChooser.addOption("Start 1, Shoot High, End Home", kstart1HighHome);
        mChooser.addOption("Start 1, Shoot Low, End Rendez-vous", kstart1LowRDV);
        mChooser.addOption("Start 1, Shoot Low, End Trench", kstart1LowTrench);
        mChooser.addOption("Start 1, Shoot Low, End Home", kstart1LowHome);

        mChooser.addOption("Start 2, Shoot High, End Rendez-vous", kstart2HighRDV);
        mChooser.addOption("Start 2, Shoot High, End Trench", kstart2HighTrench);
        mChooser.addOption("Start 2, Shoot High, End Home", kstart2HighHome);
        mChooser.addOption("Start 2, Shoot Low, End Rendez-vous", kstart2LowRDV);
        mChooser.addOption("Start 2, Shoot Low, End Trench", kstart2LowTrench);
        mChooser.addOption("Start 2, Shoot Low, End Home", kstart2LowHome);

        mChooser.addOption("Start 3, Shoot High, End Rendez-vous", kstart3HighRDV);
        mChooser.addOption("Start 3, Shoot High, End Trench", kstart3HighTrench);
        mChooser.addOption("Start 3, Shoot High, End Home", kstart3HighHome);
        mChooser.addOption("Start 3, Shoot Low, End Rendez-vous", kstart3LowRDV);
        mChooser.addOption("Start 3, Shoot Low, End Trench", kstart3LowTrench);
        mChooser.addOption("Start 3, Shoot Low, End Home",kstart3LowHome);

        SmartDashboard.putData("Auto choices", mChooser);
        SmartDashboard.putNumber("gyro", -1);
    }

    public void init(){
        mAutoSelected = mChooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + mAutoSelected);
    }

    /**
     * MISSING CODES:
     * 
     * Start 1 High Trench
     * Start 2 High RDV
     * Start 3 High RDV
     */
    public void periodic(){
        switch (mAutoSelected) {
            case kstart1HighRDV:
                mRyan.S1highRendezvous();
                break;
            case kstart1HighTrench:

                break;
            case kstart1HighHome:
                mRyan.S1HighHome();
                break;
            case  kstart1LowRDV:
                mVohnPhillip.S1LowRendezvous();
                //mMatthew.start2LowRVP();
                break;
            case kstart1LowTrench:
                mVohnPhillip.S1LowTrench();
                break;
            case kstart1LowHome:
                mAyrton.start1LowHome();
                break;
            case kstart2HighRDV:
                
                break;
            case kstart2HighTrench:
                mMatthew.start2HighTrench();
                //mMatthew.start1HighTrenchN();
                break;
            case kstart2HighHome:
                mAyrton.start2HighHome();
                //mKamren.start2HighHome();
                break;
            case kstart2LowRDV:
                mVohnPhillip.S2LowRVP();
                break;
            case kstart2LowTrench:
                mAyrton.start2LowTrench();
                break;
            case kstart2LowHome:
                mAyrton.start2LowHome();
                break;
            case kstart3HighRDV:

                break;
            case kstart3HighTrench:
                mVohnPhillip.S3HighTrench();
                break;
            case kstart3HighHome:
                mKamren.start3HighHome();
                break;
            case kstart3LowRDV:
                mAyrton.start3LowRendezVous();
                break;
            case kstart3LowTrench:
                mKamren.start3LowTrench();
                break;
            case kstart3LowHome:
                mVohnPhillip.S3LowHome();
                break;
            case kDefaultAuto:
            default:
                // Put default auto code here
                break;
          }
    }

}
