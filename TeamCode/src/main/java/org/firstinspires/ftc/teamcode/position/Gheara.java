package org.firstinspires.ftc.teamcode.position;

import org.firstinspires.ftc.teamcode.Autonom;

public class Gheara implements Action {
    @Override
    public void execute(boolean state, int time) {
        if(state){

            Autonom.sleep(time);

        } else{

        }
    }
}
