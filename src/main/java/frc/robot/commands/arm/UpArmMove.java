package frc.robot.commands.arm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class UpArmMove extends CommandBase {

  private Arm arm;
  
  public UpArmMove(Arm arm) {
    this.arm = arm;

    addRequirements(this.arm);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.arm.set(0);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.arm.stop();
  }
}
