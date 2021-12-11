package ru.vsu.kudinov_i_m.math;

public class Matrix3 {

    private float[] matrix;


    public Matrix3(float[][] m) {
        matrix = new float[9];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matrix[i * 3 + j] = m[i][j];
    }


    private Matrix3(float[] arr) {
        matrix = arr;
    }

    public float getAt(int row, int col) {
        return matrix[row * 3 + col];
    }

    public void setAt(int row, int col, float value) {
        matrix[row * 3 + col] = value;
    }

    public static Matrix3 createZeroMatrix() {
        return new Matrix3(new float[9]);
    }

    public static Matrix3 createOneMatrix() {
        Matrix3 matrix = createZeroMatrix();
        for (int i = 0; i < 3; i++)
            matrix.setAt(i, i, 1);
        return matrix;
    }

    public Vector3 multiply(Vector3 vector) {
        float[] result = new float[3];
        for (int i = 0; i < 3; i++) {
            float sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += vector.at(j) * this.getAt(j, i);
            }
            result[i] = sum;
        }
        return new Vector3(result[0], result[1]);
    }

    public Matrix3 multiply(float number) {
        float[] arr = new float[9];
        for (int i = 0; i < arr.length; i++)
            arr[i] = this.matrix[i] * number;
        return new Matrix3(arr);
    }

    /**
     * Умножает текущую матрицу(T) на другую матрицу(M) справа T x M.
     * @param matrix другая матрица
     * @return новая матрица, являющаяся результатом умножения текущей матрицы на другую.
     */
    public Matrix3 multiply(Matrix3 matrix) {
        Matrix3 result = createZeroMatrix();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    result.setAt(i, j, result.getAt(i, j) +
                            this.getAt(i, k) * matrix.getAt(k, j));
        return result;
    }
}