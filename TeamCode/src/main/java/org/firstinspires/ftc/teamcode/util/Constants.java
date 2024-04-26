package org.firstinspires.ftc.teamcode.util;

public class Constants {

    public static int DRIVER_CONTROLLER_ID = 0;

    public static String FRONT_LEFT_MOTOR_ID = "0";
    public static String BACK_LEFT_MOTOR_ID = "1";
    public static String FRONT_RIGHT_MOTOR_ID = "2";
    public static String BACK_RIGHT_MOTOR_ID = "3";

    public static double WHEEL_RADIUS = 0.0375;
    public static double DRIVETRAIN_MAX_RPM = 150 * (3/1);
    public static double DRIVETRAIN_MAX_VEL = ((Math.PI * 2) * (WHEEL_RADIUS * DRIVETRAIN_MAX_RPM)) / 60;
}
