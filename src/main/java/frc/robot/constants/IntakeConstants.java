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
            cubeInfrared = 3;
    }

    public static final class Speeds {
        public static final double
            speedCollect = -0.33,
            speedRelease = 0.3,
            speedShootMid = 0.45,
            speedShootHigh = 0.4;
    }

    public static final class Solenoid {
        public static final int
            solenoidOpen = 6,
            solenoidClose = 7;
    }

    public static final class Seconds {
        public static final int
            autoShootSeconds = 3,
            autoCollectSeconds = 5;
    }
}
