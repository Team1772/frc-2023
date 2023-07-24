package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ShootUpper extends CommandBase {
  private final Intake intake;
  
  

  public ShootUpper(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(-0.4);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }

}
