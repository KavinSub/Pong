import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

public class Pong extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(5, this); // Calls actionPerformed
	int player1Score = 0, player2Score = 0; 
	Paddle player1;
	Paddle player2;
	JLabel player1ScoreLabel, player2ScoreLabel;
	Ball ball;
	boolean paused;
	Random rng; // The random number generator
	static int screenHeight = 600;
	static int screenWidth = 600;
	static int windowHeight = 800;
	static int windowWidth = 600;

	public Pong(){
		t.start();
		paused = false;
		rng = new Random(System.currentTimeMillis());
		setSize(screenWidth, screenHeight);
		player1 = new Paddle(Paddle.width/2, Paddle.height/2);
		player2 = new Paddle(screenWidth - Paddle.width/2, screenHeight - Paddle.height/2);
		ball = new Ball(screenWidth/2, screenHeight/2);
		player1ScoreLabel = new JLabel("Player 1 score: 0");
		player2ScoreLabel = new JLabel("Player 2 score: 0");
		add(player1ScoreLabel);
		add(player2ScoreLabel);
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

		/*if(player1.paddle.intersects(ball.x, ball.y, Ball.radius * 2, Ball.radius * 2) || player2.paddle.intersects(ball.x, ball.y, Ball.radius, Ball.radius)){
			ball.velX = -ball.velX;
			if(ball.velX < 0){
				ball.velX -= ball.accelerationX;
			}else{
				ball.velX += ball.accelerationX;
			}
		}*/

		if(player1.paddle.intersects(ball.x, ball.y, Ball.radius * 2, Ball.radius * 2)){
			paddleCollision(player1);
		}else if(player2.paddle.intersects(ball.x, ball.y, Ball.radius, Ball.radius)){
			paddleCollision(player2);
		}

		checkScore();
	}

	public void paddleCollision(Paddle player){
		ball.velX = -ball.velX;
		if(ball.y + Ball.radius/2.0 < player.y){
			ball.velY = -1 * (Math.abs(ball.velY) + Ball.accelerationY);
		}else{
			ball.velY = Math.abs(ball.velY) + Ball.accelerationY;
		}
	}

	public void checkScore(){
		if(ball.x + Ball.radius > screenWidth || ball.x < 0){
			if(ball.x < 0){
				player2Score++;
			}else if(ball.x + Ball.radius > screenWidth){
				player1Score++;
			}
			ball.x = screenWidth/2;
			ball.y = screenHeight/2;
			ball.velX = 1.0;
			ball.velY = 1.0;
			player1ScoreLabel.setText(String.format("Player 1 score is: %d\t", player1Score));
			player2ScoreLabel.setText(String.format("Player 2 score is: %d", player2Score));
		}
	}

	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP){
			player2.up();
		}else if(code == KeyEvent.VK_DOWN){
			player2.down();
		}

		if(code == KeyEvent.VK_W){
			player1.up();
		}else if(code == KeyEvent.VK_S){
			player1.down();
		}

		if(code == KeyEvent.VK_P){
			if(!paused){
				paused = true;
				t.stop();
			}else{
				paused = false;
				t.start();
			}
		}

	}

	public void keyReleased(KeyEvent e){
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN){
			player2.stop();
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_S){
			player1.stop();
		}
	}

	public void keyTyped(KeyEvent e){

	}
}