package frc.robot.commands.poker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Poker;

public class PokeTimer extends CommandBase {
    private Poker poker;
    private Timer timer;

    public PokeTimer(Poker poker) {
      this.poker = poker;
      addRequirements(this.poker);
    }
  
    @Override
    public void initialize() {
      this.timer.start();
    }
  
    @Override
    public void execute() {
      this.poker.poke();
    }

    @Override
    public boolean isFinished() {
      return timer.hasElapsed(1);
    }
  
    @Override
    public void end(boolean isInterrupted) {
      this.poker.unpoke();
      this.timer.stop();
    }
  }