package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class SetBrakeMode extends CommandBase {
    private Drivetrain drivetrain;

    public SetBrakeMode(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {
        this.drivetrain.setBrakeMode();
    }
    

}
