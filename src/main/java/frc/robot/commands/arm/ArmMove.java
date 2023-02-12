package frc.robot.commands.arm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmMove extends CommandBase {
  private Arm arm;
  private DoubleSupplier speed;
  
  public ArmMove(Arm arm, DoubleSupplier speed) {
    this.arm = arm;
    this.speed = speed;

    addRequirements(this.arm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.arm.set(speed.getAsDouble());
  }

  @Override
  public void end(boolean isInterrupted) {
    this.arm.stop();
  }
}
