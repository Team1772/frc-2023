package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.BalanceRumble;
import frc.core.util.oi.DriverController;
import frc.core.util.oi.OperatorController;
import frc.robot.commands.IntakeMove.IntakeUpDown;
import frc.robot.commands.Poker.Poke;
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
import frc.robot.subsystems.Poker;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private IntakeMove intakeMove;
  private final Intake intake;
  private final Poker poker;

  private DriverController driver;
  private OperatorController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intake = new Intake();
    this.intakeMove = new IntakeMove();
    this.poker = new Poker();

    this.driver = new DriverController();
    this.operator = new OperatorController();
    configureButtonBindings(); 
    
    //this.poker.getCompressor().enableDigital();
  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
    this.buttonBindingsIntakeMove();
    this.buttonBindingsIntake();
    this.buttonBindingsPoke();
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

  private void buttonBindingsPoke(){
    this.driver.whileLeftBumper(new Poke(poker, true));
    this.driver.whileRightBumper(new Poke(poker, false));
  }

  private void buttonBindingsIntakeMove(){
    // this.intakeMove.setDefaultCommand(
    //   new IntakeUpDown(
    //     this.intakeMove, 
    //     () -> -operator.getRightY()
    //   )
    // );

    this.operator.whileYButton(new IntakeUpDown(intakeMove, () -> 0.92));
    this.operator.whileBButton(new IntakeUpDown(intakeMove, () -> -0.92));
  }
  
  private void buttonBindingsIntake(){
    this.operator.whileAButton(new Release(intake));
    this.operator.whileXButton(new ShootMid(intake));
    this.operator.whileLeftBumper(new Collect(intake));
    this.operator.whileRightBumper(new ShootHigh(intake));

    
  }

  public Command getAutonomousCommand() {
    Command auto = new BalanceRoutine(drivetrain);
    //Command auto = new Test(drivetrain, trajectoryBuilder); 

    return auto;
  }
}
