package frc.core.util.oi;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class OperatorRumble extends CommandBase{

  private static final int ENABLED = 1;
  private static final int DISABLED = 0;

  private XboxController operator;

  private double rumble;

  public OperatorRumble(XboxController operator, boolean isEnabled) {
    this.operator = operator;

    this.rumble = isEnabled ? ENABLED : DISABLED;
  }

  @Override
  public void execute() {
    this.operator.setRumble(RumbleType.kLeftRumble, rumble);
  }

  @Override
  public void end(boolean isInterruptable) {
    this.operator.setRumble(RumbleType.kLeftRumble, DISABLED);
  }
}