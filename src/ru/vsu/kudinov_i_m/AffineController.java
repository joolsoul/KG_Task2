package ru.vsu.kudinov_i_m;

import javax.swing.*;
import java.awt.event.*;

public class AffineController implements MouseListener, MouseMotionListener, MouseWheelListener {

    public interface RepaintRequiredListener {
        void shouldRepaint();
    }

    private RepaintRequiredListener listener = null;
    private AffineTransformation affineTransformation;
    private ScreenConverter screenConverter;

    public AffineController(AffineTransformation affineTransformation, ScreenConverter screenConverter) {
        this.affineTransformation = affineTransformation;
        this.screenConverter= screenConverter;
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
    }

    private ScreenPoint prevPoint = null;
    @Override
    public void mousePressed(MouseEvent e) {
        prevPoint = new ScreenPoint(e.getX(), e.getY());
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

        ScreenPoint currPoint = new ScreenPoint(e.getX(), e.getY());
        Vector2 p1 = screenConverter.screenToReal(currPoint);
        Vector2 p2 = screenConverter.screenToReal(prevPoint);
        Vector2 delta = p2.minus(p1);

        screenConverter.moveCorner(delta);
        prevPoint = currPoint;
        onRepaint();
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
