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
import frc.robot.commands.arm.ArmMove;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.BalanceChargeStation;
import frc.robot.commands.drivetrain.PrecisionDrive;
import frc.robot.commands.intake.CollectPiece;
import frc.robot.commands.intake.ReleasePiece;
import frc.robot.commands.telescope.TelescopeMove;
import frc.robot.constants.OIConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Telescope;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private final Intake intake;
  private final Arm arm;
  private final Telescope telescope;
  
  private TrajectoryBuilder trajectoryBuilder;

  private PS4Controller driver;
  private XboxController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intake = new Intake();
    this.arm = new Arm();
    this.telescope = new Telescope();

    this.driver = new PS4Controller(OIConstants.driverControllerPort);
    this.operator = new SmartController(OIConstants.operatorControllerPort);

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "straight", "reverse");

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
    this.buttonBindingsArm();
    this.buttonBindingsIntake();
    this.buttonBindingsTelescope();
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

  private void buttonBindingsTelescope() {
    this.telescope.setDefaultCommand(
      new TelescopeMove(
        this.telescope,
        this.operator,
        () -> -this.operator.getRightY()
      )
    );

    var isLimit = new Trigger(() -> this.telescope.isLimit());
    isLimit.whileTrue(new OperatorRumble(this.operator, true));
    isLimit.whileFalse(new OperatorRumble(this.operator, false));

  }
  
  public Command getAutonomousCommand() {
    Command auto = null;
    
    return auto;
  }
}
