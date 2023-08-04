package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeMove.IntakeDownToSensor;
import frc.robot.commands.IntakeMove.IntakeUpDown;
import frc.robot.commands.IntakeMove.IntakeUpToSensor;
import frc.robot.commands.Poker.Poke;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.DriveToDistance;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.drivetrain.Turn;
import frc.robot.commands.intake.ShootHigh;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class Auto1 extends SequentialCommandGroup {

    public Auto1(Drivetrain drivetrain, Poker poker, Intake intake, IntakeMove intakeMove) {
    
        
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(0.7),
            new ShootHighTimer(intake, poker),
            new WaitCommand(0.7),
            new ReverseTimer(drivetrain, 0.8),
            new Turn(drivetrain, 1.7),
            new IntakeDownToSensor(intakeMove)
            //new DriveToDistance(drivetrain, 0.04)
            //new ArcadeDrive(drivetrain, () -> 0, () -> 0.7)
        );

    } 

}