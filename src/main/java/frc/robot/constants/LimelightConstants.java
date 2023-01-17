package frc.robot.constants;

public final class LimelightConstants {
  
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