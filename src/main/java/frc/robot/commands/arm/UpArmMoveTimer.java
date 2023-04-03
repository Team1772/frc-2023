package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class UpArmMoveTimer extends CommandBase {

  private Arm arm;

  private static final double OUTPUT = -0.36;

  private Timer timer;
  private double seconds;
  
  public UpArmMoveTimer(Arm arm, double seconds) {
    this.arm = arm;
    this.seconds = seconds;

    this.timer = new Timer();

    addRequirements(this.arm);
  }

  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.arm.set(OUTPUT);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(seconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.arm.stop();
    this.timer.stop();
  }
}

