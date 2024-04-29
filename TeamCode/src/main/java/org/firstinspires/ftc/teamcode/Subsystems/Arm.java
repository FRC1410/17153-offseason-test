package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.util.IDs.*;
import static org.firstinspires.ftc.teamcode.util.Constants.*;

public class Arm {
    private DcMotorEx armMotor;
    private int armPose = 0;

    public void runArmMotor(double armInput) {
        double increaseArmPose = armInput * MAX_ARM_ENCODER_POSE;

        armPose = armPose + (int)increaseArmPose;

        this.armMotor.setTargetPosition(armPose);
        this.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void runArmMotorSimple(double armInput) {
        this.armMotor.setPower(armInput);
    }

    public void init(HardwareMap hardwareMap) {
        this.armMotor = hardwareMap.get(DcMotorEx.class, ARM_MOTOR_ID);

        this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.armMotor.setTargetPosition(armPose);
        this.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
