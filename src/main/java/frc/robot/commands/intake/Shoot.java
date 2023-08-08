package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class Shoot extends CommandBase {
  private final Intake intake;
  private double speed;
  
  

  public Shoot(Intake intake, double speed) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.setLower(speed);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stopLower();
  }

}
