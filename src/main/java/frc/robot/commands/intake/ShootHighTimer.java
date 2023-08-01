package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Poker;

public class ShootHighTimer extends CommandBase {
  private final Intake intake;
  private final Poker poker;

  private Timer timer;
  

  public ShootHighTimer(Intake intake, Poker poker) {
    this.intake = intake;
    this.poker = poker;
    this.timer = new Timer();

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.intake.set(IntakeConstants.Speeds.speedShootHigh);
    if(this.timer.hasElapsed(IntakeConstants.Seconds.autoShootSeconds - 1)){
      this.poker.poke();
    }
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(IntakeConstants.Seconds.autoShootSeconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.poker.unpoke();
  }

}
