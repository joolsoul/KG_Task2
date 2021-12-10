package ru.vsu.kudinov_i_m;

import java.awt.*;

public class Line implements Drawing
{
        private Vector2 p1;
        private Vector2 p2;

        public Line(Vector2 p1, Vector2 p2) {
                this.p1 = p1;
                this.p2 = p2;
        }

        public void setP1(Vector2 p1) {
                this.p1 = p1;
        }

        public void setP2(Vector2 p2) {
                this.p2 = p2;
        }

        public Vector2 getP1() {
                return p1;
        }

        public Vector2 getP2() {
                return p2;
        }

        @Override
        public void draw(Graphics2D graphics2D, ScreenConverter screenConverter) {
                ScreenPoint point1 = screenConverter.realToScreen(p1);
                ScreenPoint point2 = screenConverter.realToScreen(p2);
                graphics2D.drawLine(point1.getScreenX(), point1.getScreenY(), point2.getScreenX(), point2.getScreenY());
        }
}
