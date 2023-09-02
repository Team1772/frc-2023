package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.Balance;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.poker.Poke;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class Auto4 extends SequentialCommandGroup {

    public Auto4(Drivetrain drivetrain, IntakeMove intakeMove, Poker poker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(2),
            // new ShootHighTimer(intake, poker),
            new WaitCommand(2),
            new ReverseTimer(drivetrain, 2.3),
            //new DriveToDistance(drivetrain, 5),
            new WaitCommand(2),
            new Poke(poker, false),
            new Balance(drivetrain, -0.3, 2, 0.4)
        );

    } 

}