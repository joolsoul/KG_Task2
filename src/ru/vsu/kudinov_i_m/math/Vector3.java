package ru.vsu.kudinov_i_m.math;

public class Vector3 {

    private float[] values;
    private static final float EPSILON = 1e-10f;

    public Vector3(float x, float y)
    {
        this.values = new float[]{x, y, 1};
    }

    public Vector3(Vector2 point)
    {
        this.values = new float[]{point.getRealX(), point.getRealY(), 1};
    }

    private Vector3(float[] array) {
        this.values = array;
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

    public static Vector3 createZeroVector() {
        return new Vector3(new float[3]);
    }

    public Vector3 multiply(float number) {
        float[] array = new float[3];
        for (int i = 0; i < array.length; i++)
            array[i] = number * this.at(i);
        return new Vector3(array);
    }

    public Vector3 plus(Vector3 otherVector) {
        float[] result = new float[3];
        for (int i = 0; i < 3; i++) {
            result[i] = this.at(i) + otherVector.at(i);
        }
        return new Vector3(result);
    }

    public float at(int idx) {
        return values[idx];
    }

    public Vector2 asVector2() {
        return new Vector2(this.getRealX(), this.getRealY());
    }
}
