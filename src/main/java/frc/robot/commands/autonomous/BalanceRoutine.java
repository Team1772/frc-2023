package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.Balance;
import frc.robot.commands.drivetrain.DriveToDistance;
import frc.robot.commands.drivetrain.DriveToPitch;
import frc.robot.commands.drivetrain.SetBrakeMode;
import frc.robot.subsystems.Drivetrain;

public class BalanceRoutine extends SequentialCommandGroup {
    
    Drivetrain drivetrain;

    public BalanceRoutine(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        //Rodar 1 comando por vez!!
        super.addCommands(
            new SetBrakeMode(drivetrain),
            new DriveToPitch(drivetrain, 10, 0.2), //ver valores
            new PrintCommand("PITCH"),
            new DriveToDistance(drivetrain, 0.65, 0.3),
            new PrintCommand("DRIVEN"),
            new Balance(drivetrain, 0),
            new PrintCommand("BALANCED")
        );

    } 

}
