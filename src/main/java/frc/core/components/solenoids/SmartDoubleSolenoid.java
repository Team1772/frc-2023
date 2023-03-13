package frc.core.components.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class SmartDoubleSolenoid {
	private DoubleSolenoid solenoid;

	public SmartDoubleSolenoid(int channelOne, int channelTwo) {
		this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, channelOne, channelTwo);
	}

	public Value getValue() {
		return this.solenoid.get();
	}

	public void enable() {
		this.solenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void disable() {
		this.solenoid.set(DoubleSolenoid.Value.kReverse); 
	}

	public void toggle() {
		this.solenoid.toggle();      
	}   
}