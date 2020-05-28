package org.firstinspires.ftc.teamcode.position;

public class Gheara implements Action {


    public boolean isMotorState() {
        return motorState;
    }

    public void setMotorState(boolean motorState) {
        this.motorState = motorState;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public boolean motorState;
    public int runTime;


    Gheara(boolean state, int time){}

    @Override
    public void execute(boolean state, int time) {
        if(state){

            sleep(time);

        } else{

        }
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
