package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.components.Limelight;
import frc.core.components.SmartNavX;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  private double lastPitch;
  private double pitchVelocity;
  private LinearFilter pitchVelolcityFilter = LinearFilter.singlePoleIIR(0.1, 0.02);

  private final WPI_TalonSRX motorLeftBack;
  private final WPI_TalonSRX motorLeftFront;
  private final WPI_TalonSRX motorRightBack;
  private final WPI_TalonSRX motorRightFront;

  private final MotorControllerGroup motorsRight, motorsLeft;
  private final DifferentialDrive drive;
  private final Encoder encoderRight, encoderLeft;
  private final SmartNavX navX;
  private final DifferentialDriveOdometry odometry;


  public Drivetrain() {
    this.motorLeftBack = new WPI_TalonSRX(DrivetrainConstants.Motors.motorLeftBack);
    this.motorLeftFront = new WPI_TalonSRX(DrivetrainConstants.Motors.motorLeftFront);
    this.motorsLeft = new MotorControllerGroup(motorLeftBack, motorLeftFront);

    this.motorRightBack = new WPI_TalonSRX(DrivetrainConstants.Motors.motorRightBack);
    this.motorRightFront = new WPI_TalonSRX(DrivetrainConstants.Motors.motorRightFront);
    this.motorsRight = new MotorControllerGroup(motorRightBack, motorRightFront);
 
    this.motorLeftBack.setNeutralMode(NeutralMode.Coast);
    this.motorRightBack.setNeutralMode(NeutralMode.Coast);
    this.motorLeftFront.setNeutralMode(NeutralMode.Coast);
    this.motorRightFront.setNeutralMode(NeutralMode.Coast);

    this.setMotorsInverted(
      DrivetrainConstants.Motors.isMotorsLeftInverted,
      DrivetrainConstants.Motors.isMotorsRightInverted
    );

    this.drive = new DifferentialDrive(this.motorsRight, this.motorsLeft);

    this.encoderLeft = new Encoder(
      DrivetrainConstants.Encoders.encoderLeftPortOne,
      DrivetrainConstants.Encoders.encoderLeftPortTwo,
      DrivetrainConstants.Encoders.isEncoderLeftInverted
    );

    this.encoderRight = new Encoder(
      DrivetrainConstants.Encoders.encoderRightPortOne,
      DrivetrainConstants.Encoders.encoderRightPortTwo,
      DrivetrainConstants.Encoders.isEncoderRightInverted
    );

    this.navX = new SmartNavX();

    this.odometry = new DifferentialDriveOdometry(
        this.getRotation2d(), 
        0, 
        0
    );
 
    this.setEncodersDistancePerPulse();
    this.resetEncoders();
  }

  public void arcadeDrive(double forward, double rotation) {
    this.drive.arcadeDrive(forward, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    this.motorsLeft.setVoltage(leftVolts);
    this.motorsRight.setVoltage(rightVolts);
    System.out.println("left: " + leftVolts);
    System.out.println("right: " + rightVolts);

    this.drive.feed();
  }

  public void setMaxOutput(double maxOutput) {
    this.drive.setMaxOutput(maxOutput);
  } 

  public void resetEncoders() {
    this.encoderLeft.reset();
    this.encoderRight.reset();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
      this.encoderLeft.getRate(), 
      this.encoderRight.getRate()
    );
  }

  public double getAverageDistance() {
    var averageDistance = (this.encoderLeft.getDistance() + this.encoderRight.getDistance()) / 2.0;
    
    return averageDistance;
  }

  public void resetNavX() {
    this.navX.reset();
  }

  public void reset() {
    this.resetNavX();
    this.resetEncoders();
  }

  public double getAngle() {
    return this.navX.getAngle();
  }

  public Rotation2d getRotation2d() {
    return this.navX.getRotation2d();
  }

  public Pose2d getPose() {
    return this.odometry.getPoseMeters();
  }


  public void updateOdometry() {
    this.odometry.update(
      this.getRotation2d(), 
      this.encoderLeft.getDistance(), 
      this.encoderRight.getDistance()
    );
  }

  public void resetOdometry(Pose2d pose) {
    this.resetEncoders();

    /* CAUTION! MAY NOT WORK YET
    SEE WHAT WE SHOULD PUT IN PARAMS */
    this.odometry.resetPosition(
      this.getRotation2d(), 
      (double) this.getEncoderLeft().get(), 
      (double) this.getEncoderRight().get(), 
      pose
    );
  }

  public Encoder getEncoderLeft() {
    return this.encoderLeft;
  }

  public Encoder getEncoderRight() {
    return this.encoderRight;
  }

  public void setMotorsInverted(boolean isMotorsLeftInverted, boolean isMotorsRightInverted) {
    this.motorsLeft.setInverted(isMotorsLeftInverted);
    this.motorsRight.setInverted(isMotorsRightInverted);
  }

  public void setEncodersDistancePerPulse() {
    var wheelCircumferenceMeters = Units.inchesToMeters(DrivetrainConstants.Chassi.wheelRadius) * 2 * Math.PI;

    var distancePerPulse = wheelCircumferenceMeters / (double) DrivetrainConstants.Encoders.pulsesPerRotation;

    this.encoderLeft.setDistancePerPulse(distancePerPulse);
    this.encoderRight.setDistancePerPulse(distancePerPulse);
  }

  public void setBrakeMode() {
    this.motorLeftBack.setNeutralMode(NeutralMode.Brake);
    this.motorLeftFront.setNeutralMode(NeutralMode.Brake);
    this.motorRightBack.setNeutralMode(NeutralMode.Brake);
    this.motorRightFront.setNeutralMode(NeutralMode.Brake);
  }

  public double getPitch() {
    return this.navX.getPitch();
  }

  public boolean isBalanced() {
    return Math.abs(this.getPitch())<5;
  }

  public double getPitchVelocity() {
    return pitchVelocity;
  }

  @Override
  public void periodic() {
    pitchVelocity = pitchVelolcityFilter.calculate(getPitch() - lastPitch);
    lastPitch = getPitch();

    SmartDashboard.putNumber("[DRIVETRAIN] Encoder Left", this.encoderLeft.get());
    SmartDashboard.putNumber("[DRIVETRAIN] Encoder Right", this.encoderRight.get());
    SmartDashboard.putNumber("[DRIVETRAIN] Average Distance", this.getAverageDistance());
    SmartDashboard.putNumber("[DRIVETRAIN] Pitch Velocity", this.pitchVelocity);

    SmartDashboard.putNumber("[DRIVETRAIN] Altitude", this.navX.getAltitude());
    SmartDashboard.putNumber("[DRIVETRAIN] Pitch", this.navX.getPitch());
    SmartDashboard.putNumber("[DRIVETRAIN] Angle", this.navX.getAngle());
    SmartDashboard.putNumber("[LIMELIGHT] X-axis", Limelight.getX());
    SmartDashboard.putNumber("[LIMELIGHT] Y-axis", Limelight.getY());
    SmartDashboard.putNumber("[LIMELIGHT] Target Area", Limelight.getA());
    SmartDashboard.putNumber("[LIMELIGHT] Is On Target", Limelight.getV());
    


    SmartDashboard.putNumber("[MLB] getOutputCurrent", motorLeftBack.getOutputCurrent());
    SmartDashboard.putNumber("[MLB]Get Motor output voltage", motorLeftBack.getMotorOutputVoltage());
    SmartDashboard.putNumber("[MLB] getBusVoltage", motorLeftBack.getBusVoltage());
    SmartDashboard.putNumber("[MLB] getSupplyCurrent", motorLeftBack.getSupplyCurrent());
    SmartDashboard.putNumber("[MLB] getBusVoltage", motorLeftBack.getStatorCurrent());

    SmartDashboard.putNumber("[MLF] getOutputCurrent", motorLeftFront.getOutputCurrent());
    SmartDashboard.putNumber("[MLF]Get Motor output voltage", motorLeftFront.getMotorOutputVoltage());
    SmartDashboard.putNumber("[MLF] getBusVoltage", motorLeftFront.getBusVoltage());
    SmartDashboard.putNumber("[MLF] getSupplyCurrent", motorLeftFront.getSupplyCurrent());
    SmartDashboard.putNumber("[MLF] getBusVoltage", motorLeftFront.getStatorCurrent());

    SmartDashboard.putNumber("[MRB] getOutputCurrent", motorRightBack.getOutputCurrent());
    SmartDashboard.putNumber("[MRB]Get Motor output voltage", motorRightBack.getMotorOutputVoltage());
    SmartDashboard.putNumber("[MRB] getBusVoltage", motorRightBack.getBusVoltage());
    SmartDashboard.putNumber("[MRB] getSupplyCurrent", motorRightBack.getSupplyCurrent());
    SmartDashboard.putNumber("[MRB] getBusVoltage", motorRightBack.getStatorCurrent());

    SmartDashboard.putNumber("[MRF] getOutputCurrent", motorRightFront.getOutputCurrent());
    SmartDashboard.putNumber("[MRF]Get Motor output voltage", motorRightFront.getMotorOutputVoltage());
    SmartDashboard.putNumber("[MRF] getBusVoltage", motorRightFront.getBusVoltage());
    SmartDashboard.putNumber("[MRF] getSupplyCurrent", motorRightFront.getSupplyCurrent());
    SmartDashboard.putNumber("[MRF] getBusVoltage", motorRightFront.getStatorCurrent());
    
    this.updateOdometry();
  }
}