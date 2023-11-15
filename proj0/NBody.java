public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int sum = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int sum = in.readInt();
        double Radius = in.readDouble();
        Body[] bodies = new Body[sum];
        for (int i = 0; i < sum; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        for(double t = 0; t <= T; t = t + dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i = 0; i < bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i < bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
