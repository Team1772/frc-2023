package frc.robot.constants;

public final class IntakeConstants {
    
    public static final class Motors {
        public static final int
            motorUpper = 7,
            motorLower = 6;

        public static final boolean 
            isMotorUpperInverted = true,
            isMotorLowerInverted = false;
    }

    public static final class Sensor {
        public static final int
            cubeInfrared = 4;
    }

    public static final class Speeds {
        public static final double
            speedCollect = 0.27,
            speedRelease = -0.2,
            speedShootMid = -0.29,
            speedShootHigh = -0.4;
    }

}
