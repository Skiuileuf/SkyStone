package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Libs.GAMEPAD;


@TeleOp(name = "omni", group = "TeleOp")
//@Disabled
public class Omni extends OpMode {

    private Drive drive = null;

    GAMEPAD GAMEPAD1 = null;
    GAMEPAD GAMEPAD2 = null;

    DcMotor glisiera = null;

    Servo ghearaUnu = null;
    Servo ghearaDoi = null;

    Servo bratUnu = null; //dreapta
    Servo bratDoi = null; //stanga

    int minimGlisiera = 0;
    int maximGlisiera = 2830;
    int valoareGlisiera = 0;



    @Override
    public void init() {
        GAMEPAD1 = new GAMEPAD(this.gamepad1, this.telemetry);
        GAMEPAD2 = new GAMEPAD(this.gamepad2, this.telemetry);
        drive = new Drive(this.hardwareMap, GAMEPAD1, this.telemetry);

        ghearaUnu = hardwareMap.servo.get("agataStanga");
        ghearaDoi = hardwareMap.servo.get("agataDreapta");

        ghearaUnu.setDirection(Servo.Direction.REVERSE);

        bratUnu = hardwareMap.servo.get("brat1");
        bratDoi = hardwareMap.servo.get("brat2");

        bratUnu.setDirection(Servo.Direction.REVERSE);





        //Motor NeveRest 60:1
        //1680 tickuri/rotatie
        glisiera = hardwareMap.dcMotor.get("glisiera");


        //glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //glisiera.setDirection(DcMotorSimple.Direction.REVERSE);
/*
        glisiera.setTargetPosition(500);
        glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/
    }

    @Override
    public void loop() {
        drive.goTeleOp();

        if(GAMEPAD2.right_stick_y > 0.5)
        {
            glisiera.setPower(1);
        }
        else if(GAMEPAD2.right_stick_y < -0.5)
        {
            glisiera.setPower(-0.2);
        }
        else
            {
                glisiera.setPower(0.1);
            }

        if(GAMEPAD1.x.toggle)
        {
            ghearaUnu.setPosition(0.75); //stanga
            ghearaDoi.setPosition(0.6); // dreapta
            telemetry.addData("Gheare", "1");
        }
        else {
                ghearaUnu.setPosition(0);
                ghearaDoi.setPosition(0);
                telemetry.addData("Gheare", "0");
        }

        if(GAMEPAD2.x.toggle)
        {
            bratUnu.setPosition(0.94); //dreapta
            bratDoi.setPosition(1); //stanga
            telemetry.addData("Brat", 1);
        }
        else {
            bratUnu.setPosition(0); //dreapta
            bratDoi.setPosition(0.0632); //stanga
            telemetry.addData("Brat", 0);
        }



        telemetry.update();
    }

}