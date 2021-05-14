public class BouncingBall {
    public static void main(String[] args) {

        // !!! The following animation loop works
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity
        double radius = 0.03;              // a hack since "earth.gif" is in pixels

        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();


        // main animation loop
        while (true) {
            if (Math.abs(rx + vx) + radius > 1.0) {
                vx = -vx;
                //StdAudio.play("audio/2001.mid");
            }
            if (Math.abs(ry + vy) + radius > 1.0) {
                vy = -vy;
                //StdAudio.play("audio/2001.mid");
            }
            rx = rx + vx;
            ry = ry + vy;
            StdDraw.clear();
            StdDraw.picture(1, 1, "images/ball.gif", 20, 20);
            StdDraw.picture(rx, ry, "images/earth.gif");
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}