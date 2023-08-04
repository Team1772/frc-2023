package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeMove.IntakeDownToSensor;
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

public class AutoNothing extends SequentialCommandGroup {

    public AutoNothing(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, Poker poker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, poker),
            new WaitCommand(1),
            new Poke(poker, false)
        );

    } 

}