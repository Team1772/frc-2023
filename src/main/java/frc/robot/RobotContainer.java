package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.core.components.SmartController;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.OperatorRumble;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.constants.OIConstants;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;

  
  private TrajectoryBuilder trajectoryBuilder;

  private PS4Controller driver;
  private XboxController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new PS4Controller(OIConstants.driverControllerPort);
    this.operator = new SmartController(OIConstants.operatorControllerPort);

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
        () -> -this.driver.getLeftY(), 
        () -> this.driver.getRightY()
      )
    );
  }

  public Command getAutonomousCommand() {
    // Command auto = new MiddleNode(drivetrain, telescope, intake, arm, trajectoryBuilder);
    
    return null;
  }
}
