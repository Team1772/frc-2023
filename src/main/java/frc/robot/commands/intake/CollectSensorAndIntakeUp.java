package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.IntakeMove.IntakeUpToSensor;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;

public class CollectSensorAndIntakeUp extends CommandBase {

  private final Intake intake;
  private final IntakeMove intakeMove;
  private Timer timer;
  private boolean isFinished;

  public CollectSensorAndIntakeUp(Intake intake, IntakeMove intakeMove) {
    this.intake = intake;
    this.intakeMove = intakeMove;
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
    
    if(this.intake.isCollectedCube()){
        this.timer.stop();
        new IntakeUpToSensor(intakeMove);
        this.isFinished = true;
    }
  }

  @Override
  public boolean isFinished() {
    return isFinished || this.timer.hasElapsed(IntakeConstants.Seconds.autoCollectSeconds);
  }
  
  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
