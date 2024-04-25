package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

public class Robot extends OpMode {

    private final Gamepad driverController = new Gamepad();

    private final Drivetrain drivetrain = new Drivetrain();

    @Override
    public void init() {
        driverController.setGamepadId(DRIVER_CONTROLLER_ID);
        drivetrain.init(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrain.mechanicDrive(
                driverController.left_stick_x,
                driverController.left_stick_y,
                driverController.right_stick_x
        );
    }
}
