package Exe.EX3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class MyMap2DTest {

	private static final String EX3 = null;
	private static MyMap2D maptest= null;


	@Test
	void recttest()
	{
		maptest=new MyMap2D(4);
		maptest.fill(Color.WHITE.getRGB());
		Point2D p1= new Point2D(1,2);
		Point2D p2= new Point2D(3,1);
		Point2D p3= new Point2D(2,2);
		Point2D p5= new Point2D(1,3);
		
		maptest.drawRect(p1, p2, Color.BLACK.getRGB());
		assertEquals( Color.BLACK.getRGB(),maptest.getPixel(p3));
		assertEquals( Color.WHITE.getRGB(),maptest.getPixel(p5));

	}
	@Test
	void drawCircle()
	{
		
		Point2D p4= new Point2D(3,1);
		Point2D p6= new Point2D(1,3);
		Point2D p7= new Point2D(1,1);
		maptest.drawCircle(p4, 1, Color.BLACK.getRGB());
		assertEquals( Color.BLACK.getRGB(),maptest.getPixel(p7));
		assertEquals( Color.WHITE.getRGB(),maptest.getPixel(p6));

		
	}
	
	
	@Test
	void segmenttest()
	{
		maptest=new MyMap2D(4);
		maptest.fill(Color.WHITE.getRGB());
		Point2D p11= new Point2D(2,3);
		Point2D p10= new Point2D(0,0);
		Point2D p9= new Point2D(1,1);
		Point2D p8= new Point2D(1,0);
		maptest.drawSegment(p11, p10, Color.BLACK.getRGB());
		assertEquals( Color.BLACK.getRGB(),maptest.getPixel(p9));
		assertEquals( Color.WHITE.getRGB(),maptest.getPixel(p8));
		
		
	}
	
	@Test
	void nextGenGol()
	{
		Point2D p12= new Point2D(2,1);
		Point2D p13= new Point2D(2,2);
		Point2D p14= new Point2D(2,3);
		maptest=new MyMap2D(4);
		maptest.fill(Color.WHITE.getRGB());
		maptest.setPixel(p12, Color.BLACK.getRGB());
		maptest.setPixel(p13, Color.BLACK.getRGB());
		maptest.setPixel(p14, Color.BLACK.getRGB());
		maptest.nextGenGol();
		
		Point2D p15= new Point2D(1,2);
		Point2D p17= new Point2D(3,2);
		Point2D p16= new Point2D(3,3);
		
		
		assertEquals( Color.BLACK.getRGB(),maptest.getPixel(p15));
		assertEquals( Color.WHITE.getRGB(),maptest.getPixel(p17));
		assertEquals( Color.WHITE.getRGB(),maptest.getPixel(p16));
		
		
		
	}
	@Test
	void fill1()
	{
		Point2D p18= new Point2D(3,3);
		maptest.fill(p18,  Color.BLACK.getRGB());
		for(int i=0;i<4; i++)
		{
			for(int j=0;j<4;j++)
				assertEquals( Color.BLACK.getRGB(),maptest.getPixel(i,j));	
		}
		
		
	}
	

	@Test
	void fill2()
	{
		Point2D p19= new Point2D(1,1);
		Point2D p20= new Point2D(1,2);
		Point2D p21= new Point2D(2,1);
		Point2D p22= new Point2D(2,2);
		maptest.setPixel(p22,Color.RED.getRGB());
		maptest.setPixel(p21,Color.RED.getRGB());
		maptest.setPixel(p20,Color.RED.getRGB());
		maptest.setPixel(p19,Color.RED.getRGB());
		maptest.fill(0, 0, Color.BLACK.getRGB());
		
		assertEquals( Color.BLACK.getRGB(),maptest.getPixel(0,1));
		assertEquals( Color.RED.getRGB(),maptest.getPixel(2,2));
	}
	

}