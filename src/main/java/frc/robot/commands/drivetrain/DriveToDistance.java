package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistance extends CommandBase {
    private double distance;
    private Drivetrain drivetrain;
    private double speed;

    public DriveToDistance(Drivetrain drivetrain, double distance, double speed) {
        this.distance = distance;
        this.drivetrain = drivetrain;
        this.speed = speed;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        this.drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        this.drivetrain.arcadeDrive(speed, 0);
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
