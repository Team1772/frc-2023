package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.arm.DownArmMoveTimer;
import frc.robot.commands.drivetrain.ReverseTimer;
import frc.robot.commands.intake.ReleasePiece;
import frc.robot.commands.intake.ReleasePieceTimer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Telescope;

public class MiddleNode extends SequentialCommandGroup {
  public MiddleNode(Drivetrain drivetrain, Telescope telescope, Intake intake, Arm arm, TrajectoryBuilder trajectoryBuilder) {

    super.addCommands(
    new DownArmMoveTimer(arm, 3.6),
    new WaitCommand(2),
    new ReleasePieceTimer(intake, 1),
    new WaitCommand(2),
    new ReverseTimer(drivetrain, 1.4)
    );
  }
}