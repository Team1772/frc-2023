package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.core.components.Limelight;
import frc.core.components.Limelight.LedMode;
import frc.robot.constants.LimelightConstants;
import frc.robot.subsystems.Drivetrain; 

public class AimTarget extends CommandBase {
  private Drivetrain drivetrain;

  public AimTarget(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    Limelight.setLed(LedMode.ON);
    Limelight.setPipeline(LimelightConstants.tapePipeline);
  }

  @Override
  public void execute() {
    double x = Limelight.getX(),
      headingError = -(x),
      adjust = 0;

      System.out.println("x " + x);

    if (x > 1) {
      adjust = LimelightConstants.AimTarget.kP * 
                headingError - 
                LimelightConstants.AimTarget.minCommand;
    } else if (x < 1) { 
      adjust = LimelightConstants.AimTarget.kP * 
                headingError + 
                LimelightConstants.AimTarget.minCommand;
    }
    double rightSpeed = 0,
      leftSpeed = 0;
    rightSpeed -= adjust;
    leftSpeed += adjust;

    this.drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.setLed(LedMode.OFF);
  }
}