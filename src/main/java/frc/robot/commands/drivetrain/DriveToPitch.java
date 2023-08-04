package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveToPitch extends CommandBase {
    private double speed;
    private double pitch;
    private Drivetrain drivetrain;

    public DriveToPitch(Drivetrain drivetrain, double speed, double pitch) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.pitch = pitch;

        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.drivetrain.arcadeDrive(-speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
        this.drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return drivetrain.getPitch() >= pitch;
    }

}
