package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.util.Constants.*;
import static org.firstinspires.ftc.teamcode.util.Tuning.*;
import static org.firstinspires.ftc.teamcode.util.IDs.*;

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

        this.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void mechanicDrive(double xVelocity, double yVelocity, double angularVelocity) {

        double denominator =
                Math.max(
                        Math.abs(xVelocity) + Math.abs(yVelocity) + Math.abs(angularVelocity), 1);

        this.frontLeft.setVelocity((yVelocity + xVelocity + angularVelocity) / denominator);
        this.backLeft.setVelocity((yVelocity - xVelocity + angularVelocity) / denominator);
        this.frontRight.setVelocity((yVelocity - xVelocity - angularVelocity) / denominator);
        this.backRight.setVelocity((yVelocity + xVelocity - angularVelocity) / denominator);

        this.driveTelemetry.update();
    }

    // TODO: There might be a bug here causing a null pointer exception
    public double[] getVelocity() {
        double frontLeftVelocity = this.frontLeft.getVelocity() / DRIVETRAIN_ENCODER_TICKS / DRIVETRAIN_GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS;
        double backLeftVelocity = this.frontLeft.getVelocity() / DRIVETRAIN_ENCODER_TICKS / DRIVETRAIN_GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS;
        double frontRightVelocity = this.frontLeft.getVelocity() / DRIVETRAIN_ENCODER_TICKS / DRIVETRAIN_GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS;
        double backRightVelocity = this.frontLeft.getVelocity() / DRIVETRAIN_ENCODER_TICKS / DRIVETRAIN_GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS;

        double xVelocity = (frontLeftVelocity + backLeftVelocity + frontRightVelocity + backRightVelocity) / 4;
        double yVelocity = (backLeftVelocity + frontRightVelocity - frontLeftVelocity - backRightVelocity) / 4;
        double angularVelocity = (frontLeftVelocity + backLeftVelocity - frontRightVelocity - backRightVelocity) /4;

        double robotVelocity[] = {xVelocity, yVelocity, angularVelocity};
        return robotVelocity;
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

        this.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.driveTelemetry.addData("FL power", frontLeft.getPower());
        this.driveTelemetry.addData("BL power", backLeft.getPower());
        this.driveTelemetry.addData("FR power", frontRight.getPower());
        this.driveTelemetry.addData("BR power", backRight.getPower());
    }
}