public class Body {
    public double xxPos; // current x pos
    public double yyPos; // current y pos
    public double xxVel; // current velocity in x direction
    public double yyVel; // current velocity in y direction
    public double mass; // mass of body
    public String imgFileName; // name of img file depicting the body
    public static final double G = 6.67*Math.pow(10, -11); // constants

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        double disX = b.xxPos - xxPos;
        double disY = b.yyPos - yyPos;
        double distance = Math.sqrt(disX*disX + disY*disY);
        return distance;
    }

    public double calcForceExertedBy(Body b){
        double totalForce = (G * b.mass * mass) / (calcDistance(b) * calcDistance(b));
        return totalForce;
    }

    public double calcForceExertedByX(Body b){
        double forceX = calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
        return forceX;
    }

    public double calcForceExertedByY(Body b){
        double forceY = calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        // Net force in x direction exerted by other two bodies
        double netX = 0.0;
        for (int i = 0; i < bodies.length; i++){
            if (bodies[i] != this){
                netX += calcForceExertedByX(bodies[i]);
            }
        }
        return netX;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        // Net force in y direction exerted by other two bodies
        double netY = 0.0;
        for (int i = 0; i < bodies.length; i++){
            if (!this.equals(bodies[i])){
                netY += calcForceExertedByY(bodies[i]);
            }
        }
        return netY;
    }

    public void update(double dt, double fX, double fY){
        // 1. Calculate the net acceleration
        double aX = fX / mass;
        double aY = fY / mass;

        // 2. Calculate the squirrel's new velocity with dt (change in time)
        double newVelX = xxVel + dt * aX;
        double newVelY = yyVel + dt * aY;

        // 3. Calculate the new position of the squirrel
        double newPosX = xxPos + dt * newVelX;
        double newPosY = yyPos + dt * newVelY;

        xxPos = newPosX;
        yyPos = newPosY;
        xxVel = newVelX;
        yyVel = newVelY;
    }
}