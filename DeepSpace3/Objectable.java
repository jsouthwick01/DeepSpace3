/*
Jacob Southwick
CSIS 1410
Semester Project
10/8/19

This is the first draft in a class for this project.
Also, Objectable is the basic object class. Keep in mind that, because there isn't much more functionality to an asteroid over a ship, all asteroids are straight up Objectables.
*/
//Import GUI.
import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class Objectable 
{
	//Instance variables
	private int xPos;
	private int yPos;
	protected int vx; //Just make it easier for the spaceship to mess with these.
	protected int vy;
	//For my own sake, I know all images will be 30,30, so the half of each dimension is 15. That is all this is for. Makes it easier on me, plus if I changed it to 45/45, I could then easily fix this.
	public static final int box = 15; //Public because you can't change it regardless. Static so I can more easily access it. Also, I know I am breaking the traditional way you write this. I don't care.
	//Because the bullet is so small, I won't worry about finding the middle for when it goes off screen. 
	private BufferedImage bi;
	private int imageW;
	private int imageH;

//Constructors
	Objectable(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	Objectable(int x, int y, int imageW, int imageH, BufferedImage bi, int vx, int vy)
	{
		xPos = x;
		yPos = y;
		this.imageW = imageW;
		this.imageH = imageH;
		System.out.println("W" + imageW);
		System.out.println("H" + imageH);
		this.bi = bi;
		this.vx = vx;
		this.vy = vy;
		
	}
	//Getters and setters for position and rotation.
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int i)
	{
		xPos = i;
	}
	public void setYPos(int i)
	{
		yPos = i;
	}
	public void setBI(BufferedImage bi)
	{
		this.bi = bi;
	}
	public int getWidth()
	{
		return imageW;
	}
	public int getHeight()
	{
		return imageH;
	}
	
	
	
	//Draw method
	public void drawImage(Graphics g) {
		g.drawImage(bi,xPos, yPos, imageW, imageH, null);
		xPos += vx;
		yPos += vy;

	}
	
	public void collision(Objectable o)
	{
		
	}
	
}