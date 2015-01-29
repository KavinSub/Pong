import java.awt.geom.*;

public class Paddle{
	double velX, velY;
	double x, y;
	Rectangle2D paddle;
	static Double xSpeedMax = 0.0, ySpeedMax = 1.5;
	static int height = 100;
	static int width = 10;

	public Paddle(double x, double y){
		velX = 0.0;
		velY = 0.0;
		this.x = x;
		this.y = y;
		paddle = new Rectangle2D.Double(x - width/2, y - height/2, width, height);
	}

	public void up(){
		velY = -ySpeedMax;
	}

	public void down(){
		velY = ySpeedMax;
	}

	public void left(){
		velX = -xSpeedMax;
	}

	public void right(){
		velX = xSpeedMax;
	}

	public void stop(){
		velX = 0.0;
		velY = 0.0;
	}

	public void move(){
		x += velX;
		y += velY;
		paddle = new Rectangle2D.Double(x - width/2, y - height/2, width, height);
	}

	public void checkBounds(){
		if(y < Paddle.height/2){
			velY = 0.0;
			y = Paddle.height/2;
		}else if(y > Pong.screenHeight - Paddle.height/2){
			velY = 0.0;
			y = Pong.screenHeight - Paddle.height/2;
		}
	}
}