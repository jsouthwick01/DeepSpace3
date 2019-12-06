/*
Jacob Southwick
CSIS 1410
Deep Space 3
10/8/19

This is the first draft in a class for this project.
*/ 
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Spaceship extends Objectable
{
	//What do we need to know about the spaceship, which doubles as the player?
	//Lives, Position, rotation.  ---TODO--- Think of more.
	public static int lives = 3; 
	private int rotation;
	private BufferedImage[] bia;
	
	
	//This will be a constructor called after the previous ship is destroyed.
	public Spaceship(int xPos, int yPos, int imageW, int imageH, BufferedImage[] bi, int rotation)
	{
		super(xPos, yPos, imageW, imageH, bi[rotation],0,0);
		this.rotation = rotation;
		bia = new BufferedImage[bi.length];
		for(int i = 0; i < bi.length; i++)
		{
			System.out.println(i);
			System.out.println(bi[i]);
			bia[i]= bi[i];
		}
	}
	
	public int getRotation(){
		return rotation;
	}
	public void setRotation(int r) //Only 1 or -1 should be inputted. This merely rotates one position.
	{
		
		if(Math.abs(r) == 1)
		rotation += r;
		if(rotation < 0)
		{
			rotation = 7;
		}
		if(rotation > 7)
		{
			rotation = 0;
		}
		System.out.println(r); //0 is default up position. Or it should be.
	}
	
	
	public void setV(boolean zero) 
	{
		System.out.println(rotation); //0 is default up position. Or it should be.

		if(!zero)
		{
		switch(rotation)
		{
			
			case 0:
				vx = 0;
				vy = -20;
				break;
			case 1:
				vx = 10;
				vy = -10;
				break;
			case 2:
				vx = 20;
				vy = 0;
				break;
			case 3:
				vx = 10;
				vy = 10;
				break;
			case 4:
				vx = 0;
				vy = 20;
				break;
			case 5:
				vx = -10;
				vy = 10;
				break;
			case 6:
				vx = -20;
				vy = 0;
				break;
			case 7:
				vx = -10;
				vy = -10;
				break;
			}
		
		}
		else
		{
			vx = 0;
			vy = 0;
		}
	}
	
	
	public boolean isVZero()
	{
		if(vx == 0 && vy == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void drawImage(Graphics g)
	{
		setBI(bia[rotation]);
		super.drawImage(g);
	}
}