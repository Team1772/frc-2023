package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.drivetrain.Turn;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.IntakePoker;

public class Auto1 extends SequentialCommandGroup {

    public Auto1(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, IntakePoker intakePoker) {
    
        
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(0.7),
            new ShootHighTimer(intake, intakePoker),
            new WaitCommand(0.7),
            new ReverseTimer(drivetrain, 1.4),
            new Turn(drivetrain, 1.7),
            new IntakeDownToSensor(intakeMove)
            //new DriveToDistance(drivetrain, 0.04)
            //new ArcadeDrive(drivetrain, () -> 0, () -> 0.7)
        );

    } 

}