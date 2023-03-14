package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.arm.DownArmMoveTimer;
import frc.robot.commands.intake.ReleasePiece;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class MiddleNode extends SequentialCommandGroup {
  public MiddleNode(Drivetrain drivetrain, Intake intake, Arm arm, TrajectoryBuilder trajectoryBuilder) {

    super.addCommands(
    new ParallelCommandGroup(
      trajectoryBuilder.build(true, "forward"),
      new DownArmMoveTimer(arm, 1.5)
    ),
    new WaitCommand(3),
    new ReleasePiece(intake)
    );
  }
}