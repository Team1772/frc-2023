package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ReleasePieceTimer extends CommandBase {
  private Intake intake;

  private Timer timer;

  
  private double seconds;
  
  public ReleasePieceTimer(Intake intake, double seconds) {
    this.intake = intake;

    this.timer = new Timer();
    this.seconds = seconds;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.intake.open();
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(seconds);
  }

  @Override
  public void end(boolean isInterrupted) {
  }
}