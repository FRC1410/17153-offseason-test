package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;

import static org.firstinspires.ftc.teamcode.util.Constants.*;
import static org.firstinspires.ftc.teamcode.util.Tuning.*;

public class Drivetrain {

    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;

    private PIDFController frontLeftPIDF;
    private PIDFController backLeftPIDF;
    private PIDFController frontRightPIDF;
    private PIDFController backRightPIDF;

    private Telemetry driveTelemetry;

    public Drivetrain() {
        this.frontLeftPIDF = new PIDFController(FRONT_LEFT_P, FRONT_LEFT_I, FRONT_LEFT_D, FRONT_LEFT_F);
        this.backLeftPIDF = new PIDFController(BACK_LEFT_P, BACK_LEFT_I, BACK_LEFT_D, BACK_LEFT_F);
        this.frontRightPIDF = new PIDFController(FRONT_RIGHT_P, FRONT_RIGHT_I, FRONT_RIGHT_D, FRONT_RIGHT_F);
        this.backRightPIDF = new PIDFController(BACK_RIGHT_P, BACK_RIGHT_I, BACK_RIGHT_D, BACK_RIGHT_F);
    }

    public void mechanicDrive(double leftStickX, double leftStickY, double rightStickX) {

        double joystickAngle = Math.atan2(leftStickY, leftStickX);
        double magnitude = Math.sqrt((Math.pow(leftStickX, 2) + Math.pow(rightStickX, 2)));

        double frontRightPower = Math.sin(joystickAngle - (1/(4 * Math.PI)) * magnitude);
        double backLeftPower = Math.sin(joystickAngle - (1/(4 * Math.PI)) * magnitude);

        double frontLeftPower = Math.sin(joystickAngle + (1/(4 * Math.PI)) * magnitude);
        double backRightPower = Math.sin(joystickAngle + (1/(4 * Math.PI)) * magnitude);

        this.frontLeft.setPower(-frontLeftPower);
        this.backLeft.setPower(-backLeftPower);
        this.frontRight.setPower(frontRightPower);
        this.backRight.setPower(backRightPower);

        this.driveTelemetry.update();
    }

    public void init(HardwareMap hardwareMap) {
        this.frontLeft = hardwareMap.get(DcMotorEx.class, FRONT_LEFT_MOTOR_ID);
        this.backLeft = hardwareMap.get(DcMotorEx.class, BACK_LEFT_MOTOR_ID);
        this.frontRight = hardwareMap.get(DcMotorEx.class, FRONT_RIGHT_MOTOR_ID);
        this.backRight = hardwareMap.get(DcMotorEx.class, BACK_RIGHT_MOTOR_ID);

        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        this.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.driveTelemetry.addData("FL power", frontLeft.getPower());
        this.driveTelemetry.addData("BL power", backLeft.getPower());
        this.driveTelemetry.addData("FR power", frontRight.getPower());
        this.driveTelemetry.addData("BR power", backRight.getPower());
    }
}