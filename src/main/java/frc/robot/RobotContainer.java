package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.autonomous.BalanceRoutine;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;

  
  private TrajectoryBuilder trajectoryBuilder;

  private DriverController driver;
  private OperatorController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new DriverController();
    this.operator = new OperatorController();

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "reverse");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
  }

  
  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> -driver.getLeftY(),
        () -> driver.getRightX()
      )
    );
  }

  public Command getAutonomousCommand() {
    Command auto = new BalanceRoutine(drivetrain);

    return auto;
  }
}
