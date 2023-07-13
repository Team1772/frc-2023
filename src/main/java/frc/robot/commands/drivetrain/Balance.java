package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Balance extends CommandBase {
    private Drivetrain drivetrain;
    private static final double SPEED = 0.6;
    private static final double PITCH_TO_BALANCE = 5;
    private static final double PITCH_VELOCITY_ALMOST_BALANCED = 2;

    public Balance(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.setBrakeMode();
        drivetrain.arcadeDrive(SPEED, 0);
    }

    @Override
    public void execute() {
        if (Math.abs(drivetrain.getPitch()) < PITCH_TO_BALANCE &&
                Math.abs(drivetrain.getPitchVelocity()) < PITCH_VELOCITY_ALMOST_BALANCED) {
            drivetrain.arcadeDrive(0, 0);
        } else {
            double speed = (drivetrain.getPitch() > 0) ? SPEED : -SPEED;
            drivetrain.arcadeDrive(speed, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
