package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.TelescopeConstants;

public class Telescope extends SubsystemBase {
  private final TalonSRX motor;
  
  public Telescope() {
    var motor = new TalonSRX(TelescopeConstants.motorPort);
    this.motor = motor;

    this.motor.setInverted(TelescopeConstants.isInverted);

    this.motor.setNeutralMode(NeutralMode.Brake);
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

