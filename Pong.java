import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class Pong extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(5, this);
	int score;
	Paddle player1 = new Paddle(Paddle.width/2, Paddle.height/2);
	Paddle player2 = new Paddle(screenWidth - Paddle.width/2, screenHeight - Paddle.height/2);
	Ball ball = new Ball(screenWidth/2, screenHeight/2);
	static int screenHeight = 600;
	static int screenWidth = 600;

	public Pong(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//player1 = new Paddle(Paddle.width/2, Paddle.height/2);
		//player2 = new Paddle(screenWidth - Paddle.width/2, screenHeight - Paddle.height/2);
		//ball = new Ball(screenWidth/2, screenHeight/2);

		g2.fill(player1.paddle);
		g2.fill(player2.paddle);
		g2.fill(ball.circle);
	}



	public void actionPerformed(ActionEvent e){
		checkBounds();
		ball.move();
		player1.move();
		player2.move();
		repaint();
	}

	public void checkBounds(){
		ball.checkBounds();
		player1.checkBounds();
		player2.checkBounds();

		if(player1.paddle.intersects(ball.x, ball.y, Ball.radius * 2, Ball.radius * 2) || player2.paddle.intersects(ball.x, ball.y, Ball.radius, Ball.radius)){
			ball.velX = -ball.velX;
			if(ball.velX < 0){
				ball.velX -= ball.accelerationX;
			}else{
				ball.velX += ball.accelerationX;
			}
		}

		/*if(ball.x < 0){
			// Player 1 loses			
		}

		if(ball.x + Ball.radius * 2 > screenWidth){
			// Player 2 loses
		}*/

	}

	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP){
			player1.up();
		}else if(code == KeyEvent.VK_DOWN){
			player1.down();
		}

		if(code == KeyEvent.VK_W){
			player2.up();
		}else if(code == KeyEvent.VK_S){
			player2.down();
		}

	}

	public void keyReleased(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN){
			player1.stop();
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_S){
			player2.stop();
		}
	}

	public void keyTyped(KeyEvent e){

	}
}