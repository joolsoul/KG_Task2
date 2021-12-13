package ru.vsu.kudinov_i_m.view;

import ru.vsu.kudinov_i_m.drawingObject.Line;
import ru.vsu.kudinov_i_m.drawingObject.Text;
import ru.vsu.kudinov_i_m.figure.Rhombus;
import ru.vsu.kudinov_i_m.graphics.IDrawer;
import ru.vsu.kudinov_i_m.graphics.SimpleDrawer;
import ru.vsu.kudinov_i_m.graphics.World;
import ru.vsu.kudinov_i_m.math.AffineController;
import ru.vsu.kudinov_i_m.math.AffineTransformation;
import ru.vsu.kudinov_i_m.math.Vector2;
import ru.vsu.kudinov_i_m.screen.ScreenConverter;
import ru.vsu.kudinov_i_m.util.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements AffineController.RepaintRequiredListener {

    private ScreenConverter screenConverter = new ScreenConverter(4, 4, -2, 2, 1, 1);
    private AffineTransformation affineTransformation;
    private AffineController affineController;
    private World world;
    private MotionsPanel motionsPanel;

    private Line xAxis;
    private Line yAxis;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 18);
    private final Color background = new Color(0xD4F6FF);

    public MainPanel() {
        world = new World(background);
        affineTransformation = new AffineTransformation();
        affineController = new AffineController(affineTransformation, screenConverter, world);
        affineController.setListener(this);

        xAxis = new Line(new Vector2(-2, 0), new Vector2(2, 0));
        yAxis = new Line(new Vector2(0, -1), new Vector2(0, 1));

        this.addMouseListener(affineController);
        this.addMouseMotionListener(affineController);
        this.addMouseWheelListener(affineController);

        world.getModels().add(new Rhombus(new Vector2(-0.7f, 0), new Vector2(0, -0.5f), new Color(0x030326), new BasicStroke(3)));
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        screenConverter.setScreenWidth(getWidth());
        screenConverter.setScreenHeight(getHeight());

        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bufferedImage.createGraphics();

        IDrawer drawer = new SimpleDrawer(gr, screenConverter);

        world.draw(affineTransformation, drawer);
        DrawUtils.drawWithColor(gr, Color.BLUE, () -> {
            resizeAxes();
            xAxis.draw(gr, screenConverter);
            yAxis.draw(gr, screenConverter);
            drawNumbers(gr);
        });

        g.drawImage(bufferedImage, 0, 0, null);
        gr.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    @Override
    public void addPanel(int x, int y) {
        motionsPanel = new MotionsPanel();
        motionsPanel.setSize(this.getWidth() / 8, this.getHeight() / 8);
        motionsPanel.setLocation(x, y);
        add(motionsPanel);
        removeMouseListener(affineController);
        removeMouseMotionListener(affineController);
        removeMouseWheelListener(affineController);
    }

    private static class MotionsPanel extends JPanel {

        private final JLabel rotationLabel = new JLabel("Rotation on:");
        private final JTextField alphaField = new JFormattedTextField();
        private final JButton projectionYButton = new JButton("Projection by Y Axis");
        private final JButton projectionXButton = new JButton("Projection by X Axis");
        private final JButton rotationButton = new JButton("Rotation");

        public MotionsPanel() {
            setBackground(new Color(0xA1FABE));
            setLayout(null);
            addButtons();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = (this.getHeight() / 4) - 5;
            int width = this.getWidth() / 5;
            rotationLabel.setBounds(width, 0, this.getWidth(), height);
            alphaField.setBounds(this.getWidth() - width * 2, 0, width, height);
            rotationButton.setBounds(0, this.getHeight() - height * 3 - 10, this.getWidth(), height);
            projectionXButton.setBounds(0, this.getHeight() - height * 2 - 5, this.getWidth(), height);
            projectionYButton.setBounds(0, this.getHeight() - height, this.getWidth(), height);
        }

        private void addListeners() {
            projectionXButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
        private void addButtons() {
            add(rotationLabel);
            add(alphaField);
            add(projectionXButton);
            add(projectionYButton);
            add(rotationButton);
        }
    }

    @Override
    public void clearPanel() {
        motionsPanel.setVisible(false);
    }

    private void resizeAxes() {
        xAxis.setPoint1(new Vector2(screenConverter.getAngularRealX(), 0));
        xAxis.setPoint2(new Vector2(screenConverter.getAngularRealX() + screenConverter.getRealWidth(), 0));

        yAxis.setPoint1(new Vector2(0, screenConverter.getAngularRealY()));
        yAxis.setPoint2(new Vector2(0, screenConverter.getAngularRealY() - screenConverter.getRealHeight()));
    }

    private void drawNumbers(Graphics2D graphics2D) {
        drawXNumbers(graphics2D);
        drawYNumbers(graphics2D);
    }

    private void drawXNumbers(Graphics2D graphics2D) {
        int leftRealBound = (int) screenConverter.getAngularRealX();
        int rightRealBound = (int) Math.ceil(screenConverter.getAngularRealX() + screenConverter.getRealWidth());

        float firstLinePointY;
        float secondLinePointY;

        float textXShift;
        float textYShift;

        if (screenConverter.getAngularRealY() > 0) { // если мы просматриваем выше Ох
            if (screenConverter.getAngularRealY() < screenConverter.getRealHeight()) { // если ось в поле зрения
                firstLinePointY = -0.05f;
                secondLinePointY = 0.05f;

            } else {
                firstLinePointY = screenConverter.getAngularRealY() - screenConverter.getRealHeight();
                secondLinePointY = firstLinePointY + 0.2f;

            }
            textXShift = 0.04f;
            textYShift = 0.06f;
        } else { // если мы просматриваем ниже Ох
            firstLinePointY = screenConverter.getAngularRealY();
            secondLinePointY = firstLinePointY - 0.2f;

            textXShift = 0.04f;
            textYShift = -0.1f;
        }
        for (int i = leftRealBound; i <= rightRealBound; i++) {
            new Line(new Vector2(i, firstLinePointY), new Vector2(i, secondLinePointY))
                    .draw(graphics2D, screenConverter);
            new Text(Integer.toString(i), this.defaultFont, new Vector2(i + textXShift, firstLinePointY + textYShift))
                    .draw(graphics2D, screenConverter);
        }
    }

    private void drawYNumbers(Graphics2D graphics2D) {
        int upRealBound = (int) Math.ceil(screenConverter.getAngularRealY());
        int downRealBound = (int) (screenConverter.getAngularRealY() - screenConverter.getRealHeight());

        float firstLinePointX;
        float secondLinePointX;

        float textXShift;
        float textYShift;

        if (screenConverter.getAngularRealX() < 0) { // если мы просматриваем левее Оу
            if (screenConverter.getAngularRealX() > -screenConverter.getRealWidth()) { // если ось в поле зрения
                firstLinePointX = -0.05f;
                secondLinePointX = 0.05f;
            } else { // если ось в поле зрения
                firstLinePointX = screenConverter.getAngularRealX() + screenConverter.getRealWidth();
                secondLinePointX = screenConverter.getAngularRealX() + screenConverter.getRealWidth() - 0.1f;
            }
            textXShift = -0.06f;
            textYShift = 0.01f;
        } else { // если мы просматриваем правее Оу
            firstLinePointX = screenConverter.getAngularRealX();
            secondLinePointX = screenConverter.getAngularRealX() + 0.1f;

            textXShift = 0.04f;
            textYShift = 0.06f;
        }

        for (int i = downRealBound; i <= upRealBound; i++) {
            new Line(new Vector2(firstLinePointX, i), new Vector2(secondLinePointX, i)).draw(graphics2D, screenConverter);
            if (i != 0)
                new Text(Integer.toString(i), this.defaultFont, new Vector2(firstLinePointX + textXShift, i + textYShift)).draw(graphics2D, screenConverter);
        }
    }
}
