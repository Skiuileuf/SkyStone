package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(group = "Autonom",name="Autojava")
@Disabled
public class Auto extends LinearOpMode {


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



        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        // reset encoder count kept by left motor.
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // set left motor to run to target encoder position and stop with brakes on.
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        // set right motor to run without regard to an encoder.
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set left motor to run to 5000 encoder counts.

        leftFront.setTargetPosition(5000);
        rightFront.setTargetPosition(5000);

        // set both motors to 25% power. Movement will start.

        leftFront.setPower(0.25);
        rightFront.setPower(0.25);

        leftRear.setPower(0.25);
        rightRear.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && leftFront.isBusy() || rightFront.isBusy())
        {
            telemetry.addData("encoder-fwd", leftFront.getCurrentPosition() + "  busy=" + leftFront.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        leftFront.setPower(0.0);
        rightFront.setPower(0.0);

        leftRear.setPower(0.0);
        rightRear.setPower(0.0);

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

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setPower(-0.25);
        rightFront.setPower(-0.25);

        leftRear.setPower(-0.25);
        rightRear.setPower(-0.25);

        while (opModeIsActive() && leftFront.getCurrentPosition() > 0 || rightFront.getCurrentPosition() > 0)
        {
            telemetry.addData("encoder-back", leftFront.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        leftFront.setPower(0.0);
        rightFront.setPower(0.0);

        leftRear.setPower(0.0);
        rightFront.setPower(0.0);

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
