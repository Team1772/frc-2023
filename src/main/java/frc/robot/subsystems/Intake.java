package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  
    private WPI_TalonSRX motorUpper;
    private Encoder encoder;
    private WPI_TalonSRX motorLower;
    private DigitalInput cubeInfrared;
  
    public Intake() {
      this.motorUpper = new WPI_TalonSRX(IntakeConstants.Motors.motorUpper);
      this.motorUpper.setInverted(IntakeConstants.Motors.isMotorUpperInverted);

      this.motorLower = new WPI_TalonSRX(IntakeConstants.Motors.motorLower);
      this.motorLower.setInverted(IntakeConstants.Motors.isMotorLowerInverted);

      this.encoder = new Encoder(IntakeConstants.Encoders.encoderPortOne, IntakeConstants.Encoders.encoderPortTwo);
      this.cubeInfrared = new DigitalInput(IntakeConstants.Sensor.cubeInfrared);
    }
    
    public boolean isCollectedCube(){
      return !this.cubeInfrared.get();
    } 
  
    public void setDouble(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
      this.motorLower.set(ControlMode.PercentOutput, speed);
    }

    public void setUpper(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
    }

    public void setLower(double speed) {
      this.motorLower.set(ControlMode.PercentOutput, speed);
    }
  
    public void stopDouble() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
      this.motorLower.set(ControlMode.PercentOutput, 0);
    }

    public void stopUpper() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
    }

    public void stopLower() {
      this.motorLower.set(ControlMode.PercentOutput, 0);
    }

    
  
    @Override
    public void periodic() {
      SmartDashboard.putBoolean("Collected Cube", isCollectedCube());
      SmartDashboard.putNumber("Encoder Intake", encoder.get());
    }
  }
  