package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.drivetrain.Turn;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.IntakePoker;

public class Auto3 extends SequentialCommandGroup {
    public Auto3(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, IntakePoker intakePoker) {

        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, intakePoker),
            new WaitCommand(1),
            new ReverseTimer(drivetrain, 2.5),
            new Turn(drivetrain, 1.5)
            // new IntakeDownToSensor(intakeMove),
            
            //new DriveToDistance(drivetrain, 3.2),
            
        );

    } 

}