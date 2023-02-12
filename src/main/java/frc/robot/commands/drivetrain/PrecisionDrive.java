package frc.robot.commands.drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class PrecisionDrive extends CommandBase {

  private static final double MAX_OUTPUT = 0.45;
  private static final double DEFAULT_OUTPUT = 1;
  private Drivetrain drivetrain;
  private DoubleSupplier forward;
  private DoubleSupplier rotation;

  public PrecisionDrive(Drivetrain drivetrain, DoubleSupplier forward, DoubleSupplier rotation) {
    this.drivetrain = drivetrain;
    this.forward = forward;
    this.rotation = rotation;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    this.drivetrain.setMaxOutput(MAX_OUTPUT);
  }

  @Override
  public void execute() {
    this.drivetrain.arcadeDrive(forward.getAsDouble(), rotation.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    this.drivetrain.setMaxOutput(DEFAULT_OUTPUT);
  }
}
