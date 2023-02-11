package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private final VictorSPX motor;
  
  public Intake() {
    var motor = new VictorSPX(IntakeConstants.motorPort);
    this.motor = motor;

    this.motor.setInverted(IntakeConstants.isInverted);

    this.motor.setNeutralMode(NeutralMode.Coast);
  }

  public void set(double speed) {
    this.motor.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    this.motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {}
}

