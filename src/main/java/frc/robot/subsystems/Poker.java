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
import frc.robot.constants.IntakeConstants;

public class Poker extends SubsystemBase {
  
    private SmartDoubleSolenoid solenoid;
  
  
    public Poker() {
      this.solenoid = new SmartDoubleSolenoid(IntakeConstants.Solenoid.solenoidOpen, IntakeConstants.Solenoid.solenoidClose);

    }

    public void poke() {
      this.solenoid.enable();
    }
    
    public void unpoke() {
      this.solenoid.disable();
    }
  
    @Override
    public void periodic() {
    }
  }
  