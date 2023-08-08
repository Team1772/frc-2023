package frc.robot.commands.IntakePoker;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakePoker;

public class PokeIntaker extends CommandBase {
  private final IntakePoker intakePoker;
  
  

  public PokeIntaker(IntakePoker intakePoker) {
    this.intakePoker = intakePoker;

    addRequirements(this.intakePoker);
  }

  

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intakePoker.setUpper(0.7);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakePoker.stopUpper();
  }

}
