package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.core.components.Limelight;
import frc.core.util.NumberUtil;
import frc.robot.constants.LimelightConstants;
import frc.robot.subsystems.Drivetrain;

//TODO: Test if it needs angle adjust
public class BalanceChargeStation extends CommandBase {

  private static final double LOST_APRIL_TAG_TIMER_LIMIT = 3.0;
  private static final int ERROR_THRESHOLD = 5;
  private static final int POSITIVE_ERROR_THRESHOLD = ERROR_THRESHOLD;
  private static final int NEGATIVE_ERROR_THRESHOLD = NumberUtil.invert(ERROR_THRESHOLD);
  private static final double DRIVETRAIN_VELOCITY = 0.35;
  private static final double FORWARD_VELOCITY = DRIVETRAIN_VELOCITY;
  private static final double REVERSE_VELOCITY = NumberUtil.invert(DRIVETRAIN_VELOCITY);

  private Timer lostAprilTagTimer; 

  private boolean isUnbalancedNextAprilTag;
  private boolean isTimerStarted;
  private boolean isRobotLost;

  private Drivetrain drivetrain;

  public BalanceChargeStation(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    this.lostAprilTagTimer = new Timer();

    this.isUnbalancedNextAprilTag = true;
    this.isTimerStarted = false;
    this.isRobotLost = false;
    
    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    Limelight.aprilTagPipelineMode();

    this.lostAprilTagTimer.start();
  }

  @Override
  public void execute() {
    var kY = Limelight.getY();
    var isOnTarget = Limelight.getV() > 0;

    if(!isOnTarget) {

      if (!isTimerStarted) {
        this.isTimerStarted = true;

        this.lostAprilTagTimer.reset();
      }
      
      if (isUnbalancedNextAprilTag) {
        this.driveReverse();
      } else {
        this.driveForward();
      }

      if (lostAprilTagTimer.hasElapsed(LOST_APRIL_TAG_TIMER_LIMIT)) {
        this.isRobotLost = true;
      }

    }

    if (isOnTarget) {

      this.lostAprilTagTimer.reset();

      if (kY > POSITIVE_ERROR_THRESHOLD) {
        this.driveReverse();
        isUnbalancedNextAprilTag = true;
  
      } 
      
      if (kY < NEGATIVE_ERROR_THRESHOLD) {
        this.driveForward();
        isUnbalancedNextAprilTag = false;
      }
    }
  }

  public boolean isBalanced(double kY) {
    return kY < NumberUtil.absolute(ERROR_THRESHOLD);
  }

  public void driveForward() {
    this.drivetrain.arcadeDrive(FORWARD_VELOCITY, 0);
  }

  public void driveReverse() {
    this.drivetrain.arcadeDrive(REVERSE_VELOCITY, 0);
  }

  @Override
  public boolean isFinished() {

    return isRobotLost;
  }

  @Override
  public void end(boolean isInterrupted) {
    this.drivetrain.arcadeDrive(0, 0);

    this.lostAprilTagTimer.stop();

    Limelight.setPipeline(LimelightConstants.driverPipeline);
  }
}
