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
}