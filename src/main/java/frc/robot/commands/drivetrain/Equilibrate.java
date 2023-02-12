package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.core.components.Limelight;
import frc.core.components.Limelight.LedMode;
import frc.robot.constants.LimelightConstants;
import frc.robot.subsystems.Drivetrain;

public class Equilibrate extends CommandBase {

  private Drivetrain drivetrain;

  public Equilibrate(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    
    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {}

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean isInterrupted) {

  }
}
