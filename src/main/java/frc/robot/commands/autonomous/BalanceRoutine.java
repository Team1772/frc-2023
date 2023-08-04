package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.Balance;
import frc.robot.commands.drivetrain.DriveToDistance;
import frc.robot.commands.drivetrain.DriveToPitch;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.drivetrain.SetBrakeMode;
import frc.robot.subsystems.Drivetrain;

public class BalanceRoutine extends SequentialCommandGroup {
    Drivetrain drivetrain;

    public BalanceRoutine(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        super.addCommands(
            new DriveToPitch(drivetrain, 0.88, 9),
            new DriveToPitch(drivetrain, 0.4, -2),
            new Balance(drivetrain, -0.3, 2, 0.4)
        );

    } 

}
