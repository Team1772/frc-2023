package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.components.SmartController;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.constants.OIConstants;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;

  private TrajectoryBuilder trajectoryBuilder;

  private SmartController driver;
  private SmartController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new SmartController(OIConstants.driverControllerPort);
    this.operator = new SmartController(OIConstants.operatorControllerPort);

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
  }

  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> this.driver.getLeftStickY(), 
        () -> this.driver.getRightStickX()
      )
    );
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
