package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;

public class Milmeditezi extends OpMode {
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private DcMotor leftRear = null;


    @Override
    public void init() {
        this.leftFront = hardwareMap.dcMotor.get("leftFront");
        this.rightFront = hardwareMap.dcMotor.get("rightFront");
        this.rightRear= hardwareMap.dcMotor.get("rightRear");
        this.leftRear= hardwareMap.dcMotor.get("leftRear");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        if(gamepad1.left_stick_x > 0.3 || gamepad1.left_stick_y > 0.3)
        {
            drive(gamepad1.left_stick_x, gamepad1.left_stick_y);
        }
        else
            {
                stopRobot();
            }
    }

    private void drive(float x, float y)
    {


    }

    private void stopRobot()
    {


    }
}
