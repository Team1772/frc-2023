package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class UpArmMove extends CommandBase {

  private Arm arm;

  private static final double OUTPUT = 0.22;
  
  public UpArmMove(Arm arm) {
    this.arm = arm;

    addRequirements(this.arm);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.arm.set(OUTPUT);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.arm.stop();
  }
}