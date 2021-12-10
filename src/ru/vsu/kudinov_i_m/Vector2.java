package ru.vsu.kudinov_i_m;

public class Vector2 {

    private float[] values;
    private static final float EPSILON = 1e-10f;

    public Vector2(float x, float y)
    {
        this.values = new float[]{x, y};
    }

    public float getRealX()
    {
        return values[0];
    }

    public void setRealX(float realX)
    {
        this.values[0] = realX;
    }

    public float getRealY()
    {
        return values[1];
    }

    public void setRealY(float realY)
    {
        this.values[1] = realY;;
    }

    public Vector2 minus(Vector2 point)
    {
        return new Vector2(this.getRealX() - point.getRealX(), this.getRealY() - point.getRealY());
    }

    public float length() {
        float lenSqr = values[0] * values[0] + values[1] * values[1];
        if (lenSqr < EPSILON)
            return 0;
        return (float)Math.sqrt(lenSqr);
    }
}
