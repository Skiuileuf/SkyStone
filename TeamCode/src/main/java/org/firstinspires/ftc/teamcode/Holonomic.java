package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Holonomic", group = "Autonomous")
public class Holonomic extends OpMode {

    DcMotor SF;
    DcMotor SS;
    DcMotor DF;
    DcMotor DS;

    float motorPower = 0.5f;

    @Override
    public void init() {
        SF = hardwareMap.get(DcMotor.class, "SF");
        SS = hardwareMap.get(DcMotor.class, "SS");
        DF = hardwareMap.get(DcMotor.class, "DF");
        DS = hardwareMap.get(DcMotor.class, "DS");

        DF.setDirection(DcMotor.Direction.REVERSE);
        DS.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status: ","Initialized");
    }

    @Override
    public void start()
    {

        GoForward(3000);

        telemetry.addData("Status: ","Started");
    }


    @Override
    public void loop() {

    }

    @Override
    public void stop()
    {
        telemetry.addData("Status: ","Stopped");

    }


    public void GoForward(long ms)
    {
        SF.setPower(motorPower);
        SS.setPower(motorPower);
        DF.setPower(motorPower);
        DS.setPower(motorPower);


        SF.setPower(0);
        SS.setPower(0);
        DF.setPower(0);
        DS.setPower(0);
    }
}
