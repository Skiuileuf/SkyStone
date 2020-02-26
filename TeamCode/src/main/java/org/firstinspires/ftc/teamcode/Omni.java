package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Libs.GAMEPAD;
import org.firstinspires.ftc.teamcode.Libs.Utils;


@TeleOp(name = "omni", group = "TeleOp")
//@Disabled
public class Omni extends OpMode {

    private Drive drive = null;

    GAMEPAD GAMEPAD1 = null;
    GAMEPAD GAMEPAD2 = null;

    DcMotor glisiera = null;

    Servo ghearaUnu = null;
    Servo ghearaDoi = null;

    Servo bratUnu = null;
    Servo bratDoi = null;

    int minimGlisiera = 0;
    int maximGlisiera = 2500;
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

        valoareGlisiera = glisiera.getCurrentPosition();


        //ACEST COD NECESITA UN ENCODER
        //glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

/*
        glisiera.setTargetPosition(500);
        glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/
    }

    @Override
    public void loop() {
        drive.goTeleOp();




        if(GAMEPAD2.right_stick_y > 0.3 || GAMEPAD2.right_stick_y < -0.3)
        {
            valoareGlisiera -= Math.floor(Utils.cut(GAMEPAD2.right_stick_y, (double)minimGlisiera, (double)maximGlisiera)); //axa y merge cu minus in sus
        }

        telemetry.addData("Pozitie Glisiera", glisiera.getCurrentPosition());
        telemetry.addData("Valoare Glisiera", valoareGlisiera);

        glisiera.setTargetPosition(valoareGlisiera);


        if(GAMEPAD1.right_bumper.toggle) {
            if (GAMEPAD1.left_bumper.toggle) {
                glisiera.setDirection(DcMotorSimple.Direction.REVERSE);
                glisiera.setPower(0.7);
                telemetry.addData("Directie Glisiera", "REVERSE");

            } else {
                glisiera.setDirection(DcMotorSimple.Direction.FORWARD);
                glisiera.setPower(0.7);
                telemetry.addData("Directie Glisiera", "FORWARD");
            }
            telemetry.addData("Glisiera","activata, apasa right bumper pt dezactivare");
        }
        else {
                glisiera.setPower(0);
                telemetry.addData("Glisiera", "dezactivata");
        }

        if(GAMEPAD1.x.toggle)
        {
            ghearaUnu.setPosition(0.7); //stanga
            ghearaDoi.setPosition(0.6); // dreapta
            telemetry.addData("Gheare", "1");
        }
        else {
                ghearaUnu.setPosition(0);
                ghearaDoi.setPosition(0);
                telemetry.addData("Gheare", "0");
        }


        telemetry.update();
    }

}