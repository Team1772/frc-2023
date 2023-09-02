package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class IntakePoker extends SubsystemBase {
  
    private WPI_TalonSRX motorUpper;
  
    public IntakePoker() {
      this.motorUpper = new WPI_TalonSRX(IntakeConstants.Motors.motorUpper);
      this.motorUpper.setInverted(IntakeConstants.Motors.isMotorUpperInverted);
    }
  
    public void setDouble(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
    }

    public void setUpper(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
    }
  
    public void stopDouble() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
    }

    public void stopUpper() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
    }    
  
    @Override
    public void periodic() {
    }
  }
  