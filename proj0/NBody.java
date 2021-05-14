import java.util.Scanner;

public class NBody {

    public static double readRadius(String filename){
        // Read the second number from data(the body's radius)
        // Use in class
        In in = new In(filename);
        double first = in.readDouble(); // readDouble() also reads in int data, so need to pass it
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        // Read an array of corresponding bodies in the file and return
        In in = new In(filename);
        int index = 0;

        int size = in.readInt(); // ! The first number is # bodies
        double radius = in.readDouble();

        Body[] bodies = new Body[size];

        // When operating on files, remember to check whether the end of the file is eof
        while (index < size){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Body a = new Body(xPos, yPos, xVel, yVel, mass, img);
            bodies[index] = a;
            index++;
        }

        return bodies;
    }

    public static void main(String args[]) {

        // ! T, dt, filename come in as command line arguments --> Use Scanner class
        //  Need to convert T and dt to doubles

        Scanner in = new Scanner(System.in);

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        // Read in bodies and radius
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        double time = 0.0;
        int num = bodies.length;

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(1.0, 100.0);
        StdDraw.setYscale(1.0, 100.0);
        StdDraw.enableDoubleBuffering();

        while (time < T){
            // Update:
            double xForces[] = new double[num];
            double yForces[] = new double[num];

            // compute all the new forces
            int index = 0;
            for (Body b: bodies) {
                xForces[index] = b.calcNetForceExertedByX(bodies);
                yForces[index] = b.calcNetForceExertedByY(bodies);
                index++;
            }
            // update position
            index = 0;
            for (Body b: bodies){
                b.update(dt, xForces[index], yForces[index]);
                index++;
            }

            // Clear:
            StdDraw.clear();

            // Background:
            StdDrawBackground();

            // Bodies:
            // Draw all the bodies
            for (Body a: bodies){
                a.draw();
            }

            // Show the offscreen buffer
            StdDraw.show();

            // Pause the animation for 10 miliseconds
            StdDraw.pause(10);  // unit: miliseconds

            // Increase T by dt
            T += dt;
        }

    }

    public static void StdDrawBackground(){
        // Maybe double buffering here causes the problem?

//        StdDraw.enableDoubleBuffering();

        StdDraw.setXscale(-200, 200);
        StdDraw.setYscale(0, 600);

//        StdDraw.clear();

        String imgToDraw = "images/starfield.jpg";

        StdDraw.picture(50, 300, imgToDraw, 500, 600);

//        StdDraw.show();

//        StdDraw.pause(100);
    }
}