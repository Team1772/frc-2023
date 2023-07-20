package frc.core.util.oi;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drivetrain;

public class BalanceRumble extends CommandBase {
    private Drivetrain drivetrain;
    private Trigger isBalanced;
    private DriverController driverController;
    private Timer timer;

    public BalanceRumble(Drivetrain drivetrain, DriverController driverController) {
        this.drivetrain = drivetrain;
        this.driverController = driverController;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        this.isBalanced = new Trigger(() -> this.drivetrain.isBalanced());
        this.isBalanced.whileTrue(this.driverController.enableRumble());
        //this.isBalanced.whileFalse(this.driverController.disableRumble());
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(2);
    }

}
