package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Libs.GAMEPAD;
import org.firstinspires.ftc.teamcode.Libs.Utils;

public class Drive {
    private GAMEPAD gamepad1 = null;
    private Telemetry telemetry = null;


    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private DcMotor leftRear = null;


    Drive(HardwareMap hardwareMap, GAMEPAD gamepad1, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        initMecanum(hardwareMap);

    }

    private void initMecanum(HardwareMap hardwareMap) {
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

    private void setPower(double a, double b, double c, double d) {
        if (gamepad1.a.toggle){
            leftFront.setPower(a * 0.4);
            rightFront.setPower(b * 0.4);
            rightRear.setPower(c * 0.4);
            leftRear.setPower(d * 0.4);
        }
        else{
            leftFront.setPower(a);
            rightFront.setPower(b);
            rightRear.setPower(c);
            leftRear.setPower(d);
        }
    }

    void goTeleOp() {
        goMecanum(gamepad1.left_stick_powerY, gamepad1.left_stick_powerX, gamepad1.right_stick_powerX);
    }

    private void goMecanum(double left_stick_powerY, double left_stick_powerX, double right_stick_powerX) {

        double lf;
        double rf;
        double rr;
        double lr;
/*
        a = +left_stick_powerY - left_stick_powerX + right_stick_powerX;
        b = +left_stick_powerY + left_stick_powerX - right_stick_powerX;
        c = +left_stick_powerY - left_stick_powerX - right_stick_powerX;
        d = +left_stick_powerY + left_stick_powerX + right_stick_powerX;
*/
        /*
        lf = +left_stick_powerY - left_stick_powerX + right_stick_powerX;
        rf = +left_stick_powerY + left_stick_powerX - right_stick_powerX;
        rr = +left_stick_powerY - left_stick_powerX - right_stick_powerX;
        lr = +left_stick_powerY + left_stick_powerX + right_stick_powerX;
        */

        lf = +left_stick_powerY + left_stick_powerX + (right_stick_powerX * 1);
        rf = +left_stick_powerY - left_stick_powerX - (right_stick_powerX * 1);
        rr = +left_stick_powerY + left_stick_powerX - (right_stick_powerX * 1);
        lr = +left_stick_powerY - left_stick_powerX + (right_stick_powerX * 1);

        lf = Utils.cut(lf, -1d, 1d);
        rf = Utils.cut(rf, -1d, 1d);
        rr = Utils.cut(rr, -1d, 1d);
        lr = Utils.cut(lr, -1d, 1d);

        setPower(lf, rf, rr, lr);
    }

}