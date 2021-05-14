public class Spirograph {

    public static void main(String[] args) {
        double R = Double.parseDouble(args[0]);
        double r = Double.parseDouble(args[1]);
        double a = Double.parseDouble(args[2]);

        StdDraw.setXscale(-300, +300);
        StdDraw.setYscale(-300, +300);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();

        for (double t = 0.0; t < 100; t += 0.01) {
            double x = (R+r) * Math.cos(t) - (r+a) * Math.cos(((R+r)/r)*t);
            double y = (R+r) * Math.sin(t) - (r+a) * Math.sin(((R+r)/r)*t);
            double degrees = -Math.toDegrees((R+r)/r)*t;
            StdDraw.picture(x, y, "earth.gif", degrees);
            // StdDraw.rotate(+Math.toDegrees((R+r)/r)*t);
            StdDraw.show();
            StdDraw.pause(20);
        }

    }

}