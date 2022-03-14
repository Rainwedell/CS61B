public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet R) {
        double temp = 0;
        double distance;
        temp = (xxPos - R.xxPos) * (xxPos - R.xxPos) + (yyPos - R.yyPos) * (yyPos - R.yyPos);
        distance = Math.pow(temp, 0.5);
        return distance;
    }

    public double calcForceExertedBy(Planet R) {
        return (6.67 * 1E-11 * mass * R.mass / (calcDistance(R) * calcDistance(R)));
    }

    public double calcForceExertedByX(Planet R) {
        double dx = R.xxPos - xxPos;
        return (calcForceExertedBy(R) * dx / calcDistance(R));
    }

    public double calcForceExertedByY(Planet R) {
        double dy = R.yyPos - yyPos;
        return (calcForceExertedBy(R) * dy / calcDistance(R));
    }

    // 如果
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double NetForceX = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i])) {
                NetForceX += calcForceExertedByX(allPlanets[i]);
            }
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double NetForceY = 0;

        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i])) {
                NetForceY += calcForceExertedByY(allPlanets[i]);
            }
        }
        return NetForceY;
    }

    public void update(double dt, double fX, double fY) {
        xxVel = xxVel + dt * fX / mass;
        yyVel = yyVel + dt * fY / mass;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        String imgToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToDraw);
    }
}
