package org.firstinspires.ftc.teamcode.position;



public class Position {
    int leftFront;
    int leftRear;
    int rightFront;
    int rightRear;


    Action action;

    public Position (int lf, int lr, int rf, int rr, Action action) {
        this.leftFront = lf;
        this.leftRear = lr;
        this.rightFront = rf;
        this.rightRear = rr; //AICI ERA GRESEALA
        this.action = action;

    }

    public Action getAction() {
        return action;
    }

    public int getLeftFront() {
        return leftFront;
    }

    public void setLeftFront(int leftFront) {
        this.leftFront = leftFront;
    }

    public int getLeftRear() {
        return leftRear;
    }

    public void setLeftRear(int leftRear) {
        this.leftRear = leftRear;
    }

    public int getRightFront() {
        return rightFront;
    }

    public void setRightFront(int rightFront) {
        this.rightFront = rightFront;
    }

    public int getRightRear() {
        return rightRear;
    }

    public void setRightRear(int rightRear) {
        this.rightRear = rightRear;
    }
}
