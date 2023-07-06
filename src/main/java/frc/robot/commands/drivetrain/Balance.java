package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Balance extends CommandBase {
    private Drivetrain drivetrain;
    private double speed;

    public Balance(Drivetrain drivetrain, double speed) {
        this.drivetrain = drivetrain;
        this.speed = speed;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.setBrakeMode();
        drivetrain.arcadeDrive(speed, 0);
    }

    @Override
    public void execute() {
        // Dar uma olhada pra ter certeza o que é esse calculo
        if (Math.abs(drivetrain.getPitchVelocity()) > 0.15) {
            drivetrain.arcadeDrive(0, 0);
        } else {
            if (drivetrain.getPitch() > 0) {
                drivetrain.arcadeDrive(speed, 0);
            } else {
                drivetrain.arcadeDrive(-speed, 0);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0);
    }

    // Brincar com os valores
    @Override
    public boolean isFinished() {
        return Math.abs(drivetrain.getPitch()) < 3 && Math.abs(drivetrain.getPitchVelocity()) < 0.15;
    }

}
