import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LifeScreen extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel pSouth;
	private  JPanel pCenter = new JPanel(); //create panel for the cells
	
	private int n; //save the size of the universe 
	private int GenNum;
	private JLabel lblText; 
	private JLabel[][] lblCells;
	private JButton cmdNext, cmdReset;
	
	private NextGeneration gen ;
	
	LifeScreen(int num)
	{
		n=num;
		screen();
	}
	
	
	private void screen() 
	{
		gen = new NextGeneration(n);
		lblCells = new JLabel[n][n];
		cmdNext = new JButton("Next");
		cmdReset = new JButton("New Game");
		GenNum = 0;
		lblText= new JLabel("Generation number: "+GenNum);
		lblText.setOpaque(true);
		
		setScreen();
		
		
		cmdNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
				actionPer();
				}

				private void actionPer() 
				{
					gen.doNext();
					GenNum++;
					turn();
				}
			});
		
		cmdReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
				{
				actionPer();
				}

				private void actionPer() 
				{
					gen = new NextGeneration(n);
					GenNum=0;
					turn();
				}
			});
	}
	
	private void turn()
	{
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				setcolor(i,j);
				lblText.setText("Turn number: "+GenNum);
			}
		}
	}
	
	
	
	private void setScreen() //
	{
		pCenter.setLayout(new GridLayout(n,n,2,2));//set the panel size to n*n
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				lblCells[i][j] = new JLabel();
				lblCells[i][j].setOpaque(true);
				pCenter.add(lblCells[i][j]);
			}
		}
		
		pCenter.setOpaque(true);
		pCenter.setVisible(true);
		pSouth = new JPanel();
		pSouth.setLayout(new GridLayout(1,3));
		pSouth.add(lblText);
		pSouth.add(cmdNext);
		pSouth.add(cmdReset);
		pSouth.setOpaque(true);
		
		turn();
		
		
		this.setLayout(new BorderLayout()); //set panel layout
		this.add(pCenter, BorderLayout.CENTER); //add the cells grid
		this.add(pSouth, BorderLayout.SOUTH);
		this.setTitle("Game of Life");
		this.setVisible(true);
		this.repaint();
		
	}
	
	
	private void setcolor(int i, int j) //set cell color
	{
		char col = gen.getCellVal(i,j);
		
		if (col=='W')
			lblCells[i][j].setBackground(Color.white);
		else if (col=='P')
			lblCells[i][j].setBackground(Color.pink);
		
	}
	
	
}
