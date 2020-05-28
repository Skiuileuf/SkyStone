package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.position.ListOfPositions;
import org.firstinspires.ftc.teamcode.position.Position;

import java.util.List;


@Autonomous(name = "AutonomLinear", group = "Autonomous")
public class AutonomLinear extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    List<Position> positions = null;

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor rightRear = null;
    DcMotor leftRear = null;

    private int currentStep = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //INIT

        telemetry.addData("Status", "Init");
        gettingMotorsReference();
        settingMotorsRunningMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // TODO: De testat daca merge fara
        settingMotorsDirections();
        settingMotorsZeroPowerBehavior();

        sleep(100);

        positions = ListOfPositions.positions();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //START
        settingMotorsPower(0.2);
        telemetry.addData("Status", "Start");
        settingMotorsRunningMode(DcMotor.RunMode.RUN_TO_POSITION);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Loop");
            checkPosition();
            //LOOP
        }

        if (!leftFront.isBusy() && !rightFront.isBusy() && !leftRear.isBusy() && !rightRear.isBusy()) {
            stopAndResetEncoders();
        }
        telemetry.addData("Status", "Stoped");
        //STOP
    }

    private void checkPosition() {
//        telemetry.addData("Info", "left front busy : " + leftFront.isBusy());
//        telemetry.addData("Info2", "right front busy : " + rightFront.isBusy());
//        telemetry.addData("Info3", "left rear busy : " + leftRear.isBusy());
//        telemetry.addData("Info4", "right rear busy : " + rightRear.isBusy());

        if (leftFront.isBusy() || rightFront.isBusy() || leftRear.isBusy() || rightRear.isBusy()) {
            telemetry.addData("Motor Status", "BUSY");
        } else {
            telemetry.addData("Motor Status", "READY");
            if (!goToNextStep()) {
                stop();
            }
        }

        telemetry.addData("INFO", "Left Front Pos: " + leftFront.getCurrentPosition());
        telemetry.addData("INFO", "Right Front Pos: " + rightFront.getCurrentPosition());
        telemetry.addData("INFO", "Left Rear Pos: " + leftRear.getCurrentPosition());
        telemetry.addData("INFO", "Right Rear Pos: " + rightRear.getCurrentPosition());

    }


    private boolean goToNextStep() {
        telemetry.addData("Current step ", currentStep);
        if (currentStep < positions.size()) {
            Position position = positions.get(currentStep);
            if(position.getAction() != null) {
                position.getAction().execute(position.getAction().motorState, position.getAction().runTime);
                telemetry.addData("SLEEP", position.getAction().runTime);
                sleep(position.getAction().runTime);
            } else {
                leftFront.setTargetPosition(position.getLeftFront());
                rightFront.setTargetPosition(position.getRightFront());
                leftRear.setTargetPosition(position.getLeftRear());
                rightRear.setTargetPosition(position.getRightRear());
            }
            currentStep++;
        } else {
            telemetry.addData("INFO", "Out of steps");
            return false;
        }
        return true;
    }


    public void settingMotorsPower(double pow) {
        leftFront.setPower(pow);
        rightFront.setPower(pow);
        leftRear.setPower(pow);
        rightRear.setPower(pow);
    }

    private void settingMotorsZeroPowerBehavior() {
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void settingMotorsDirections() {
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void gettingMotorsReference() {
        this.leftFront = hardwareMap.dcMotor.get("leftFront");
        this.rightFront = hardwareMap.dcMotor.get("rightFront");
        this.rightRear = hardwareMap.dcMotor.get("rightRear");
        this.leftRear = hardwareMap.dcMotor.get("leftRear");
    }

    private void settingMotorsRunningMode(DcMotor.RunMode runMode) {
        leftFront.setMode(runMode);
        rightFront.setMode(runMode);
        rightRear.setMode(runMode);
        leftRear.setMode(runMode);
    }

    public void stopAndResetEncoders() {
        settingMotorsRunningMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

}
