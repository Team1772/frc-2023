package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.core.components.SmartController;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.autonomous.Test;
import frc.robot.commands.drivetrain.AimTarget;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.PrecisionDrive;
import frc.robot.constants.OIConstants;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;

  private TrajectoryBuilder trajectoryBuilder;

  private XboxController driver;
  private SmartController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new XboxController(OIConstants.driverControllerPort);
    this.operator = new SmartController(OIConstants.operatorControllerPort);

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "straight", "reverse");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
  }

  private void buttonBindingsDrivetain() {

    var buttonBumperRight = new JoystickButton(this.driver, Button.kRightBumper.value);
    buttonBumperRight.toggleOnTrue(new PrecisionDrive(
      drivetrain, 
      () -> -this.driver.getLeftY(),
      () -> this.driver.getRightX()
    ));

    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> -this.driver.getLeftY(), 
        () -> this.driver.getRightX()
      )
    );

    var buttonBumperLeft = new JoystickButton(this.driver, Button.kLeftBumper.value);
    buttonBumperLeft.whileTrue(new AimTarget(this.drivetrain));

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
