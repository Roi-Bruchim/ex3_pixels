package Exe.EX3;

import java.awt.Color;

/**
 * This class is a simple "inter-layer" connecting (aka simplifing) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * You should change this class!
 * 
 * @author
 * ID1: 322695883
 * ID2: 
 */
public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
	static Point2D pp=new Point2D(0,0);
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();

	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}
	
	 public static void drawGrid(Map2D map) {
		 int w = map.getWidth();
		 int h = map.getHeight();
		 for(int i=0;i<w;i++) {
			 StdDraw_Ex3.line(i, 0, i, h);
		 }
		 for(int i=0;i<h;i++) {
			 StdDraw_Ex3.line(0, i, w, i);
		 }
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;
		//choosing color
		if(p.equals("Blue")) {_color = Color.BLUE; }
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }
		//clearing map
		if(p.equals("Clear")) {_map.fill(WHITE);}
		//choosing map size
		if(p.equals("20x20")) {init(20);}
		if(p.equals("160x160")) {init(160);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("40x40")) {init(40);}

		drawArray(_map);
		
	}
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();
		if(_mode.equals("Point")) {
			_map.setPixel(p,col );
		}
		if(_mode.equals("Fill")) {
			_map.fill(p, col);
			_mode = "none";
		}
		
		//calling rectangle
		if(_mode.equals("Rect2"))
		{
			_map.drawRect(p,pp, col);
			_mode="none";
		}
		
		if(_mode.equals("Rect"))
		{
			pp= new Point2D(p);
			_mode="Rect2";
		}
		
		// calling circle
		if(_mode.equals("Circle2"))
		{
			_map.drawCircle(pp,pp.distance(p), col);
		    _mode="none";
			
		}
		if(_mode.equals("Circle"))
		{
		     pp= new Point2D(p);
			_mode="Circle2";
		}
		
		//calling segment
		if(_mode.equals("Segment2"))
		{
			_map.drawSegment(p, pp, col);
		}
		if(_mode.equals("Segment"))
		{
			pp=new Point2D(p);
			_mode="Segment2";
		}
		//calling fill
		if(_mode.equals("Fill"))
		{
			_map.fill(p, col);
		}
		
		//calling ShortestPath
		if(_mode.equals("ShortestPath2"))
		{
			_map.shortestPathDist(p, pp);
		}
		if(_mode.equals("ShortestPath"))
		{
			pp=new Point2D(p);
			_mode="ShortestPath2";
		}
		
	    //calling Game of life
		if(_mode.equals("Gol")) {
			_map.nextGenGol();	
		}
		drawArray(_map);
	}
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
	
	// getter for color
	public static Color get_color()
	{
		return _color;
	}
}