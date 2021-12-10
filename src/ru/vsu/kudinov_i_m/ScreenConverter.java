package ru.vsu.kudinov_i_m;

public class ScreenConverter
{
    private double realWidth, realHeight;
    private int screenWidth, screenHeight;
    private double angularRealX, angularRealY;

    public ScreenConverter(double realWidth, double realHeight, double angularRealX, double angularRealY, int screenWidth, int screenHeight)
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
        double screenX = (realPoint.getRealX() - angularRealX) / realWidth * screenWidth;
        double screenY = (angularRealY - realPoint.getRealY()) / realHeight * screenHeight;

        return new ScreenPoint((int) screenX, (int) screenY);
    }

    public Vector2 screenToReal(ScreenPoint screenPoint) {
        float x = (float) (angularRealX + screenPoint.getScreenX() * realWidth / screenWidth);
        float y = (float) (angularRealY - screenPoint.getScreenY() * realHeight / screenHeight);
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


    public double getRealWidth()
    {
        return realWidth;
    }

    public void setRealWidth(double realWidth)
    {
        this.realWidth = realWidth;
    }

    public double getRealHeight()
    {
        return realHeight;
    }

    public void setRealHeight(double realHeight)
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

    public double getAngularRealX()
    {
        return angularRealX;
    }

    public void setAngularRealX(double angularRealX)
    {
        this.angularRealX = angularRealX;
    }

    public double getAngularRealY()
    {
        return angularRealY;
    }

    public void setAngularRealY(double angularRealY)
    {
        this.angularRealY = angularRealY;
    }
}
