package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakePoker;

public class ShootHighTimer extends CommandBase {
  private final Intake intake;
  private final IntakePoker intakePoker;

  private Timer timer;
  

  public ShootHighTimer(Intake intake, IntakePoker intakePoker) {
    this.intake = intake;
    this.intakePoker = intakePoker;
    this.timer = new Timer();

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.intake.setLower(IntakeConstants.Speeds.speedShootHigh);
    if(this.timer.hasElapsed(2)){
      this.intakePoker.setUpper(0.7);
    }
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(IntakeConstants.Seconds.autoShootSeconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stopLower();
  }

}
