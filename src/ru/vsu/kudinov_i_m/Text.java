package ru.vsu.kudinov_i_m;

import ru.vsu.kudinov_i_m.util.DrawUtils;

import java.awt.*;

public class Text implements Drawing {
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
