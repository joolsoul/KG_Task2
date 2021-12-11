package ru.vsu.kudinov_i_m.math;

public class AffineTransformation implements IAffine{

    private Matrix3 translation, scale, rotation, projection;

    public AffineTransformation() {
        translation = Matrix3.createOneMatrix();
        scale = Matrix3.createOneMatrix();
        rotation = Matrix3.createOneMatrix();
        projection = Matrix3.createOneMatrix();
    }

    @Override
    public Vector2 affineTransform(Vector2 point) {
        return projection.multiply(
                translation.multiply(
                        rotation.multiply(
                                scale.multiply(new Vector3(point)
                                )
                        )
                )
        ).asVector2();
    }

    public boolean isXProjection() {
        return projection.getAt(1, 1) == -1;
    }

    public boolean isYProjection() {
        return projection.getAt(0, 0) == -1;
    }

    public Matrix3 getTranslation() {
        return translation;
    }

    public void setTranslation(Matrix3 translation) {
        this.translation = translation;
    }

    public void modifyTranslation(Matrix3 delta) {
        this.translation = delta.multiply(this.translation);
    }

    public Matrix3 getScale() {
        return scale;
    }

    public void setScale(Matrix3 scale) {
        this.scale = scale;
    }

    public void modifyScale(Matrix3 delta) {
        this.scale = delta.multiply(this.scale);
    }

    public Matrix3 getRotation() {
        return rotation;
    }

    public void setRotation(Matrix3 rotation) {
        this.rotation = rotation;
    }

    public void modifyRotation(Matrix3 delta) {
        this.rotation = delta.multiply(this.rotation);
    }

    public Matrix3 getProjection() {
        return projection;
    }

    public void setProjection(Matrix3 projection) {
        this.projection = projection;
    }

    public void modifyProjection(Matrix3 delta) {
        this.projection = delta.multiply(this.projection);
    }
}
