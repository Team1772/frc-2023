package frc.robot.commands.drivetrain;

import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Turn extends CommandBase {
  private Drivetrain drivetrain;
  private Timer timer;

  private double seconds;

  public Turn(Drivetrain drivetrain, double seconds) {
    this.drivetrain = drivetrain;

    this.timer = new Timer();
    this.seconds = seconds;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    this.timer.start();
}

  @Override
  public void execute() {
    this.drivetrain.arcadeDrive(-0.3, -0.67);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(seconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.drivetrain.arcadeDrive(0, 0);
    this.timer.stop();
  }
}