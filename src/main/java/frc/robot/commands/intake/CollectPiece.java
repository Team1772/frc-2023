package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.core.components.Limelight;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class CollectPiece extends CommandBase {
  private Intake intake;
  
  public CollectPiece(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.close();
  }

  @Override
  public void end(boolean isInterrupted) {}
}