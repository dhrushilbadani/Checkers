public class Piece
{
	private boolean isFire, isKing= false;
	private Board b;
	private int x,y;
	private String type;
	private int no_captured=0;

	public Piece (boolean isFire, Board b, int x, int y, String type)
	{
		this.isFire= isFire;
		this.b=b;
		this.x=x;
		this.y=y;
		this.type=type;

	}

	public boolean isFire()
	{
		return isFire;
	}

	public int side()
	{
		if (isFire==true)
		{
			return 0;
		}
		return 1;
	}

	public boolean isKing()
	{
		/*if ( (isFire()==true && y==7) || (isFire()==false && y==0) )
			return true;
		else
			return false; */
			return isKing;
	}

	public boolean isBomb()
	{
		return (type.equals("bomb"));
	}

	public boolean isShield()
	{
		return (type.equals("shield"));
	}

	public void move(int x, int y)
	{
		if ( (isFire() && y==7) || ((isFire()==false) && y==0))
			{isKing = true;}
		int x_del=1, y_del=1, xl, yl;
		if (this.x > x)
			{x_del=-1;}
		if (this.y > y)
			{y_del=-1;}
		xl=this.x+x_del; yl=this.y+y_del; 
		Piece p=b.pieceAt(xl,yl);
		Piece p2;
		if (p!=null)
		{boolean check= p.isFire && this.isFire;
		if (check==false)
			{
			b.remove(xl,yl);
			no_captured+=1;}
		}

		b.remove(this.x, this.y);
		this.x=x;
		this.y=y;
		b.place(this,this.x,this.y);

		boolean hasExploded=false;
		if (this.isBomb() && no_captured>0)
		{
			for (xl=x-1; xl<=x+1; xl=xl+1)
			{
				for (yl=y-1; yl<=y+1; yl=yl+1)
				{
					p2=b.pieceAt(xl,yl);
					if (p2!=null && p2.isShield()==false && (xl!=x || yl!=y) )
						{b.remove(xl,yl);
						no_captured+=1;
						hasExploded=true;
					}
				}

			}
		/* if (hasExploded==true) */
			b.remove(this.x,this.y);
						
		}
			}


	public boolean hasCaptured()
	{
		if (no_captured>0)
			return true;
		return false;
	}

	public void doneCapturing()
	{
		no_captured=0;

	}









}