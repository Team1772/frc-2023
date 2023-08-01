package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeMove.IntakeUpToSensor;
import frc.robot.commands.drivetrain.Balance;
import frc.robot.commands.drivetrain.ForwardTimer;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class Auto4 extends SequentialCommandGroup {

    public Auto4(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, Poker poker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, poker),
            new WaitCommand(1),
            new ReverseTimer(drivetrain, 2.3),
            new WaitCommand(1),
            new ForwardTimer(drivetrain, 2),
            new Balance(drivetrain)
        );

    } 

}