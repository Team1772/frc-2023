package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakePoker;

public class PokeIntake extends CommandBase {
  private final IntakePoker intakePoker;
  
  

  public PokeIntake(IntakePoker intake) {
    this.intakePoker = intake;

    addRequirements(this.intakePoker);
  }

  

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intakePoker.setUpper(0.5);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakePoker.stopUpper();
  }

}
