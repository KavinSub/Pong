import java.awt.geom.*;

public class Ball{
	double x, y;
	double velX, velY;
	Ellipse2D circle;
	static double radius = 10;
	static double accelerationX = 0.3;

	public Ball(int x, int y){
		velX = 1.0;
		velY = 1.0;
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
		if(y > Pong.screenHeight - Ball.radius * 2  || y < 0){
			velY = -velY;
		}

	}

}