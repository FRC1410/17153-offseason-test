package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.util.IDs.*;

public class Intake {

    private DcMotorEx intakeMotor;

    public void runIntake(double intakeTrigger, double outtakeTrigger) {
        this.intakeMotor.setPower(intakeTrigger - outtakeTrigger);
    }

    public void init(HardwareMap hardwareMap) {
        this.intakeMotor = hardwareMap.get(DcMotorEx.class, INTAKE_MOTOR_ID);

        this.intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.intakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        this.intakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
}
