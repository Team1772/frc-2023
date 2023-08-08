package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ShootSuperHigh extends CommandBase {
  private final Intake intake;

  public ShootSuperHigh(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.setLower(IntakeConstants.Speeds.speedShootSuperHigh);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stopLower();
  }
}
