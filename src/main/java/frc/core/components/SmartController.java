package frc.core.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class SmartController extends XboxController {
  private XboxController controller;
  
  private JoystickButton buttonA;
  private JoystickButton buttonB;
  private JoystickButton buttonX;
  private JoystickButton buttonY;
  private JoystickButton leftBumper;
  private JoystickButton rightBumper;
  private JoystickButton buttonLeftStick;
  private JoystickButton buttonRightStick;

  public SmartController(int controllerPort) {
    super(controllerPort);

    this.controller = new XboxController(controllerPort);

    this.buttonA = new JoystickButton(controller, Button.kA.value);
    this.buttonB = new JoystickButton(controller, Button.kB.value);
    this.buttonX = new JoystickButton(controller, Button.kX.value);
    this.buttonY = new JoystickButton(controller, Button.kY.value);
    this.leftBumper = new JoystickButton(controller, Button.kLeftBumper.value);
    this.rightBumper = new JoystickButton(controller, Button.kRightBumper.value);
    this.buttonLeftStick = new JoystickButton(controller, Button.kLeftStick.value);
    this.buttonRightStick = new JoystickButton(controller, Button.kRightStick.value);
  }

  public JoystickButton getButtonA() {
    return this.buttonA;
  }

  public JoystickButton getButtonB() {
    return this.buttonB;
  }

  public JoystickButton getButtonX() {
    return this.buttonX;
  }

  public JoystickButton getButtonY() {
    return this.buttonY;
  }

  public JoystickButton getBumperLeft() {
    return this.leftBumper;
  }

  public JoystickButton getBumperRight() {
    return this.rightBumper;
  }

  public JoystickButton getButtonLeftStick() {
    return this.buttonLeftStick;
  }

  public JoystickButton getButtonRightStick() {
    return this.buttonRightStick;
  }

  public double getLeftStickY() {
    return -this.controller.getLeftY();
  }

  public double getRightStickX() {
    return -this.controller.getRightX();
  }

  public double getLeftTrigger() {
    return this.controller.getLeftTriggerAxis();
  }

  public double getRigtTrigger() {
    return this.controller.getRightTriggerAxis();
  }
}
