package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "HolomonicLinear", group = "Autonomous")
public class HolonomicLinear extends LinearOpMode {


    DcMotor SF;
    DcMotor SS;
    DcMotor DF;
    DcMotor DS;



    float motorPower = 0.5f;

    @Override
    public void runOpMode() throws InterruptedException {
        SF = hardwareMap.get(DcMotor.class, "SF");
        SS = hardwareMap.get(DcMotor.class, "SS");
        DF = hardwareMap.get(DcMotor.class, "DF");
        DS = hardwareMap.get(DcMotor.class, "DS");

        telemetry.addData("Status: ","Initialized");

        waitForStart();

        /*
        for(int i = 0; i < 10; i++) {
            GoForward(800);

            //Turn(525);
            Turn(575); //90 grade
        }
        *//*
        GoForward(3000);
        Turn(595);
        GoForward(3000);
        //Turn(575);

        //GoForward(1000);

        Turn(595);

        GoForward(9000);
        */

        for(int i = 0; i < 10; i++) {
            GoForward(1000);
            GoLeft(1000);
            GoBackward(1000);
            GoRight(1000);

        }



        telemetry.addData("Status: ","Started");
    }

    public void GoForward(long ms)
    {
        DF.setDirection(DcMotor.Direction.REVERSE);
        DS.setDirection(DcMotor.Direction.REVERSE);

        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);

        ///wait(ms);
        sleep(ms);

        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);

        DF.setDirection(DcMotor.Direction.FORWARD);
        DS.setDirection(DcMotor.Direction.FORWARD);
    }

    public void GoBackward(long ms)
    {
        SF.setDirection(DcMotor.Direction.REVERSE);
        SS.setDirection(DcMotor.Direction.REVERSE);

        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);

        ///wait(ms);
        sleep(ms);

        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);

        SF.setDirection(DcMotor.Direction.FORWARD);
        SS.setDirection(DcMotor.Direction.FORWARD);
    }

    public void GoRight(long ms)
    {
        SF.setDirection(DcMotor.Direction.REVERSE);
        DF.setDirection(DcMotor.Direction.REVERSE);

        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);

        ///wait(ms);
        sleep(ms);


        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);

        SF.setDirection(DcMotor.Direction.FORWARD);
        DF.setDirection(DcMotor.Direction.FORWARD);
    }

    public void GoLeft(long ms)
    {
        SS.setDirection(DcMotor.Direction.REVERSE);
        DS.setDirection(DcMotor.Direction.REVERSE);

        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);

        ///wait(ms);
        sleep(ms);


        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);

        SS.setDirection(DcMotor.Direction.FORWARD);
        DS.setDirection(DcMotor.Direction.FORWARD);
    }

    public void Turn(long ms)
    {
        DF.setDirection(DcMotor.Direction.FORWARD);
        DS.setDirection(DcMotor.Direction.FORWARD);

        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);

        sleep(ms);

        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);

        DF.setDirection(DcMotor.Direction.REVERSE);
        DS.setDirection(DcMotor.Direction.REVERSE);

    }
}


