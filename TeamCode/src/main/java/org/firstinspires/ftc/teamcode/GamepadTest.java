package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Gamepad Test", group = "TeleOp")
public class GamepadTest extends OpMode
{
    @Override
    public void init() {
        telemetry.addData("Ajutor","Acest mod de operare arata toate datele primite de la controllere.");
    }

    @Override
    public void loop() {
        //GAMEPAD 1

        telemetry.addData("Gamepad 1 A", gamepad1.a);
        telemetry.addData("Gamepad 1 atRest()", gamepad1.atRest());
        telemetry.addData("Gamepad 1 B", gamepad1.b);
        telemetry.addData("Gamepad 1 Back", gamepad1.back);
        telemetry.addData("Gamepad 1 DPAD Down", gamepad1.dpad_down);
        telemetry.addData("Gamepad 1 DPAD Left", gamepad1.dpad_left);
        telemetry.addData("Gamepad 1 DPAD Right", gamepad1.dpad_right);
        telemetry.addData("Gamepad 1 DPAD Up", gamepad1.dpad_up);
        telemetry.addData("Gamepad 1 getGamepadId()", gamepad1.getGamepadId());
        telemetry.addData("Gamepad 1 getRobocolMsgType()", gamepad1.getRobocolMsgType());
        telemetry.addData("Gamepad 1 getSequenceNumber()", gamepad1.getSequenceNumber());
        telemetry.addData("Gamepad 1 getUser()", gamepad1.getUser());
        telemetry.addData("Gamepad 1 Guide", gamepad1.guide);
        telemetry.addData("Gamepad 1 hashCode()", gamepad1.hashCode());
        telemetry.addData("Gamepad 1 Id", gamepad1.id);
        //LEFT
        telemetry.addData("Gamepad 1 Left Bumper", gamepad1.left_bumper);
        telemetry.addData("Gamepad 1 Left Stick Button", gamepad1.left_stick_button);
        telemetry.addData("Gamepad 1 Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Left Trigger", gamepad1.left_trigger);
        //RIGHT
        telemetry.addData("Gamepad 1 Right Bumper", gamepad1.right_bumper);
        telemetry.addData("Gamepad 1 Right Stick Button", gamepad1.right_stick_button);
        telemetry.addData("Gamepad 1 Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Gamepad 1 Right Stick Y", gamepad1.right_stick_y);
        telemetry.addData("Gamepad 1 Right Trigger", gamepad1.right_trigger);

        telemetry.addData("Gamepad 1 toString()", gamepad1.toString());
        telemetry.addData("Gamepad 1 type()", gamepad1.type());
        telemetry.addData("Gamepad 1 X", gamepad1.x);
        telemetry.addData("Gamepad 1 Y", gamepad1.y);



        //GAMEPAD 2
        telemetry.addData("Gamepad 2 A", gamepad2.a);
        telemetry.addData("Gamepad 2 atRest()", gamepad2.atRest());
        telemetry.addData("Gamepad 2 B", gamepad2.b);
        telemetry.addData("Gamepad 2 Back", gamepad2.back);
        telemetry.addData("Gamepad 2 DPAD Down", gamepad2.dpad_down);
        telemetry.addData("Gamepad 2 DPAD Left", gamepad2.dpad_left);
        telemetry.addData("Gamepad 2 DPAD Right", gamepad2.dpad_right);
        telemetry.addData("Gamepad 2 DPAD Up", gamepad2.dpad_up);
        telemetry.addData("Gamepad 2 getGamepadId()", gamepad2.getGamepadId());
        telemetry.addData("Gamepad 2 getRobocolMsgType()", gamepad2.getRobocolMsgType());
        telemetry.addData("Gamepad 2 getSequenceNumber()", gamepad2.getSequenceNumber());
        telemetry.addData("Gamepad 2 getUser()", gamepad2.getUser());
        telemetry.addData("Gamepad 2 Guide", gamepad2.guide);
        telemetry.addData("Gamepad 2 hashCode()", gamepad2.hashCode());
        telemetry.addData("Gamepad 2 Id", gamepad2.id);
        //LEFT
        telemetry.addData("Gamepad 2 Left Bumper", gamepad2.left_bumper);
        telemetry.addData("Gamepad 2 Left Stick Button", gamepad2.left_stick_button);
        telemetry.addData("Gamepad 2 Left Stick X", gamepad2.left_stick_x);
        telemetry.addData("Gamepad 2 Left Stick Y", gamepad2.left_stick_y);
        telemetry.addData("Gamepad 2 Left Trigger", gamepad2.left_trigger);
        //RIGHT
        telemetry.addData("Gamepad 2 Right Bumper", gamepad2.right_bumper);
        telemetry.addData("Gamepad 2 Right Stick Button", gamepad2.right_stick_button);
        telemetry.addData("Gamepad 2 Right Stick X", gamepad2.right_stick_x);
        telemetry.addData("Gamepad 2 Right Stick Y", gamepad2.right_stick_y);
        telemetry.addData("Gamepad 2 Right Trigger", gamepad2.right_trigger);

        telemetry.addData("Gamepad 2 toString()", gamepad2.toString());
        telemetry.addData("Gamepad 2 type()", gamepad2.type());
        telemetry.addData("Gamepad 2 X", gamepad2.x);
        telemetry.addData("Gamepad 2 Y", gamepad2.y);


        telemetry.update();

    }
}



























