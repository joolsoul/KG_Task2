package ru.vsu.kudinov_i_m.drawingObject;

import ru.vsu.kudinov_i_m.screen.ScreenConverter;
import ru.vsu.kudinov_i_m.screen.ScreenPoint;
import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;

public class Line implements DrawingObject
{
        private Vector2 point1;
        private Vector2 point2;

        public Line(Vector2 point1, Vector2 point2) {
                this.point1 = point1;
                this.point2 = point2;
        }

        public void setPoint1(Vector2 p1) {
                this.point1 = p1;
        }

        public void setPoint2(Vector2 point2) {
                this.point2 = point2;
        }

        public Vector2 getPoint1() {
                return point1;
        }

        public Vector2 getPoint2() {
                return point2;
        }

        @Override
        public void draw(Graphics2D graphics2D, ScreenConverter screenConverter) {
                ScreenPoint point1 = screenConverter.realToScreen(this.point1);
                ScreenPoint point2 = screenConverter.realToScreen(this.point2);
                graphics2D.drawLine(point1.getScreenX(), point1.getScreenY(), point2.getScreenX(), point2.getScreenY());
        }
}
