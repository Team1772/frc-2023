package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ShootMid extends CommandBase {
  private final Intake intake;
  
  

  public ShootMid(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(IntakeConstants.Speeds.speedShootMid);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }

}
