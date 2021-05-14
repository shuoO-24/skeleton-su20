
public class UniverseTrace {
    private final double radius;     // radius of universe
    private final int n;             // number of bodies
    private final Body[] bodies;     // array of n bodies

    // read universe from standard input
    public UniverseTrace() {

        // number of bodies
        n = StdIn.readInt();

        // the set scale for drawing on screen
        radius = StdIn.readDouble();
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        // read in the n bodies
        bodies = new Body[n];
        for (int i = 0; i < n; i++) {
            double rx   = StdIn.readDouble();
            double ry   = StdIn.readDouble();
            double vx   = StdIn.readDouble();
            double vy   = StdIn.readDouble();
            double mass = StdIn.readDouble();
            double[] position = { rx, ry };
            double[] velocity = { vx, vy };
            Vector r = new Vector(position);
            Vector v = new Vector(velocity);
            bodies[i] = new Body(r, v, mass);
        }
    }

    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        Vector[] f = new Vector[n];
        for (int i = 0; i < n; i++) {
            f[i] = new Vector(new double[2]);
        }

        // compute the forces
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    f[i] = f[i].plus(bodies[i].forceFrom(bodies[j]));
                }
            }
        }

        // move the bodies
        for (int i = 0; i < n; i++) {
            bodies[i].move(f[i], dt);
        }
    }

    // draw the n bodies
    public void draw() {
        for (int i = 0; i < n; i++) {
            // if (i == 1 || i == 2) bodies[i].draw(0.05);
            bodies[i].draw(0.025);
        }
    }


    // client to simulate a universe
    public static void main(String[] args) {
        StdDraw.setCanvasSize(700, 700);
        Universe newton = new Universe();
        double dt = Double.parseDouble(args[0]);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.setPenColor(StdDraw.WHITE);
            newton.draw();
            newton.increaseTime(dt);
            StdDraw.setPenColor(StdDraw.BLACK);
            newton.draw();
            StdDraw.show();
            StdDraw.pause(50);
        }
    }
}