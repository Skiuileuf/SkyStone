package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Libs.GAMEPAD;


@TeleOp(name = "omni", group = "TeleOp")
//@Disabled
public class Omni extends OpMode {

    private Drive drive = null;



    @Override
    public void init() {
        GAMEPAD GAMEPAD1 = new GAMEPAD(this.gamepad1, this.telemetry);
        drive = new Drive(this.hardwareMap, GAMEPAD1, this.telemetry);

    }

    @Override
    public void loop() {
        drive.goTeleOp();


        telemetry.update();
    }

}