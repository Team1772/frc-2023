package frc.robot.commands.telescope;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Telescope;

public class TelescopeMove extends CommandBase {
  private Telescope telescope;
  private DoubleSupplier speed;
  
  public TelescopeMove(Telescope telescope, DoubleSupplier speed) {
    this.telescope = telescope;
    this.speed = speed;

    addRequirements(this.telescope);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.telescope.set(speed.getAsDouble());
  }

  @Override
  public void end(boolean isInterrupted) {
    this.telescope.stop();
  }
}
