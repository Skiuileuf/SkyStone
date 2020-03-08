
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.position.ListOfPositions;
import org.firstinspires.ftc.teamcode.position.Position;

import java.util.List;

@Autonomous(name = "Autonom", group = "Pushbot")

public class Autonom extends OpMode {

    List<Position> positions = null;

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor rightRear = null;
    DcMotor leftRear = null;

    private int currentStep = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Init");
        gettingMotorsReference();
        settingMotorsRunningMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // TODO: De testat daca merge fara
        settingMotorsDirections();
        settingMotorsZeroPowerBehavior();
        settingMotorsPower(0.2);
        positions = ListOfPositions.positions();
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

    @Override
    public void start() {
        telemetry.addData("Status", "Start");
        settingMotorsRunningMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    @Override
    public void loop() {
        telemetry.addData("Status", "Loop");
        checkPosition();
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
            leftFront.setTargetPosition(position.getLeftFront());
            rightFront.setTargetPosition(position.getRightFront());
            leftRear.setTargetPosition(position.getLeftRear());
            rightRear.setTargetPosition(position.getRightRear());
            if(position.getAction() != null) {
                position.getAction().execute(true, 1000);
            }
            currentStep++;
        } else {
            telemetry.addData("INFO", "Out of steps");
            return false;
        }
        return true;
    }


    @Override
    public void stop() {
        if (!leftFront.isBusy() && !rightFront.isBusy() && !leftRear.isBusy() && !rightRear.isBusy()) {
            stopAndResetEncoders();
        }
        telemetry.addData("WARN", "Stoped");
    }

    public void settingMotorsPower(double pow) {
        leftFront.setPower(pow);
        rightFront.setPower(pow);
        leftRear.setPower(pow);
        rightRear.setPower(pow);
    }

    public void stopAndResetEncoders() {
        settingMotorsRunningMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * This method puts the current thread to sleep for the given time in msec.
     * It handles InterruptException where it recalculates the remaining time
     * and calls sleep again repeatedly until the specified sleep time has past.
     *
     * @param sleepTime specifies sleep time in msec.
     */
    public static void sleep(long sleepTime)
    {
        long wakeupTime = System.currentTimeMillis() + sleepTime;

        while (sleepTime > 0)
        {
            try
            {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
            }
            sleepTime = wakeupTime - System.currentTimeMillis();
        }
    }   //sleep
}
