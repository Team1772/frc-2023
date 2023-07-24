package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.BalanceRumble;
import frc.core.util.oi.DriverController;
import frc.core.util.oi.OperatorController;
import frc.robot.commands.IntakeMove.IntakeUpDown;
import frc.robot.commands.autonomous.BalanceRoutine;
import frc.robot.commands.autonomous.Test;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.ShootMid;
import frc.robot.commands.intake.ShootHigh;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private IntakeMove intakeMove;
  private final Intake intake;
  
  private TrajectoryBuilder trajectoryBuilder;

  private DriverController driver;
  private OperatorController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intake = new Intake();
    this.intakeMove = new IntakeMove();

    this.driver = new DriverController();
    this.operator = new OperatorController();
    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain,  "reverse1", "charge1");
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
    this.buttonBindingsTeste();
    this.buttonBindingsTeste2();
  }

  
  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> -driver.getLeftY(),
        () -> driver.getRightX()
      )
    );

    this.driver.getAButton().toggleOnTrue(
      new BalanceRumble(this.drivetrain, this.driver)
    );
  }

  private void buttonBindingsTeste(){
    this.intakeMove.setDefaultCommand(
      new IntakeUpDown(
        this.intakeMove, 
        () -> operator.getRightY()
      )
    );
  }
  
  private void buttonBindingsTeste2(){
    this.operator.whileRightBumper(new Collect(intake));
    this.operator.whileAButton(new Release(intake));
    this.operator.whileLeftBumper(new ShootHigh(intake));
    this.operator.whileXButton(new ShootMid(intake));

    //this.driver.whileYButton(new IntakeMove(intakeUpDown, () -> -0.6));
    //this.driver.whileBButton(new IntakeMove(intakeUpDown, () -> 0.6));
  }

  public Command getAutonomousCommand() {
    //Command auto = new BalanceRoutine(drivetrain);
    Command auto = new Test(drivetrain, trajectoryBuilder); 

    return auto;
  }
}
