package frc.robot.subsystems;

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
  