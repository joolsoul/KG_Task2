package ru.vsu.kudinov_i_m.screen;

import ru.vsu.kudinov_i_m.math.Vector2;

public class ScreenConverter
{
    private float realWidth, realHeight;
    private int screenWidth, screenHeight;
    private float angularRealX, angularRealY;

    public ScreenConverter(float realWidth, float realHeight, float angularRealX, float angularRealY, int screenWidth, int screenHeight)
    {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.angularRealX = angularRealX;
        this.angularRealY = angularRealY;
    }

    public ScreenPoint realToScreen(Vector2 realPoint)
    {
        float screenX = (realPoint.getRealX() - angularRealX) / realWidth * screenWidth;
        float screenY = (angularRealY - realPoint.getRealY()) / realHeight * screenHeight;

        return new ScreenPoint((int) screenX, (int) screenY);
    }

    public Vector2 screenToReal(ScreenPoint screenPoint) {
        float x = (angularRealX + screenPoint.getScreenX() * realWidth / screenWidth);
        float y = (angularRealY - screenPoint.getScreenY() * realHeight / screenHeight);
        return new Vector2(x, y);
    }

    public void moveCorner(Vector2 delta)
    {
        angularRealX += delta.getRealX();
        angularRealY += delta.getRealY();
    }

    public void changeScale(double scale)
    {
        realHeight *= scale;
        realWidth *= scale;
        angularRealX *= scale;
        angularRealY *= scale;
    }


    public float getRealWidth()
    {
        return realWidth;
    }

    public void setRealWidth(float realWidth)
    {
        this.realWidth = realWidth;
    }

    public float getRealHeight()
    {
        return realHeight;
    }

    public void setRealHeight(float realHeight)
    {
        this.realHeight = realHeight;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight)
    {
        this.screenHeight = screenHeight;
    }

    public float getAngularRealX()
    {
        return angularRealX;
    }

    public void setAngularRealX(float angularRealX)
    {
        this.angularRealX = angularRealX;
    }

    public float getAngularRealY()
    {
        return angularRealY;
    }

    public void setAngularRealY(float angularRealY)
    {
        this.angularRealY = angularRealY;
    }
}
