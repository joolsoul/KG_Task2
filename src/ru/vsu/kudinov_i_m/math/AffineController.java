package ru.vsu.kudinov_i_m.math;

import ru.vsu.kudinov_i_m.graphics.World;
import ru.vsu.kudinov_i_m.screen.ScreenConverter;
import ru.vsu.kudinov_i_m.screen.ScreenPoint;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class AffineController implements MouseListener, MouseMotionListener, MouseWheelListener {

    public interface RepaintRequiredListener {
        void shouldRepaint();
    }

    private RepaintRequiredListener listener = null;
    private AffineTransformation affineTransformation;
    private World world;
    private ScreenConverter screenConverter;

    public AffineController(AffineTransformation affineTransformation, ScreenConverter screenConverter, World world) {
        this.affineTransformation = affineTransformation;
        this.screenConverter= screenConverter;
        this.world = world;
    }

    ScreenPoint lastPoint = null;
    boolean leftFlag, rightFlag, middleFlag;
    @Override
    public void mouseClicked(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {
            affineTransformation.modifyRotation(MatrixFactories.rotation(Math.PI/10));
            onRepaint();
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            affineTransformation.modifyRotation(MatrixFactories.rotation(-Math.PI/10));
            onRepaint();
        }

        if(SwingUtilities.isMiddleMouseButton(e)) {
            if(e.isShiftDown()) {
                affineTransformation.modifyProjection(MatrixFactories.centralProjection(MatrixFactories.Axis.X));
            }
            else {
                affineTransformation.modifyProjection(MatrixFactories.centralProjection(MatrixFactories.Axis.Y));
            }

        }
        onRepaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = new ScreenPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPoint = new ScreenPoint(e.getX(), e.getY());

        if(e.isShiftDown()) {
            Vector2 p1 = screenConverter.screenToReal(currentPoint);
            Vector2 p2 = screenConverter.screenToReal(lastPoint);
            Vector2 delta = p2.minus(p1);

            screenConverter.moveCorner(delta);
            lastPoint = currentPoint;
        }
        else {

            if(isPointInsidePolygon(screenConverter.screenToReal(currentPoint), world.getModels().get(0).getPoints())) {
                Vector2 p1 = screenConverter.screenToReal(currentPoint);
                Vector2 p2 = screenConverter.screenToReal(lastPoint);
                Vector2 delta = new Vector2(0, 0);

                if(affineTransformation.isYProjection()) {
                    Vector2 p3 = new Vector2(p1.getRealX(), p2.getRealY());
                    Vector2 p4 = new Vector2(p2.getRealX(), p1.getRealY());
                    delta = p4.minus(p3);
                }
                if(affineTransformation.isXProjection()) {
                    Vector2 p3 = new Vector2(p2.getRealX(), p1.getRealY());
                    Vector2 p4 = new Vector2(p1.getRealX(), p2.getRealY());
                    delta = p4.minus(p3);
                }
                if(affineTransformation.isYProjection() && affineTransformation.isXProjection()) {
                    delta = p2.minus(p1);
                }
                if(!affineTransformation.isYProjection() && !affineTransformation.isXProjection()) {
                    delta = p1.minus(p2);
                }

                affineTransformation.modifyTranslation(MatrixFactories.translation(delta));
                lastPoint = currentPoint;
            }
        }
        onRepaint();
    }

    boolean isPointInsidePolygon(Vector2 point, List<Vector2> points){
        List<Vector2> newPoints = new LinkedList<>();
        for (Vector2 p : points) {
            newPoints.add(affineTransformation.affineTransform(p));
        }
        boolean result = false;
        int j = newPoints.size() - 1;
        for (int i = 0; i < newPoints.size(); i++) {
            if ((newPoints.get(i).getRealY() > point.getRealY()) != (newPoints.get(j).getRealY() > point.getRealY()) &&
                    (point.getRealX() < newPoints.get(i).getRealX() + (newPoints.get(j).getRealX() - newPoints.get(i).getRealX()) *
                            (point.getRealY() - newPoints.get(i).getRealY()) / (newPoints.get(j).getRealY() - newPoints.get(i).getRealY()))) {
                result = !result;
            }
            j = i;
        }
        return result;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private static final double SCALE_STEP = 0.05;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.isShiftDown())
        {
            int clicks = e.getWheelRotation();
            double coef = 1 + SCALE_STEP * (clicks < 0 ? -1 : 1);
            double overallScale = 1;
            for (int i = Math.abs(clicks); i > 0; i--) {
                overallScale *= coef;
            }
            screenConverter.changeScale(overallScale);
            onRepaint();
        }
        else {
            int delta = e.getWheelRotation();
            float factor = 1;
            float scale = delta < 0 ? 0.9f : 1.1f;
            int counter = delta < 0 ? -delta : delta;
            while (counter-- > 0)
                factor *= scale;
            affineTransformation.modifyScale(MatrixFactories.scale(factor));
            onRepaint();
        }

    }

    public void setListener(RepaintRequiredListener listener) {
        this.listener = listener;
    }

    protected void onRepaint() {
        if(listener != null) {
            listener.shouldRepaint();
        }
    }


}
