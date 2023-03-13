package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.components.solenoids.SmartDoubleSolenoid;
import frc.robot.constants.TelescopeConstants;

public class Telescope extends SubsystemBase {
  private final TalonSRX motor;
  private DigitalInput limit;
  
  public Telescope() {
    var motor = new TalonSRX(TelescopeConstants.motorPort);
    this.motor = motor;

    this.motor.setInverted(TelescopeConstants.isInverted);

    this.motor.setNeutralMode(NeutralMode.Brake);

    this.limit = new DigitalInput(5);
  }

  public void set(double speed) {
    this.motor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isLimit() {
    return !this.limit.get();
  }

  public void stop() {
    this.motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("is limit", this.isLimit());
  }
}
