import javax.swing.*;

public class MainDisplay{
	public static void main(String[] args){
		Pong p = new Pong();
		JFrame frame = new JFrame();
		frame.add(p);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong!");
		frame.setSize(Pong.screenWidth, Pong.screenHeight);
	}

}