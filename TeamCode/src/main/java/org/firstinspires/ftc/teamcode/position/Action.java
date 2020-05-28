package org.firstinspires.ftc.teamcode.position;

public interface Action {
    public boolean motorState = false;
    public int runTime = 0;
    void execute(boolean state, int time);

}


