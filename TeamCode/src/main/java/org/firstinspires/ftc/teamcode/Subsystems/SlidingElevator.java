package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.util.IDs.*;

public class SlidingElevator {

    private DcMotorEx slidingElevatorMotor;
    private int desiredPosition = 0;

    enum ElevatorState {
        LOW,
        MEDIUM,
        HIGH,
        HOME
    }

    public int setDesiredState(ElevatorState state) {
        switch (state) {
            case HIGH:
                this.desiredPosition = 1000;
                this.slidingElevatorMotor.setTargetPosition(desiredPosition);

            case MEDIUM:
                this.desiredPosition = 500;
                this.slidingElevatorMotor.setTargetPosition(desiredPosition);

            case LOW:
                this.desiredPosition = 100;
                this.slidingElevatorMotor.setTargetPosition(desiredPosition);

            case HOME:
                this.desiredPosition = 0;
                this.slidingElevatorMotor.setTargetPosition(desiredPosition);
        }
        return this.desiredPosition;
    }

    public void goToElevatorPosition(boolean high, boolean mid, boolean low, boolean home) {
        if(high) {
            this.setDesiredState(ElevatorState.HIGH);
        } else if(mid) {
            this.setDesiredState(ElevatorState.MEDIUM);
        } else if(low) {
            this.setDesiredState(ElevatorState.LOW);
        } else if(home) {
            this.setDesiredState(ElevatorState.HOME);
        }

        this.slidingElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void simpleRunElevator(double extendVelocity, double retractVelocity) {
        this.slidingElevatorMotor.setPower(extendVelocity - retractVelocity);
    }

    public void init(HardwareMap hardwareMap) {
        this.slidingElevatorMotor = hardwareMap.get(DcMotorEx.class, SLIDING_ELEVATOR_ID);
        this.slidingElevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.slidingElevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.slidingElevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.slidingElevatorMotor.setTargetPosition(setDesiredState(ElevatorState.HOME));
        this.slidingElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
