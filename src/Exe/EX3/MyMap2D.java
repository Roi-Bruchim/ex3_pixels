package Exe.EX3;
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. 
 * 
 * @author
 * ID1: 322695883
 * ID2: 
 * */
public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}

	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		//used Bresenham algorithm
		//we searched for a way to draw a line between 2 points in java
		//and found this algorithm, we read about  it and understood it 
		// and implemented it in the code below
		// TODO Auto-generated method stub
		int x0= p1.ix();
		int y0= p1.iy();
		int x1= p2.ix();
		int y1= p2.iy();
		if(Math.abs(y1-y0)< Math.abs(x1-x0))
		{
			//calling helper function below
			if(x0>x1)
			{
				drawLineLow(x1,y1,x0,y0,v);
			}
			else
			{
				drawLineLow(x0,y0,x1,y1,v);
			}
		}
		else
		{
			if(y0>y1)
			{
				//calling helper function below
				drawLineHigh(x1,y1,x0,y0,v);
			}
			else
			{
				drawLineHigh(x0,y0,x1,y1,v);
			}
		}
		
	}
	
	//this function is used in drawSegment function
	//it draws a line between two points
	private void drawLineLow(int x0 ,int y0, int x1, int y1, int v)
	{
		int dx= x1-x0;
		int dy= y1-y0;
		int yi=1;
		if(dy<0)
		{
			yi=-1;
			dy=-dy;
		}
		int d= 2*dy -dx;
		int y= y0;
		for(int x=x0;x<=x1;x++)
		{
			this.setPixel(x,y,v);
			if(d>0)
			{
				y=y+yi;
				d=d-2*dx;
			}
			d=d+2*dy;
		}
	}
	

	
	
	//this function is used in drawSegment function
	//it draws a line between two points
	private void drawLineHigh(int x0 ,int y0, int x1, int y1, int v)
	{
		int dx= x1-x0;
		int dy= y1-y0;
		int xi=1;
		if(dx<0)
		{
			xi=-1;
			dx=-dx;
		}
		int d=2*dx-dy;
		int x=x0;
		for(int y=y0; y<=y1;y++)
		{
			this.setPixel(x, y, v);
			if(d>0) 
			{
				x=x+xi;
				d=d-2*dy;
			}
			d=d+2*dx;
		}
	}
	
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		// TODO Auto-generated method stub
		//checking the minimum and maximum values of the points
		int minX=Math.min(p1.ix(),p2.ix());
		int maxX=Math.max(p1.ix(),p2.ix());
		int minY=Math.min(p1.iy(),p2.iy());
		int maxY=Math.max(p1.iy(),p2.iy());
		//for every point between the minimum and maximum area should painted in chosen color
		for(int i = 0;i<this.getWidth();i++)
		{
			for(int j = 0;j<this.getHeight();j++)
			{
				if((i>=minX) && (i<=maxX) && (j>=minY) && (j<=maxY))
				{
					this.setPixel(i,j,col);
				}
			}
		}
	}

	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		// TODO Auto-generated method stub
        
		for(int x = 0;x<this.getWidth();x++) {


			for(int y = 0;y<this.getHeight();y++)
			{
				//checking if the distance of our current (x,y) from the center point (p) is shorter thar rad.
				//if it is we will paint it
				double t=Math.pow(p.ix()-x,2)+Math.pow(p.iy()-y,2);
				if(Math.sqrt(t)<=rad)
				{
					this.setPixel(x, y, col);
				}

			}
		}
	}

	@Override
	//this is a recursive function.
	//our goal  is to fill the inside area of a shape with a specific color
	//we start from a point inside the shape, and we check if the pixel is the same color as the shape color
	//if it is we change if to the fill color, and we call the function again with the new point
	//our stop case is when the pixel is not the same color as the shape color or reaching map limits
	public int fill(Point2D p, int new_v) {
		// TODO Auto-generated method stub
		int old_v = this.getPixel(p.ix(), p.iy());
		if(old_v==new_v)
			return 0;
		this.setPixel(p.ix(), p.iy(), new_v);
		if(p.ix()+1< this.getWidth() && this.getPixel(p.ix()+1, p.iy())==old_v)
            fill(new Point2D (p.ix()+1, p.iy()),new_v);
		if(p.ix()-1>=0 && this.getPixel(p.ix()-1, p.iy())==old_v)
			fill(new Point2D (p.ix()-1, p.iy()),new_v);
		if(p.iy()+1<this.getHeight() && this.getPixel(p.ix(), p.iy()+1)==old_v)
			fill(new Point2D (p.ix(), p.iy()+1),new_v);
		if(p.iy()-1>=0 && this.getPixel(p.ix(), p.iy()-1)==old_v)
			fill(new Point2D (p.ix(), p.iy()-1),new_v);

		return 0;  
	}

	@Override
	public int fill(int x, int y, int new_v) {
		// TODO Auto-generated method stub
		fill(new Point2D (x,y),new_v);
		return 0;
	}

	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return shortestPath(p1,p2).length;
	}

	@Override
	public void nextGenGol() {
		// TODO Auto-generated method stub
		//original map
		int [][]mat1= new int[this.getWidth()][this.getHeight()];
		//helping map
		int [][]mat2= new int[this.getWidth()][this.getHeight()];
		//every black point be 1, every white point be 0
		for(int i=0;i<this.getWidth();i++)
		{
			for(int j=0;j<this.getHeight();j++)
			{
				if(this.getPixel(i, j)==BLACK)
					mat1[i][j]=1;
				if(this.getPixel(i, j)==WHITE)
					mat1[i][j]=0;
			}
		}
			for(int k=1;k<this.getWidth()-1;k++)
			{
				for(int m=1;m<this.getHeight()-1;m++)
				{
					//check if the point is white or black
					if(mat1[k][m]==0)
					{
						//check if white point has 3 black neighbors 
						if(mat1[k-1][m-1]+mat1[k-1][m]+mat1[k-1][m+1]+mat1[k][m-1]+mat1[k][m+1]+mat1[k+1][m-1]+mat1[k+1][m]+mat1[k+1][m+1]==3)
							//if it has, the point is gonna be black (save the point in helping mat)
						{
							mat2[k][m]=1;
						}
						    // if it isn't the point remain white in next gol
						else
						{
							mat2[k][m]=0;
						}
					}
					else
					{
						//check if black point has 2 or 3 black neighbors
						if ((mat1[k-1][m-1]+mat1[k-1][m]+mat1[k-1][m+1]+mat1[k][m-1]+mat1[k][m+1]+mat1[k+1][m-1]+mat1[k+1][m]+mat1[k+1][m+1]==3) || (mat1[k-1][m-1]+mat1[k-1][m]+mat1[k-1][m+1]+mat1[k][m-1]+mat1[k][m+1]+mat1[k+1][m-1]+mat1[k+1][m]+mat1[k+1][m+1]==2))
							//if it has, the point is gonna be black (save the point in helping mat)
						{
							mat2[k][m]=1;
						}
						else
						{
							mat2[k][m]=0;
						}
					}
						
				}
			}
			//painting every point in her next gol color (mat2)
			for(int p=1;p<this.getWidth()-1;p++)
			{
				for(int t=1;t<this.getHeight()-1;t++)
				{
					if(mat2[p][t]==0)
						this.setPixel(p, t, WHITE);
					if(mat2[p][t]==1)
						this.setPixel(p, t, BLACK);
				}
			}
		}
		
		


	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}

}