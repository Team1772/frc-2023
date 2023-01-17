package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.components.Limelight;
import frc.core.components.SmartNavX;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  private final MotorControllerGroup motorsRight, motorsLeft;
  private final DifferentialDrive drive;
  private final Encoder encoderRight, encoderLeft;
  private final SmartNavX navX;
  private final DifferentialDriveOdometry odometry;

  public Drivetrain() {
    var motorLeftBack = new VictorSP(DrivetrainConstants.Motors.motorLeftBack);
    var motorLeftFront = new VictorSP(DrivetrainConstants.Motors.motorLeftFront);
    this.motorsLeft = new MotorControllerGroup(motorLeftBack, motorLeftFront);

    var motorRightBack = new VictorSP(DrivetrainConstants.Motors.motorRightBack);
    var motorRightFront = new VictorSP(DrivetrainConstants.Motors.motorRightFront);
    this.motorsRight = new MotorControllerGroup(motorRightBack, motorRightFront);

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

    /* CAUTION! MAY NOT WORK YET
    SEE WHAT WE SHOULD PUT IN PARAMS */
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
      0, 
      0, 
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

  @Override
  public void periodic() {
    SmartDashboard.putNumber("[DRIVETRAIN] Encoder Left", this.encoderLeft.get());
    SmartDashboard.putNumber("[DRIVETRAIN] Encoder Right", this.encoderRight.get());
    SmartDashboard.putNumber("[DRIVETRAIN] Average Distance", this.getAverageDistance());

    SmartDashboard.putNumber("[LIMELIGHT] X-axis", Limelight.getX());
    SmartDashboard.putNumber("[LIMELIGHT] Y-axis", Limelight.getY());
    SmartDashboard.putNumber("[LIMELIGHT] Target Area", Limelight.getA());
    SmartDashboard.putNumber("[LIMELIGHT] Is On Target", Limelight.getV());

    this.updateOdometry();
  }
}