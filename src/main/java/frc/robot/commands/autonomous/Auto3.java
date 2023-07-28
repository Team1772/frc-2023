package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeMove.IntakeUpDown;
import frc.robot.commands.IntakeMove.IntakeUpToSensor;
import frc.robot.commands.Poker.Poke;
import frc.robot.commands.drivetrain.DriveToDistance;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.intake.ShootHigh;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class Auto3 extends SequentialCommandGroup {
    public Auto3(Drivetrain drivetrain, Poker poker, Intake intake, IntakeMove intakeMove) {

        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, poker),
            new WaitCommand(2),
            //new ReverseTimer(drivetrain, 1.8),
            new DriveToDistance(drivetrain, 3.2),
            new Poke(poker, false)
        );

    } 

}