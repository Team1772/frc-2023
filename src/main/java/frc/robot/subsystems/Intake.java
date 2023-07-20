package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  
    private WPI_TalonSRX motorUpper;
    private WPI_TalonSRX motorLower;
  
    private boolean isReleaseCargo;
  
    public Intake() {
      this.motorUpper = new WPI_TalonSRX(IntakeConstants.Motors.motorUpper);
      this.motorUpper.setInverted(true);

      this.motorLower = new WPI_TalonSRX(IntakeConstants.Motors.motorLower);
      this.motorLower.setInverted(false);
  
      this.isReleaseCargo = false;
    }

    public boolean isReleaseCargo() {
      return this.isReleaseCargo;
    }
    
    public void setReleaseCargo(boolean isReleaseCargo) {
      this.isReleaseCargo = isReleaseCargo;
    }
  
    public void set(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
      this.motorLower.set(ControlMode.PercentOutput, speed);
    }
  
    public void stop() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
      this.motorLower.set(ControlMode.PercentOutput, 0);
    }
  
    @Override
    public void periodic() {
        
    }
  }
  