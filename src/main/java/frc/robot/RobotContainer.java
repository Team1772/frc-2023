package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.core.components.SmartController;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.autonomous.Test;
import frc.robot.commands.drivetrain.ArcadeDrive;
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

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "straight");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
  }

  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> -this.driver.getLeftY(), 
        () -> this.driver.getRightX()
      )
    );
  }

  public Command getAutonomousCommand() {
    return new Test(this.drivetrain, this.trajectoryBuilder);
  }
}
