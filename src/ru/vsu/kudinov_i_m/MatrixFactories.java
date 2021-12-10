package ru.vsu.kudinov_i_m;

public class MatrixFactories {

    public enum Axis {X, Y};

    public static Matrix3 createZeroMatrix() {
        return Matrix3.createZeroMatrix();
    }

    public static Matrix3 createOneMatrix() {
        return Matrix3.createOneMatrix();
    }

    public static Matrix3 rotation(double alpha) {
        Matrix3 matrix = createOneMatrix();

        matrix.setAt(0, 0, (float)Math.cos(alpha));
        matrix.setAt(0, 1, (float)Math.sin(alpha));
        matrix.setAt(1, 0, -(float)Math.sin(alpha));
        matrix.setAt(1, 1, (float)Math.cos(alpha));
        return matrix;
    }

    public static Matrix3 translation(float deltaX, float deltaY) {
        Matrix3 matrix = createOneMatrix();
        matrix.setAt(0, 2, deltaX);
        matrix.setAt(1, 2, deltaY);
        return matrix;
    }

    public static Matrix3 translation(Vector3 v) {
        return translation(v.getRealX(), v.getRealY());
    }

    public static Matrix3 scale(float factorX, float factorY) {
        Matrix3 matrix = createOneMatrix();
        matrix.setAt(0, 0, factorX);
        matrix.setAt(1, 1, factorY);
        return matrix;
    }

    public static Matrix3 scale(float factor) {
        return scale(factor, factor);
    }

    public static Matrix3 centralProjection(int axisIndex) {
        Matrix3 matrix = createOneMatrix();
        if (axisIndex < 0 || axisIndex > 2)
            return matrix;
        if(axisIndex == 0) {
            matrix.setAt(0, 0, -1);
        } else {
            matrix.setAt(1, 1, -1);
        }
        return matrix;
    }


    public static Matrix3 centralProjection(Axis axis) {
        return centralProjection(axis == Axis.X ? 0 : 1);
    }


}
