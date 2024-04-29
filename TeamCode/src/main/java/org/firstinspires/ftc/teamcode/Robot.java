package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.util.Constants.*;
import static org.firstinspires.ftc.teamcode.util.IDs.*;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.SlidingElevator;

@TeleOp
public class Robot extends OpMode {

    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final SlidingElevator slidingElevator = new SlidingElevator();

    @Override
    public void init() {
        this.drivetrain.init(hardwareMap);

        this.intake.init(hardwareMap);

        this.slidingElevator.init(hardwareMap);
    }

    @Override
    public void loop() {
        this.drivetrain.mechanicDrive(
                gamepad1.left_stick_x * DRIVETRAIN_MAX_VEL * 1.1,
                -gamepad1.left_stick_y * DRIVETRAIN_MAX_VEL,
                gamepad1.right_stick_x * DRIVETRAIN_MAX_VEL,
                telemetry
        );

        this.intake.runIntake(
                gamepad1.left_trigger * -1,
                gamepad1.right_stick_x
        );

        this.slidingElevator.simpleRunElevator(
                gamepad2.left_trigger * -1,
                gamepad2.right_trigger
        );

//        this.slidingElevator.goToElevatorPosition(
//                gamepad2.dpad_up,
//                gamepad2.dpad_left,
//                gamepad2.dpad_down,
//                gamepad2.left_bumper
//        );
    }
}
