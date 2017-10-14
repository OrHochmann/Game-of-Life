import java.util.concurrent.ThreadLocalRandom;

public class NextGeneration
{
	int n;
	private char[][] lifeArray; //current Generation
	private char[][] assistArray;// previous Generation
	private static final char[] cellOptions = new char[]
			{'W','P'};
	
	NextGeneration(int num)
	{
		n=num;
		lifeArray = new char[n][n];
		assistArray = new char[n][n];
		FillArray();
	}
	
	public char getCellVal(int i, int j) //return the value of a cell
	{
		return lifeArray[i][j];
	}
	
	public void doNext() //calculate the next generation 
	{
		nextGen();
	}
	
	
	private void nextGen() //will fill the new generation data to the lifeArray
	{
		copyArr();		
		for(int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				newCell(i,j);
					
			}
		}
	}
		
		
	private void newCell(int i, int j) //will calculate the new state of the cell at the next generation 
	{
		int number;
		char cell;
		
		cell=assistArray[i][j];
		number=numberOfLiving(i,j);
		lifeArray[i][j]= val(number, cell);
	}
	
		
	private int numberOfLiving(int i, int j) //count the number of living cells arount the current cell
	{
		int left, right;
		int top, bot;
		int sum=0;
		
		top=gettop(j);
		bot=getbot(j);
		left=gettop(i);
		right=getbot(i);
		
		for (int c=left; c<=right; c++)
		{	
			for (int r=top; r<=bot; r++)
			{
				
				if (!((c==i) && (r==j)))
				{
					if (assistArray[c][r]=='P')
					{
						sum=sum+1;
					}
				}
			}
		}
		return sum;
	}

	private char val(int number, char cell)
	{
		if (cell=='W') //dead cell with exactly three live neighbors becomes a live 
		{
			if (number==3)
				{
				return 'P';
				}
		}
		
		else if (cell=='P')//live cell 
		{
			if ((number>=4)||(number<=1))//fewer than two or more than three live neighbors or dies
			{
				return 'W';
			}
		}
		
		return cell; //if none of the above - stay the same
	}

	private void copyArr() //will copy the current state to the assist array
	{
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				assistArray[i][j]=lifeArray[i][j];
			}
		}
	}

	//******************** assist function - for define the limit ********************//
	private int gettop(int i) //get the upper limit
	{
		int top;

		if (i==0)
		{
			top=i;
		}
		else
		{
			top=i-1;
		}
		
		return top;
	}
	
	private int getbot(int i)//get the lower bond
	{
		int bot;
		
		if (i==(n-1))
		{
			bot=i;
		}
		else 
		{
			bot=i+1;
		}
		
		return bot;
	}
	
	//******************** used for the settings of the screen. first use only ********************//
	
	private char randChar() //get random char. W- white, P - Pink. pink is the live cell
	{
		int rand = ThreadLocalRandom.current().nextInt(0, cellOptions.length);
		return cellOptions[rand];
	}
	
	private void FillArray() //first fill of the universe 
	{
		
		for (int i=0 ; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				lifeArray[i][j]=randChar();	
			}
		}
	}
}
