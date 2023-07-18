package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.core.util.oi.DriverController;
import frc.robot.subsystems.Drivetrain;

public class BalanceRumble extends CommandBase {
    private Drivetrain drivetrain;
    private Trigger isBalanced;
    private DriverController driverController;
    private boolean enableRumble;

    public BalanceRumble(Drivetrain drivetrain, DriverController driverController, boolean enableRumble) {
        this.drivetrain = drivetrain;
        this.driverController = driverController;
        this.enableRumble = enableRumble;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(enableRumble){
            this.isBalanced = new Trigger(() -> this.drivetrain.isBalanced());
            this.isBalanced.whileTrue(this.driverController.enableRumble());
            this.isBalanced.whileFalse(this.driverController.disableRumble());        
        }else{

        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
