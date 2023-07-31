package frc.robot.commands.Poker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Poker;

public class PokeTimer extends CommandBase {
    private Poker poker;
    private boolean control;
    
    private Timer timer;

    public PokeTimer(Poker poker, boolean control) {
      this.poker = poker;
      this.control = control;
  
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