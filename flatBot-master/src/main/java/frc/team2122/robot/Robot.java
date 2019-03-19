package frc.team2122.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private AHRS gyro;

  private Joystick driverJoystick = new Joystick(0);
  private Jaguar leftMotor = new Jaguar(1);
  private Jaguar rightMotor = new Jaguar(9);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    leftMotor.set(getLeftValue());
    rightMotor.set(getRightValue());
    System.out.println("Left Motor:  " + getLeftValue());
    System.out.println("RightMotor: " + getRightValue());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    System.out.println("Press top button to test gyro");

    if(driverJoystick.getTop) {
      double rate = gyro.getRate();
      double angle = gyro.getAngle();
      double calibrationOffset = gyro.getAngleAdjustment();
      System.out.println("Gyro Angle: {}, Rate: {} (Offset: {})", angle, rate, calibrationOffset);
    }
  }

  private double getDeadZone(double input, double deadZone) {
    if(deadZone > Math.abs(input)) {
      return 0.0;
    } else {
      return input;
    }
  }

  /**
   * Joystick stuff
   */
  private double getRightValue() {
    return getDeadZone(driverJoystick.getRawAxis(5), 0.148625);
  }

  private double getLeftValue() {
    return -getDeadZone(driverJoystick.getRawAxis(1), 0.148625);
  }
}
