package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.components.solenoids.SmartDoubleSolenoid;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private SmartDoubleSolenoid solenoid;
  
  public Intake() {
    this.solenoid = new SmartDoubleSolenoid(IntakeConstants.channelOne, IntakeConstants.channelTwo);
  }

  public void open() {
    this.solenoid.enable();
  }

  public void close() {
    this.solenoid.disable();
  }

  public void toggle() {
    this.solenoid.toggle();
  }


  @Override
  public void periodic() {}
}

