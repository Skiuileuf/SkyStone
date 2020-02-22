//Cod oferit de Autovortex
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {
    private org.firstinspires.ftc.teamcode.GAMEPAD gamepad1 = null;
    private org.firstinspires.ftc.teamcode.GAMEPAD gamepad2 = null;
    private Telemetry telemetry = null;


    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private DcMotor leftRear = null;


    Drive(HardwareMap hardwareMap, org.firstinspires.ftc.teamcode.GAMEPAD gamepad1, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        //this.gamepad2   = gamepad2;
        initMecanum(hardwareMap);

    }

    private void initMecanum(HardwareMap hardwareMap) {
        //Aici se declara numele motoarelor din configuratie
        this.leftFront = hardwareMap.dcMotor.get("leftFront");
        this.rightFront = hardwareMap.dcMotor.get("rightFront");
        this.rightRear= hardwareMap.dcMotor.get("rightRear");
        this.leftRear= hardwareMap.dcMotor.get("leftRear");

        //Se seteaza directia de mers
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        //Se seteaza comportamentul pentru power = 0;
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

        double a;
        double b;
        double c;
        double d;

        a = +left_stick_powerY - left_stick_powerX + right_stick_powerX;
        b = +left_stick_powerY + left_stick_powerX - right_stick_powerX;
        c = +left_stick_powerY - left_stick_powerX - right_stick_powerX;
        d = +left_stick_powerY + left_stick_powerX + right_stick_powerX;


        a = org.firstinspires.ftc.teamcode.Utils.cut(a, -1d, 1d);
        b = org.firstinspires.ftc.teamcode.Utils.cut(b, -1d, 1d);
        c = org.firstinspires.ftc.teamcode.Utils.cut(c, -1d, 1d);
        d = org.firstinspires.ftc.teamcode.Utils.cut(d, -1d, 1d);

        setPower(a, b, c, d);
    }

}