package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.Constants.*;

public class Drivetrain {

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    private Telemetry driveTelemetry;

    public void mecanumDrive(double leftStickX, double leftStickY, double rightStickX) {

        double joystickAngle = Math.atan2(leftStickY, leftStickX);
        double magnitude = Math.sqrt((Math.pow(leftStickX, 2) + Math.pow(rightStickX, 2)));

        double frontRightPower = Math.sin(joystickAngle - (1/(4 * Math.PI)) * magnitude);
        double backLeftPower = Math.sin(joystickAngle - (1/(4 * Math.PI)) * magnitude);

        double frontLeftPower = Math.sin(joystickAngle + (1/(4 * Math.PI)) * magnitude);
        double backRightPower = Math.sin(joystickAngle + (1/(4 * Math.PI)) * magnitude);

        frontLeft.setPower(-frontLeftPower);
        backLeft.setPower(-backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        driveTelemetry.update();
    }

    public void init(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get(FRONT_LEFT_MOTOR_ID);
        backLeft = hardwareMap.dcMotor.get(BACK_LEFT_MOTOR_ID);
        frontRight = hardwareMap.dcMotor.get(FRONT_RIGHT_MOTOR_ID);
        backRight = hardwareMap.dcMotor.get(BACK_RIGHT_MOTOR_ID);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        driveTelemetry.addData("FL power", frontLeft.getPower());
        driveTelemetry.addData("BL power", backLeft.getPower());
        driveTelemetry.addData("FR power", frontRight.getPower());
        driveTelemetry.addData("BR power", backRight.getPower());
    }
}
