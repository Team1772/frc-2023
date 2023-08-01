package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeMove.IntakeDownToSensor;
import frc.robot.commands.IntakeMove.IntakeUpToSensor;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.drivetrain.TurnTimer;
import frc.robot.commands.intake.CollectSensor;
import frc.robot.commands.intake.CollectSensorAndIntakeUp;
import frc.robot.commands.intake.ShootHighTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Poker;

public class Auto1 extends SequentialCommandGroup {

    public Auto1(Drivetrain drivetrain, Intake intake, IntakeMove intakeMove, Poker poker) {
        super.addCommands(
            new IntakeUpToSensor(intakeMove),
            new WaitCommand(1),
            new ShootHighTimer(intake, poker),
            new WaitCommand(1),
            new ReverseTimer(drivetrain, 0.6),
            new TurnTimer(drivetrain, 5.7),
            new WaitCommand(1),
            new IntakeDownToSensor(intakeMove),
            //new CollectSensor(intake)  // Testar para ver se está funcionando
            new CollectSensorAndIntakeUp(intake, intakeMove) // Testar para ver se está pegando o cubo direitinho e subindo o intake
         );

    } 

}