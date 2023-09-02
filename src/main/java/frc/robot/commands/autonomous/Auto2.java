package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.Balance;
import frc.robot.commands.drivetrain.DriveToPitch;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.IntakePoker;

public class Auto2 extends SequentialCommandGroup {

    public Auto2(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, IntakePoker intakePoker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, intakePoker),
            new WaitCommand(1),
            new DriveToPitch(drivetrain, 0.78, 9),
            new ReverseTimer(drivetrain, 0.3),
            new Balance(drivetrain, 0.4, 6, 0.15)
        );

    } 

}