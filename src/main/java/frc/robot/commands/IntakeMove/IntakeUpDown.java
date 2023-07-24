package frc.robot.commands.IntakeMove;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMove;

public class IntakeUpDown extends CommandBase {
  private IntakeMove intakeMove;
  private DoubleSupplier speed;
  
  public IntakeUpDown(IntakeMove intakeMove, DoubleSupplier speed) {
    this.intakeMove = intakeMove;
    this.speed = speed;

    addRequirements(this.intakeMove);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intakeMove.set(0.5 * speed.getAsDouble());
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakeMove.stop();
  }
}