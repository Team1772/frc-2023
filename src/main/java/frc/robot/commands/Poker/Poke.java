package frc.robot.commands.poker;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Poker;

public class Poke extends CommandBase {
  private Poker poker;
  private boolean control;

  public Poke(Poker poker, boolean control) {
    this.poker = poker;
    this.control = control;

    addRequirements(this.poker);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (this.control) {
      this.poker.poke();
    } else {
      this.poker.unpoke();
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean isInterrupted) {
  }
}