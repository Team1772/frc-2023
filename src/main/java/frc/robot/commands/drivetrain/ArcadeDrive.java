package frc.robot.commands.drivetrain;

import frc.core.util.oi.DriverController;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
  private Drivetrain drivetrain;
  private DoubleSupplier forward;
  private DoubleSupplier rotation;
  private DriverController driver;


  public ArcadeDrive(Drivetrain drivetrain, DoubleSupplier forward, DoubleSupplier rotation, DriverController driver) {
    this.drivetrain = drivetrain;
    this.forward = forward;
    this.rotation = rotation;
    this.driver = driver;

    addRequirements(this.drivetrain);
  }



  @Override
  public void execute() {

    if (this.driver.getLeftBumper().getAsBoolean()){
      this.drivetrain.arcadeDrive(this.forward.getAsDouble()*0.5, this.rotation.getAsDouble());
    } else {
      this.drivetrain.arcadeDrive(this.forward.getAsDouble(), this.rotation.getAsDouble());
    }
  }
}