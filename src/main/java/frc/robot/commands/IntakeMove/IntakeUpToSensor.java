package frc.robot.commands.IntakeMove;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;

public class IntakeUpToSensor extends CommandBase {
  private IntakeMove intakeMove;
  private double speed;
  
  public IntakeUpToSensor(IntakeMove intakeMove) {
    this.intakeMove = intakeMove;
    this.speed = 0.42;

    addRequirements(this.intakeMove);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intakeMove.set(this.speed);
  }
  
  @Override
  public boolean isFinished() {
    return this.intakeMove.isLimitMax();
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakeMove.stop();
  }

}