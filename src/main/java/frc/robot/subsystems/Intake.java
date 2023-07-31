package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.components.solenoids.SmartDoubleSolenoid;
import frc.robot.commands.intake.PieceRumble;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  
    private WPI_TalonSRX motorUpper;
    private WPI_TalonSRX motorLower;
    private DigitalInput cubeInfrared;
  
    public Intake() {
      this.motorUpper = new WPI_TalonSRX(IntakeConstants.Motors.motorUpper);
      this.motorUpper.setInverted(IntakeConstants.Motors.isMotorUpperInverted);

      this.motorLower = new WPI_TalonSRX(IntakeConstants.Motors.motorLower);
      this.motorLower.setInverted(IntakeConstants.Motors.isMotorLowerInverted);

      this.cubeInfrared = new DigitalInput(IntakeConstants.Sensor.cubeInfrared);
    }
    
    public boolean isCollectedCube(){
      return !this.cubeInfrared.get();
    } 
  
    public void set(double speed) {
      this.motorUpper.set(ControlMode.PercentOutput, speed);
      this.motorLower.set(ControlMode.PercentOutput, 1 * speed);
    }
  
    public void stop() {
      this.motorUpper.set(ControlMode.PercentOutput, 0);
      this.motorLower.set(ControlMode.PercentOutput, 0);
    }
  
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Collected Cube", isCollectedCube());
    }
  }
  