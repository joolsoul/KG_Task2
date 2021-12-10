package ru.vsu.kudinov_i_m;

import ru.vsu.kudinov_i_m.util.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements AffineController.RepaintRequiredListener {

    private ScreenConverter screenConverter = new ScreenConverter(4, 4, -2, 2, 1, 1);
    private AffineTransformation affineTransformation;
    private AffineController affineController;
    private Scene scene;

    private Line xAxis;
    private Line yAxis;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 18);

    public MainPanel() {
        scene = new Scene(Color.WHITE);
        affineTransformation = new AffineTransformation();
        affineController = new AffineController(affineTransformation, screenConverter);
        affineController.setListener(this);

        xAxis = new Line(new Vector2(-2, 0), new Vector2(2, 0));
        yAxis = new Line(new Vector2(0, -1), new Vector2(0, 1));

        this.addMouseListener(affineController);
        this.addMouseMotionListener(affineController);
        this.addMouseWheelListener(affineController);

        scene.getModels().add(new Rhombus(new Vector2(-0.7f, 0), new Vector2(0, 0.5f), new Vector2(0.7f, 0), new Vector2(0, -0.5f)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        screenConverter.setScreenWidth(getWidth());
        screenConverter.setScreenHeight(getHeight());

        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bufferedImage.createGraphics();

        IDrawer drawer = new SimpleDrawer(gr, screenConverter);

        scene.drawWorld(affineTransformation, drawer);
        DrawUtils.drawWithColor(gr, Color.BLUE, () -> {
            updateAxes();
            xAxis.draw(gr, screenConverter);
            yAxis.draw(gr, screenConverter);
            drawAxisScale(gr);
        });

        g.drawImage(bufferedImage, 0, 0, null);
        gr.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    private void updateAxes() {
        xAxis.setP1(new Vector2((float) (screenConverter.getRealWidth() + screenConverter.getAngularRealX()), 0));
        xAxis.setP2(new Vector2((float) screenConverter.getAngularRealX(), 0));

        yAxis.setP1(new Vector2((float) 0, (float) (screenConverter.getAngularRealY() - screenConverter.getRealHeight())));
        yAxis.setP2(new Vector2((float) 0, (float) screenConverter.getAngularRealY()));
    }

    private void drawAxisScale(Graphics2D graphics2D) {
        drawAxisScaleX(graphics2D);
        drawAxisScaleY(graphics2D);
    }

    private void drawAxisScaleX(Graphics2D graphics2D) {
        int leftRealBound = (int) screenConverter.getAngularRealX();
        int rightRealBound = (int) Math.ceil(screenConverter.getAngularRealX() + screenConverter.getRealWidth());

        double firstLinePointY;
        double secondLinePointY;

        double textXShift;
        double textYShift;

        if (screenConverter.getAngularRealY() > 0) {
            if (screenConverter.getAngularRealY() < screenConverter.getRealHeight()) {
                firstLinePointY = -0.05;
                secondLinePointY = 0.05;

            }
            else {
                firstLinePointY = screenConverter.getAngularRealY() - screenConverter.getRealHeight();
                secondLinePointY = screenConverter.getAngularRealY() - screenConverter.getRealHeight() + 0.2;

            }
            textXShift = 0.04;
            textYShift = 0.06;
        }
        else {
            firstLinePointY = screenConverter.getAngularRealY();
            secondLinePointY = screenConverter.getAngularRealY() - 0.2;

            textXShift = 0.04;
            textYShift = -0.1;
        }
        for (int i = leftRealBound; i <= rightRealBound; i++) {
            new Line(new Vector2((float) i, (float) firstLinePointY), new Vector2((float) i, (float) secondLinePointY)).draw(graphics2D, screenConverter);
            new Text(Integer.toString(i), this.defaultFont, new Vector2((float) (i + textXShift), (float) (firstLinePointY + textYShift))).draw(graphics2D, screenConverter);
        }
    }

    private void drawAxisScaleY(Graphics2D graphics2D) {
        int upRealBound = (int) Math.ceil(screenConverter.getAngularRealY());
        int downRealBound = (int) (screenConverter.getAngularRealY() - screenConverter.getRealHeight());

        double firstLinePointX;
        double secondLinePointX;

        double textXShift;
        double textYShift;

        if (screenConverter.getAngularRealX() < 0) {
            if (screenConverter.getAngularRealX() > -screenConverter.getRealWidth()) {
                firstLinePointX = -0.05;
                secondLinePointX = 0.05;
            }
            else {
                firstLinePointX = screenConverter.getAngularRealX() + screenConverter.getRealWidth();
                secondLinePointX = screenConverter.getAngularRealX() + screenConverter.getRealWidth() - 0.1;
            }
            textXShift = -0.06;
            textYShift = 0.01;
        }
        else {
            firstLinePointX = screenConverter.getAngularRealX();
            secondLinePointX = screenConverter.getAngularRealX() + 0.1;

            textXShift = 0.04;
            textYShift = 0.06;
        }

        for (int i = downRealBound; i <= upRealBound; i++) {
            new Line(new Vector2((float) firstLinePointX, i), new Vector2((float) secondLinePointX, i)).draw(graphics2D, screenConverter);
            new Text(Integer.toString(i), this.defaultFont, new Vector2((float) (firstLinePointX + textXShift), (float) (i + textYShift))).draw(graphics2D, screenConverter);
        }
    }
}
