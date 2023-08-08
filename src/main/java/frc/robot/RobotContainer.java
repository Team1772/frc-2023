package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.BalanceRumble;
import frc.core.util.oi.DriverController;
import frc.core.util.oi.OperatorController;
import frc.robot.commands.IntakeMove.IntakeUpDown;
import frc.robot.commands.IntakePoker.PokeIntaker;
import frc.robot.commands.Poker.Poke;
import frc.robot.commands.autonomous.Auto1;
import frc.robot.commands.autonomous.Auto2;
import frc.robot.commands.autonomous.Auto3;
import frc.robot.commands.autonomous.Auto4;
import frc.robot.commands.autonomous.AutoNothing;
import frc.robot.commands.autonomous.BalanceRoutine;
import frc.robot.commands.autonomous.Test;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.PieceRumble;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.Shoot;
import frc.robot.commands.intake.ShootMid;
import frc.robot.commands.intake.ShootSuperHigh;
import frc.robot.commands.intake.ShootHigh;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.IntakePoker;
import frc.robot.subsystems.Poker;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private final IntakeMove intakeMove;
  private final Intake intake;
  private final IntakePoker intakePoker;
  private final Poker poker;

  public DriverController driver;
  private OperatorController operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intake = new Intake();
    this.intakePoker = new IntakePoker();
    this.intakeMove = new IntakeMove();
    this.poker = new Poker();

    this.driver = new DriverController();
    this.operator = new OperatorController();
    configureButtonBindings();

  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetainQuick();
    // this.buttonBindingsDrivetainSlow();
    this.buttonBindingsIntakeMove();
    this.buttonBindingsIntake();
    this.buttonBindingsReleaser();
    this.buttonBindingsReleaserTeste();
    // this.buttonBindingsPoke();
  }

  private void buttonBindingsDrivetainQuick() {
    this.drivetrain.setDefaultCommand(
        new ArcadeDrive(
            this.drivetrain,
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            driver));
  }


  private void buttonBindingsIntakeMove() {
    this.operator.whileYButton(new IntakeUpDown(intakeMove, () -> 0.92));
    this.operator.whileBButton(new IntakeUpDown(intakeMove, () -> -0.92));
  }

  // private void buttonBindingsPoke() {
  // this.driver.whileLeftBumper(new Poke(poker, true));
  // this.driver.whileRightBumper(new Poke(poker, false));
  // }

  private void buttonBindingsIntake() {
    this.operator.whileAButton(new Release(intake));
    this.operator.whileXButton(new ShootMid(intake));
    this.operator.whileRightBumper(new ShootHigh(intake));
    this.operator.whileXUp(new ShootSuperHigh(intake));
    this.operator.whileLeftBumper(new Collect(intake));


    this.operator.whileXLeft(new Shoot(intake, 0.39));
    this.operator.whileXRight(new Shoot(intake, 0.34));
  }

  private void buttonBindingsReleaser() {
    this.driver.whileRightBumper(new PokeIntaker(intakePoker));
  }

  private void buttonBindingsReleaserTeste() {
    this.operator.whileXDown(new PokeIntaker(intakePoker));
  }

  public Command getAutonomousCommand() {

    Command auto1 = new Auto1(drivetrain, poker, intake, intakeMove, intakePoker);
    Command auto2 = new Auto2(drivetrain, intake, intakeMove, poker, intakePoker);
    Command auto3 = new Auto3(drivetrain, poker, intake, intakeMove, intakePoker);
    Command auto4 = new Auto4(drivetrain, intake, intakeMove, poker);
    Command autoNothing = new AutoNothing(drivetrain, intake, intakeMove, poker);
    // Command auto = new Test(drivetrain, null);

    return auto1;
  }
}
