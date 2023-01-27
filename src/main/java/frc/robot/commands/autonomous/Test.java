package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.core.util.TrajectoryBuilder;
import frc.robot.subsystems.Drivetrain;

public class Test extends SequentialCommandGroup {
  public static boolean INITIAL_TRAJECTORY = true;
  public static boolean MIDDLE_TRAJECTORY = false;

  public Test(Drivetrain drivetrain, TrajectoryBuilder trajectoryBuilder) {
  
    super.addCommands(
      trajectoryBuilder.build(INITIAL_TRAJECTORY, "straight")
      // trajectoryBuilder.build(MIDDLE_TRAJECTORY, "reverse")

    );
  
    }
  }
