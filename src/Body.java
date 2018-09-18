
public class Body {
private double myXPos ;
private double myYPos ;
private double myXVel ;
private double myYVel ;
private double myMass ;
private String myFileName ;
/**
 * Create a Body from parameters
 * @param x initial x position
 * @param y initial y position
 * @param xv initial x velocity
 * @param yv initial y velocity
 * @param mass of object
 * @param filename  of image for object animation
 */
public Body(double x, double y, double xv, double yv, double mass, String filename) {
myXVel = xv ;
myYVel = yv ;
myXPos = x ;
myYPos = y ;
myMass = mass ;
myFileName = filename ;
}
/**
 * Return initial x-position of this Body
 * @return value of initial x-position
 */
public double getX() {	
	return myXPos; }
/**
 * Return initial y-position of this Body
 * @return value of y-position 
 */
public double getY() {	
	return myYPos; }
/**
 * Return x-velocity of this Body
 * @return value of x-velocity 
 */
public double getXVel() {	
	return myXVel; }
/**
 *Return y-velocity of this Body 
 * @return value of y-velocity
 */
public double getYVel() {	
	return myYVel; }
/**
 * Return mass of this Body
 * @return value of mass 
 */
public double getMass() {
	return myMass; }
/**
 * Return filename of image for object animation
 * @return gif of the image of the object
 */
String getName() { 
	return myFileName; }
/**
 * Copy constructor: copy instance variables from one body to this body
 * @param b used to initialize this body
 */
public Body(Body b) { 
	this(b.getX(),b.getY(),b.getXVel(), b.getYVel(), b.getMass(),b.getName() );
	
}
/**
 * Return the distance between this body and another
 * @param b the other body to which distance is calculated
 * @return value of the distance between this body and b
 */
public double calcDistance(Body b) {	
    return Math.sqrt(((myXPos-b.getX())*(myXPos-b.getX()))+((myYPos-b.getY())*(myYPos-b.getY())));
}
/**
 * Calculates and returns the force exerted on this body by another
 * @param b the other body to which force is calculated
 * @return value of the force exerted on this body by another body
 */
public double calcForceExertedBy(Body b) {
	return (6.67 * Math.pow(10, -11)) * ((myMass * b.getMass())/(calcDistance(b)*calcDistance(b))) ;
}
/**
 * Calculates and returns the force exerted on this body by another in the x direction
 * @param b the other body to which force is calculated in the x direction
 * @return value of the force exerted on this body by another body in the x direction
 */
public double calcForceExertedByX (Body b) {
	return calcForceExertedBy(b) * ((b.getX()-myXPos)/calcDistance(b)) ;
}
/**
 * Calculates and returns the force exerted on this body by another in the y direction
 * @param b the other body to which force is calculated in the y direction
 * @return value of the force exerted on this body by another body in the y direction
 */
public double calcForceExertedByY (Body b) {
	return calcForceExertedBy(b) * ((b.getY()-myYPos)/calcDistance(b)) ;	
}
/**
 * Calculates and returns sum of the net forces exerted on this body by the other bodies in the system in  x direction
 * @param bodies the other bodies in the system to which net force is calculated in the x direction
 * @return value of the sum of the net forces exerted on this body by other bodies in the system in the x direction
 */
public double calcNetForceExertedByX (Body[] bodies) { 
	double sumX = 0 ;
	for (Body b :bodies) {
		if(!b.equals(this)) { 
			sumX = sumX + calcForceExertedByX(b);
		}
	}
	return sumX ;
}
/**
 * Calculates and returns the sum of net forces exerted on this body by the other bodies in the system in  y direction
 * @param bodies the other bodies in the system to which net force is calculated in the y direction
 * @return value of the sum of net force exerted on this body by other bodies in the system in the y direction
 */
public double calcNetForceExertedByY (Body[] bodies) { 
	double sumY = 0 ;
	for (Body b :bodies) {
		if(!b.equals(this)) { 
			sumY = sumY + calcForceExertedByY(b);
		}
	}
	return sumY ;
}
/**
 * updates the function every increment of time with new values for net forces calculated in the x and y directions in order to sumulate animation
 * @param deltaT tracks the passage of time and updates gif every time a new value is passed to simulate animation
 * @param xforce array of sum of the net forces acting on each of bodies (with exception of the body in question) in the x-direction
 * @param yforce array of sum of the net forces acting on each of bodies (with exception of the body in question) in the y-direction
 * 
 */
public void update(double deltaT, double xforce, double yforce) {

double ax = xforce / (getMass()) ;
double ay = yforce / (getMass()) ;
double nvx = myXVel + (deltaT * (ax));
double nvy = myYVel + (deltaT * (ay));
double nx = myXPos + (deltaT * (nvx));
double ny = myYPos + (deltaT * (nvy));
myXPos=nx;
myYPos=ny;
myXVel=nvx;
myYVel=nvy;
}
/**
 * places a image of the gif at the specific x/y cordinates on a grid
 * 
 */
public void draw() {StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
}
}



