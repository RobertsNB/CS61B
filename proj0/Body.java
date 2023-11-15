public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	//This method is used for calcute the distance between two bodys.
	public double calcDistance (Body b){
		double x = b.xxPos - this.xxPos;
		double y = b.yyPos - this.yyPos;
		double distance = Math.sqrt(x*x + y*y);
		return distance;
	}

	//This method is used for calcute the force exerted on this body by the given body.
	public double calcForceExertedBy (Body b){
		double G = 6.67e-11;
		double distance = calcDistance(b);
		double force = (G * this.mass * b.mass)/Math.pow(distance,2);
		return force;
	}

	public double calcForceExertedByX (Body b){
		double index1 = b.xxPos - this.xxPos;
		//double x = (index1 < 0)? -index1:index1;
		double x = index1;
		double force = calcForceExertedBy(b);
		double distance = calcDistance(b);
		double Fx = (force * x) / distance;
		return Fx;
	}
	
	public double calcForceExertedByY(Body b){
		double index2 = b.yyPos - this.yyPos;
		//double y = (index2 < 0)? -index2:index2;
		double y = index2;
		double force = calcForceExertedBy(b);
		double distance = calcDistance(b);
		double Fy = (force * y) / distance;
		return Fy;
	}

	public double calcNetForceExertedByX (Body[] bodys){
		double netFx = 0;
		for(Body body : bodys){
			if(!body.equals(this)){
				double force = calcForceExertedByX(body);
				netFx += force;
			}
		}
		return netFx;
	}

	public double calcNetForceExertedByY (Body[] bodys){
		double netFy = 0;
		for(Body body : bodys){
			if(!body.equals(this)){
				double force = calcForceExertedByY(body);
				netFy += force;
			}
		}
		return netFy;
	}

	public void update (double dt, double fX, double fY){
		double aNetX = fX / this.mass;
		double aNetY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aNetX;
		this.yyVel = this.yyVel + dt * aNetY;
		this.xxPos = this.xxPos + dt * xxVel;
		this.yyPos = this.yyPos + dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}
