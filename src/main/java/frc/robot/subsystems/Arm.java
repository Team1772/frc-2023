package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ArmConstants;

public class Arm extends SubsystemBase {

  private static final NeutralMode BRAKE_MODE = NeutralMode.Brake;
  private final TalonSRX motorLeft;
  private final TalonSRX motorRight;
  
  public Arm() {
    var motorLeft = new TalonSRX(ArmConstants.leftMotorPort);
    var motorRight = new TalonSRX(ArmConstants.rightMotorPort);

    this.motorLeft = motorLeft;
    this.motorRight = motorRight;

    this.motorLeft.setInverted(ArmConstants.isMotorLeftInverted);
    this.motorRight.setInverted(ArmConstants.isMotorRightInverted);

    this.motorLeft.setNeutralMode(BRAKE_MODE);
    this.motorRight.setNeutralMode(BRAKE_MODE);
  }

  public void set(double speed) {
    // this.motorLeft.set(ControlMode.PercentOutput, speed * 0.75);
    this.motorRight.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    this.motorLeft.set(ControlMode.PercentOutput, 0);
    this.motorRight.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
  }
}

