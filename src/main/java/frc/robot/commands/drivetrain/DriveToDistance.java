package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistance extends CommandBase {
    private double distance = 0.65;
    private static final double SPEED = 0.7;
    private Drivetrain drivetrain;

    public DriveToDistance(Drivetrain drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;

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

        if (distance > 0 && drivetrain.getAverageDistance() > distance) {
            return true;
        } else if (distance < 0 && drivetrain.getAverageDistance() < distance) {
            return true;
        }
        return false;
    }

}
