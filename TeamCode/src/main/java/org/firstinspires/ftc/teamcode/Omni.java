package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "omni", group = "TeleOp")
//@Disabled
public class Omni extends OpMode {

    private Drive drive = null;



    @Override
    public void init() {
        org.firstinspires.ftc.teamcode.GAMEPAD GAMEPAD1 = new org.firstinspires.ftc.teamcode.GAMEPAD(this.gamepad1, this.telemetry);
        drive = new Drive(this.hardwareMap, GAMEPAD1, this.telemetry);

    }

    @Override
    public void loop() {
        drive.goTeleOp();


        telemetry.update();
    }

}