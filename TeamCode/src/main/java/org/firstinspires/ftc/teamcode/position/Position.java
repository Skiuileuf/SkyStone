package org.firstinspires.ftc.teamcode.position;



public class Position {
    int leftFront;
    int leftRear;
    int rightFront;
    int rightRear;

    Action action;

    public Position (int a, int b, int c, int d, Action action) {
        this.leftFront = a;
        this.leftRear = b;
        this.rightFront = c;
        this.leftRear = d;
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
