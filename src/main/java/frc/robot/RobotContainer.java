package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.DriverController;
import frc.core.util.oi.OperatorController;
import frc.robot.commands.autonomous.BalanceRoutine;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.BalanceRumble;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private boolean enableRumble;
  
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
    this.buttonBindingsTeste();
  }

  
  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
      new ArcadeDrive(
        this.drivetrain, 
        () -> -driver.getLeftY(),
        () -> driver.getRightX()
      )
    );

    this.driver.getBButton().toggleOnTrue(
      new BalanceRumble(this.drivetrain, this.driver, enableRumble)
    );



    
  }

  private void buttonBindingsTeste(){
    this.driver.getYButton().toggleOnTrue(driver.enableRumble());
    //this.driver.whileAButton(driver.enableRumble());
  }

  public Command getAutonomousCommand() {
    Command auto = new BalanceRoutine(drivetrain);

    return auto;
  }
}
