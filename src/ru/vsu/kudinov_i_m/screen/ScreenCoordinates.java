package ru.vsu.kudinov_i_m.screen;

import java.util.List;

public class ScreenCoordinates {

    private int[] xCoordinates, yCoordinates;

    public ScreenCoordinates(List<ScreenPoint> screenPoints) {
        this.xCoordinates = new int[screenPoints.size()];
        this.yCoordinates = new int[screenPoints.size()];
        int i = 0;
        for (ScreenPoint screenPoint : screenPoints) {
            xCoordinates[i] = screenPoint.getScreenX();
            yCoordinates[i] = screenPoint.getScreenY();
            i++;
        }
    }

    public int[] getXCoordinates() {
        return xCoordinates;
    }

    public int[] getYCoordinates() {
        return yCoordinates;
    }

    public int getLength() {
        return xCoordinates.length;
    }
}
