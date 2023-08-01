package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.core.util.oi.DriverController;
import frc.core.util.oi.OperatorController;

import frc.robot.subsystems.Intake;

public class PieceRumble extends CommandBase {
  private final Intake intake;
  private Timer timer;
  private OperatorController operator; 
  private DriverController driver;  

  public PieceRumble(Intake intake, OperatorController operator, DriverController driver) {
    this.intake = intake;
    this.timer = new Timer();
    this.operator = operator;
    this.driver = driver;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {
    timer.start();
  }

  @Override
  public void execute() {
    this.operator.enableRumble();
    this.driver.enableRumble();
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(2);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.operator.disableRumble();
    this.driver.disableRumble();
    this.timer.stop();
  }
}
