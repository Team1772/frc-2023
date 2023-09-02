package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.poker.Poke;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class AutoShootAndDontGoReverse extends SequentialCommandGroup {

    public AutoShootAndDontGoReverse(IntakeMove intakeMove, Poker poker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            // new ShootHighTimer(intake, poker),
            new WaitCommand(1),
            new Poke(poker, false)
        );

    } 

}