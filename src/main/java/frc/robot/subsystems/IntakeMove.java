package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMove extends SubsystemBase {
  
    private WPI_TalonSRX motorMove;
    private DigitalInput limit;
  
    public IntakeMove() {
      this.motorMove = new WPI_TalonSRX(5);
      this.motorMove.setInverted(false);
      this.limit = new DigitalInput(5);
  
    }
  
    public void set(double speed) {
      this.motorMove.set(ControlMode.PercentOutput, speed);
    }

    public boolean isLimit(){
      return this.limit.get();
    }
  
    public void stop() {
      this.motorMove.set(0);
    }
  
    @Override
    public void periodic() {
      SmartDashboard.putBoolean("Is limit", isLimit());
    }
  }
  