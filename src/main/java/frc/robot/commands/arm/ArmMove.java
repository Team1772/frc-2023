package frc.robot.commands.arm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmMove extends CommandBase {
  private static final double SENSIBILITY_DEADZONE = 0.4;
  private static final double OUTPUT_LIMIT = 0.16;
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
    var absoluteSpeed = Math.abs(speed.getAsDouble());
    var realSpeed = speed.getAsDouble();
    if(absoluteSpeed < (SENSIBILITY_DEADZONE * 0.8)) {
      this.arm.set(realSpeed * (OUTPUT_LIMIT * 0.8));
    } else if (absoluteSpeed < SENSIBILITY_DEADZONE) {
      this.arm.set(realSpeed * (OUTPUT_LIMIT * 0.9));
    } else {
      this.arm.set(realSpeed * OUTPUT_LIMIT);
    }
  }

  @Override
  public void end(boolean isInterrupted) {
    this.arm.stop();
  }
}
