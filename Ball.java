import java.awt.geom.*;

public class Ball{
	double x, y;
	double velX, velY;
	Ellipse2D circle;
	static double radius = 20;
	static double accelerationX = 0.1;
	static double accelerationY = 0.2;
	static double startVelX = 1.0;
	static double startVelY = 1.0;

	public Ball(int x, int y){
		velX = startVelX;
		velY = startVelY;
		this.x = x;
		this.y = y;
		circle = new Ellipse2D.Double(x, y, radius, radius);
	}

	public void paddleCollision(){
		velX = -velX;
		velY = -velY;
	}

	public void move(){
		x += velX;
		y += velY;
		circle = new Ellipse2D.Double(x, y, radius, radius);
	}

	public void checkBounds(){
		if(y > Pong.screenHeight - Ball.radius  || y < 0){
			velY = -velY;
		}

	}

}