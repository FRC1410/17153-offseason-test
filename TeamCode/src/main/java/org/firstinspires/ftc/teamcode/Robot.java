package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.util.Constants.*;
import static org.firstinspires.ftc.teamcode.util.IDs.*;

public class Robot extends OpMode {

    private final Gamepad driverController = new Gamepad();
    private final Gamepad operatorController = new Gamepad();
    private final Drivetrain drivetrain = new Drivetrain();

    @Override
    public void init() {
        this.driverController.setGamepadId(DRIVER_CONTROLLER_ID);
        this.operatorController.setGamepadId(OPERATOR_CONTROLLER_ID);
        this.drivetrain.init(hardwareMap);
    }

    @Override
    public void loop() {
        this.drivetrain.mechanicDrive(
                driverController.left_stick_x * DRIVETRAIN_MAX_VEL * 1.1,
                -driverController.left_stick_y * DRIVETRAIN_MAX_VEL,
                driverController.right_stick_x * DRIVETRAIN_MAX_VEL
        );
    }
}
