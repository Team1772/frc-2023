package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class CollectSensor extends CommandBase {

  private final Intake intake;
  private Timer timer;

  public CollectSensor(Intake intake) {
    this.intake = intake;
    this.timer = new Timer();
    addRequirements(this.intake);
  }


  @Override
  public void initialize() {
    this.timer.start();
  }

  @Override
  public void execute() {
    this.intake.set(IntakeConstants.Speeds.speedCollect);
  }

  @Override
  public boolean isFinished() {
    return this.intake.isCollectedCube() || this.timer.hasElapsed(IntakeConstants.Seconds.autoCollectSeconds);
  }
  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
