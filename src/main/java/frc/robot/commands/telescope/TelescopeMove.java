package frc.robot.commands.telescope;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Telescope;

public class TelescopeMove extends CommandBase {
  private Telescope telescope;
  private XboxController operator;
  private DoubleSupplier speed;
  
  public TelescopeMove(Telescope telescope, XboxController operator, DoubleSupplier speed) {
    this.telescope = telescope;
    this.speed = speed;

    addRequirements(this.telescope);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (this.telescope.isLimit() && speed.getAsDouble() < 0) {
      this.telescope.set(0);
    } else {
      this.telescope.set(speed.getAsDouble() * 0.8);
    }
  }

  @Override
  public void end(boolean isInterrupted) {
    this.telescope.stop();
  }
}
