package org.firstinspires.ftc.teamcode.position;

import java.util.ArrayList;
import java.util.List;

public class ListOfPositions {

    public static List<Position> positions (){
        List<Position> positions = new ArrayList<>();

        Action g = new Gheara();
        positions.add(new Position(0,0,0,0,g));

        positions.add(new Position(1000, 1000, 1000, 1000, g));
        positions.add(new Position(0, 0, 0, 0, g));
        positions.add(new Position(-1000, -1000, -1000, -1000, g));
        positions.add(new Position(1000, 1000, 1000, 1000, g));
        positions.add(new Position(0, 0, 0, 0, g));


        return positions;
    }
}
