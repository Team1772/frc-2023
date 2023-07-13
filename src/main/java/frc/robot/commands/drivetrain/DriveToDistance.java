package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistance extends CommandBase {
    private static final double DISTANCE = 0.65;
    private static final double SPEED = 0.6;
    private Drivetrain drivetrain;

    public DriveToDistance(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        this.drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        this.drivetrain.arcadeDrive(SPEED, 0);
    }

    @Override
    public void end(boolean interrupted) {
        this.drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {

        if (DISTANCE > 0 && drivetrain.getAverageDistance() > DISTANCE) {
            return true;
        } else if (DISTANCE < 0 && drivetrain.getAverageDistance() < DISTANCE) {
            return true;
        }
        return false;
    }

}
