package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Libs.GAMEPAD;

@TeleOp(name="MilImprumuti", group="Pushbot")
@Disabled
public class Milimprumuti extends OpMode {

    //Unghiuri
    double reset_angle = 0;
    //Declaratii motoare
    private DcMotor front_left_wheel = null;
    private DcMotor back_left_wheel = null;
    private DcMotor back_right_wheel = null;
    private DcMotor front_right_wheel = null;
    //Gamepad ca sa suporte toggle
    private GAMEPAD gamepad = new GAMEPAD(this.gamepad1, this.telemetry);
    //Declaratii servo
    private Servo gheara_unu = null;
    private Servo gheara_doi = null;

    @Override
    public void init() {
        //Initializare motoare si servo motoare
        front_left_wheel = hardwareMap.dcMotor.get("leftFront");
        back_left_wheel = hardwareMap.dcMotor.get("leftRear");
        back_right_wheel = hardwareMap.dcMotor.get("rightRear");
        front_right_wheel = hardwareMap.dcMotor.get("rightFront");

        gheara_unu = hardwareMap.servo.get("agataStanga");
        gheara_doi = hardwareMap.servo.get("agataDreapta");


        //Setare directii motoare
        gheara_unu.setDirection(Servo.Direction.FORWARD);
        gheara_doi.setDirection(Servo.Direction.REVERSE);

        front_left_wheel.setDirection(DcMotor.Direction.FORWARD);
        back_left_wheel.setDirection(DcMotor.Direction.FORWARD);

        front_right_wheel.setDirection(DcMotor.Direction.REVERSE);
        back_right_wheel.setDirection(DcMotor.Direction.REVERSE);
        //Setare comportament cand power = 0
        front_left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        drive();
        telemetry.update();
    }

    public void drive() {
        double Protate = gamepad1.right_stick_x/4;
        double stick_x = gamepad1.left_stick_x * Math.sqrt(Math.pow(1-Math.abs(Protate), 2)/2); //Accounts for Protate when limiting magnitude to be less than 1
        double stick_y = gamepad1.left_stick_y * Math.sqrt(Math.pow(1-Math.abs(Protate), 2)/2);
        double theta = 0;
        double Px = 0;
        double Py = 0;


        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAngle into radians
        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

        if(gamepad1.right_bumper){ //Disables gyro, sets to -Math.PI/2 so front is defined correctly.
            gyroAngle = -Math.PI/2;
        }

        //Linear directions in case you want to do straight lines.
        if(gamepad1.dpad_right){
            stick_x = 0.5;
        }
        else if(gamepad1.dpad_left){
            stick_x = -0.5;
        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }

        if(gamepad.x.toggle)
        {
            gheara_unu.setPosition(1);
            gheara_doi.setPosition(1);
        }
        else
        {
            gheara_unu.setPosition(0);
            gheara_doi.setPosition(0);
        }

        //MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta - Math.PI / 4));

        telemetry.addData("Stick_X", stick_x);
        telemetry.addData("Stick_Y", stick_y);
        telemetry.addData("Magnitude",  Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)));
        telemetry.addData("Front Left", Py - Protate);
        telemetry.addData("Back Left", Px - Protate);
        telemetry.addData("Back Right", Py + Protate);
        telemetry.addData("Front Right", Px + Protate);
        telemetry.addData("Servo", gheara_unu.getPosition());

        front_left_wheel.setPower(Py - Protate);
        back_left_wheel.setPower(Px - Protate);
        back_right_wheel.setPower(Py + Protate);
        front_right_wheel.setPower(Px + Protate);
    }
    public double getHeading(){
        //Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        //double heading = angles.firstAngle;\
        double heading = 0;
        if(heading < -180) {
            heading = heading + 360;
        }
        else if(heading > 180){
            heading = heading - 360;
        }
        heading = heading - reset_angle;
        return heading;
    }
}
