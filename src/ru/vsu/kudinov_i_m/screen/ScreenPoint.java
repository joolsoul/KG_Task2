package ru.vsu.kudinov_i_m.screen;

public class ScreenPoint {

    private final int screenX;
    private final int screenY;

    public ScreenPoint(int screenX, int screenY)
    {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getScreenX()
    {
        return screenX;
    }

    public int getScreenY()
    {
        return screenY;
    }
}
