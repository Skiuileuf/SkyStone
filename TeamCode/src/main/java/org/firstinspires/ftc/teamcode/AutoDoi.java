package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(group = "Autonom",name="Autojava2")
@Disabled
public class AutoDoi extends LinearOpMode {


    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftRear;
    DcMotor rightRear;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set left motor to run to 5000 encoder counts.



        // set both motors to 25% power. Movement will start.



        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && leftFront.isBusy() || rightRear.isBusy())
        {
            telemetry.addData("encoder-fwd", leftFront.getCurrentPosition() + "  busy=" + leftFront.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.



        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-end", leftFront.getCurrentPosition() + "  busy=" + leftFront.isBusy());
            telemetry.update();
            idle();
        }

        // Now back up to starting point. In this example instead of
        // having the motor monitor the encoder, we will monitor the encoder ourselves.



        while (opModeIsActive() && leftFront.getCurrentPosition() > 0 || rightRear.getCurrentPosition() > 0)
        {
            telemetry.addData("encoder-back", leftFront.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.



        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-end", leftFront.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}
