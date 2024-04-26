package org.firstinspires.ftc.teamcode.util;

public class Constants {

    // Drivetrain constants
    public static double DRIVETRAIN_ENCODER_TICKS = 28;
    public static double WHEEL_RADIUS = 0.0375;
    public static double DRIVETRAIN_GEAR_RATIO = 3;
    public static double DRIVETRAIN_MAX_RPM = 6000 * 3;
    public static double DRIVETRAIN_MAX_VEL = ((Math.PI * 2) * (WHEEL_RADIUS * DRIVETRAIN_MAX_RPM)) / 60;
}
