package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.core.components.Limelight;
import frc.core.components.SmartController;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.arm.ArmMove;
import frc.robot.commands.drivetrain.AimTarget;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.BalanceChargeStation;
import frc.robot.commands.drivetrain.PrecisionDrive;
import frc.robot.commands.intake.CollectPiece;
import frc.robot.commands.intake.ReleasePiece;
import frc.robot.constants.OIConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private final Intake intake;
  private final Arm arm;
  
  private TrajectoryBuilder trajectoryBuilder;

  private XboxController driver;
  private XboxController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intake = new Intake();
    this.arm = new Arm();

    this.driver = new XboxController(OIConstants.driverControllerPort);
    this.operator = new SmartController(OIConstants.operatorControllerPort);

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "straight", "reverse");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
    this.buttonBindingsArm();
    this.buttonBindingsIntake();
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

  private void buttonBindingsArm() {
    this.arm.setDefaultCommand(
      new ArmMove(
        this.arm, 
        () -> this.operator.getLeftY()
      )
    );
  }

  private void buttonBindingsIntake() {
    var leftBumper = new JoystickButton(this.operator, Button.kLeftBumper.value);
    var rightBumper = new JoystickButton(this.operator, Button.kRightBumper.value);

    leftBumper.whileTrue(new CollectPiece(this.intake));
    rightBumper.whileTrue(new ReleasePiece(this.intake));
  }
  
  public Command getAutonomousCommand() {
    var auto = new BalanceChargeStation(drivetrain);
    
    return auto;
  }
}
