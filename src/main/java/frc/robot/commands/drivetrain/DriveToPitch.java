package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToPitch extends CommandBase {
    private static final double SPEED = 0.6;
    private static final double PITCH = 5;
    private Drivetrain drivetrain;

    public DriveToPitch(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.drivetrain.arcadeDrive(-SPEED, 0);
    }

    @Override
    public void end(boolean interrupted) {
        this.drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drivetrain.getPitch()) >= PITCH;
    }

}
