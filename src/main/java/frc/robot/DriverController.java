package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class DriverController {

    public final XboxController controller = new XboxController(0);

    public final JoystickButton xButton = new JoystickButton(controller, XboxController.Button.kX.value);
    public final JoystickButton aButton = new JoystickButton(controller, XboxController.Button.kA.value);
    public final JoystickButton bButton = new JoystickButton(controller, XboxController.Button.kB.value);
    public final JoystickButton yButton = new JoystickButton(controller, XboxController.Button.kY.value);
    public final JoystickButton leftBumper = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
    public final JoystickButton rightBumper = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
    public final JoystickButton startButton = new JoystickButton(controller, 6);
    public final JoystickButton selectButton = new JoystickButton(controller, 7);
    public final POVButton XUp = new POVButton(controller, 0);
    public final POVButton XDown = new POVButton(controller, 180);
    public final POVButton XRight = new POVButton(controller, 90);
    public final POVButton XLeft = new POVButton(controller, 270);

    public double getLeftX() {
        return controller.getLeftX();
    }

    public double getLeftY() {
        return controller.getLeftY();
    }

    public double getRightX() {
        return controller.getRightX();
    }
    
    public double getRightY() {
        return controller.getRightY();
    }

}
