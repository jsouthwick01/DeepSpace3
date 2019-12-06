

import java.awt.Graphics;
import java.awt.Color;

//I just took this entire class, as it was almost exactly what I needed for my project. Thanks.
public class Bullet1 extends Objectable
{

	public Bullet1(int startx, int starty, int r)
	{
		super(startx, starty);
		//This will determine the proper velocities based on the rotation. If I could just make everything follow the mouse perfectly, that would be preferable.
		//Unfortunately that is either super complicated or something I was too lazy to look into.
		switch(r)
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
	
			
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		//because we added the bullet functions what should go here?
		g.setColor(Color.WHITE);
		g.fillOval(getXPos(), getYPos(), 7,7);
		setXPos(getXPos() + vx);
		setYPos(getYPos() + vy);

	}
	

	
}