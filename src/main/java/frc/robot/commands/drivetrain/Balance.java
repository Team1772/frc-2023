package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Balance extends CommandBase {
    private Drivetrain drivetrain;
    private double speed = 0.55;
    private double pitchToBalance = 2;
    private double pitchVelocityAlmostBalanced = 0.4;
    boolean controle;

    public Balance(Drivetrain drivetrain, double speed, double pitchToBalance, double pitchVelocityAlmostBalanced) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.pitchToBalance = pitchToBalance;
        this.pitchVelocityAlmostBalanced = pitchVelocityAlmostBalanced;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.setBrakeMode();
        drivetrain.arcadeDrive(speed, 0);
        controle = false;
    }

    @Override
    public void execute() {
        // if (Math.abs(drivetrain.getPitch()) <= 6) {
        //     double speed = (drivetrain.getPitch() > 0) ? 0.1 : -0.1;
        //     drivetrain.arcadeDrive(speed, 0);
        // }

        // if (Math.abs(drivetrain.getPitch()) < pitchToBalance &&
        //         Math.abs(drivetrain.getPitchVelocity()) < pitchVelocityAlmostBalanced) {
        //     drivetrain.arcadeDrive(0, 0);
        //     controle = true;
        // } else {
        //     speed = (drivetrain.getPitch() > 0) ? speed : -speed;

        //     drivetrain.arcadeDrive(speed, 0);
        // }
    }
    
    @Override
    public boolean isFinished() {
        return controle;
    }
    
    @Override
    public void end(boolean interrupted) {
    }


}
