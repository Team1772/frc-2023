package frc.robot.commands.telescope;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Telescope;

public class UpTelescopeMove extends CommandBase {
  private Telescope telescope;
  private Timer timer;
  private double seconds;

  public UpTelescopeMove(Telescope telescope, double seconds) {
    this.telescope = telescope;
    this.seconds = seconds;

    this.timer = new Timer();
    addRequirements(this.telescope);
  }

  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.telescope.set(0.7);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(seconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.telescope.stop();
    this.timer.stop();
  }
}

