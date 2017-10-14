
import javax.swing.JFrame;

public class LifeMain
{

	public static void main(String[] args) 
	{
		startGameOfLife(15);
	}

	private static void startGameOfLife(int i) 
	{
		LifeScreen frame = new LifeScreen(i);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
