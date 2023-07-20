package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake;

public class Shoot extends CommandBase {
  private final Intake intake;
  

  public Shoot(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(-0.75);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }

}
