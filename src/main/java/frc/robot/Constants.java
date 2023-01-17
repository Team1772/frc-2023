package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {

  public static final class DrivetrainConstants {
    /* MOTOR CONSTANTS */
    public static final int
      motorLeftBack = 0,
      motorLeftFront = 1,
      motorRightBack = 2,
      motorRightFront = 3;

    public static final boolean 
      isMotorsLeftInverted = true,
      isMotorsRightInverted = false;

    /* ENCODER CONSTANTS */
    public static final int
      encoderLeftPortOne = 6,
      encoderLeftPortTwo = 7,
      encoderRightPortOne = 8,
      encoderRightPortTwo = 9;

    public static final boolean
      isEncoderLeftInverted = true,
      isEncoderRightInverted = false;
    
    public static final int
      pulsesPerRotation = 500, 
			cyclesPerRevolution = pulsesPerRotation * 4;  

    /* CHASSI CONSTANTS */
		public static final int 
      wheelRadius = 2;

    /* SysID CONSTANTS */
		public static final double 
      ksVolts = 1, //kS
      kvVoltSecondsPerMeter = 2, //kV
      kaVoltSecondsSquaredPerMeter = 0.9, //kA
      kTrackwidthMeters = 0.8,
      differentialDriveVoltageConstraintMaxVoltage = 7;

    public static final DifferentialDriveKinematics
      kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final class PID {
      public static final double 
        kPDriveVelocity = 4.0,
        kIDriveVelocity = 0,
        kDDriveVelocity = 0;
    }
  }

  public static final class AutoConstants {
    public static final double 
      kMaxSpeedMetersPerSecond = 2,
      kMaxAccelerationMetersPerSecondSquared = 2,
      kRamseteB = 2,
      kRamseteZeta = 0.7;
  }

  public static final class LimelightConstants {
		public static final String tableName = "limelight";
		public static final int pipeline = 0;

    public static final class Seeking {
			public static final double kP = -0.1;
		}

		public static final class AimTarget {
			public static final double
        kP = 0.1,
				minCommand = 0.02;
		}

    public static final class RangeTarget {
			public static final double 
				kPDistance = -0.1;
		}
		
		public static final class AimAndRangeTarget {
			public static final double 
        kP = -0.1,
				kPDistance = -0.50,
				minCommand = 0.05;
		}
	}

  public static final class OIConstants {
    public static final int
      driverControllerPort = 0,
      operatorControllerPort = 1;
  }

  public static final class PIDTalonConstants {
    public static final boolean isSensorPhase = true;

    public static final int kPIDLoopIdx = 0,
      kTimeoutMs = 30,
      nominalOutputForwardValue = 0,
      nominalOutputReverseValue = 0,
      peakOutputForwardValue = 1,
      peakOutputReverseValue = -1;
  }
}