package ru.vsu.kudinov_i_m.drawingObject;

import ru.vsu.kudinov_i_m.screen.ScreenConverter;
import ru.vsu.kudinov_i_m.screen.ScreenPoint;
import ru.vsu.kudinov_i_m.math.Vector2;
import ru.vsu.kudinov_i_m.util.DrawUtils;

import java.awt.*;

public class Text implements DrawingObject {
    private final String text;
    private final Vector2 realCoordinates;
    private final Font font;

    public Text(String text, Font font, Vector2 realCoordinates) {
        this.text = text;
        this.realCoordinates = realCoordinates;
        this.font = font;
    }

    @Override
    public void draw(Graphics2D graphics2D, ScreenConverter screenConverter) {
        DrawUtils.drawWithFont(graphics2D, this.font, () -> {
            ScreenPoint screenCoordinates = screenConverter.realToScreen(this.realCoordinates);
            graphics2D.drawString(this.text, screenCoordinates.getScreenX(), screenCoordinates.getScreenY());
        });
    }

}
