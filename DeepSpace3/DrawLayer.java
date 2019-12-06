import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.security.SecureRandom;
//This will be the same as MapDriver in essence
@SuppressWarnings("serial")
public class DrawLayer extends JPanel {
	//Create our objects
	//True to asteroids, you can only have one bullet at a time.
	private Bullet1 b1;
	ArrayList<Objectable> asteroids = new ArrayList<Objectable>(); //maybe change to a new class?
	private Spaceship p1;
	private BufferedImage[] shipImg = new BufferedImage[8];
	private BufferedImage asteroidImg;
	//This allows for me to give the game an end, or at the very least get progressively more complicated.
	private int level;
	private int score;
	private boolean shot;
	private boolean dead;
	//These will be used as ghetto constant variables, because I can't initialize them without getting the variables from later.
	private int HALF_W;
	private int HALF_H;
	private SecureRandom ayn;
	
	
	public DrawLayer()
	{
		//This is my super messy, unorganized constructor. First we deal with the image files.
		try
		{
			asteroidImg = ImageIO.read(new File("markedasteroid.png"));
			for(int i = 0; i < 8; i++)
			{
				shipImg[i] = ImageIO.read(new File("r" + (i+1) + ".png"));
			}
		}
		catch(IOException e)
		{
			System.out.println("Invalid file path");
		}
		//Now we instantiate a bunch of the variables we will be using
		
		
		level = 1;
		score = 0;
		ayn = new SecureRandom();
		shot = false;
		dead = false;
		JOptionPane.showMessageDialog(this, "Try to shoot for the red targets.\n Use 'W' to go forward, and 'A' and 'D' to pivot. Spacebar to shoot. \nGood luck.");
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		//paint in the asteroids and the spaceship
		if(p1 == null)
		{
			
			//I have to put this in here because I need the properties of the JPanel.
			HALF_W = this.getWidth()/2;
			HALF_H = this.getHeight()/2;
			System.out.println(this.getHeight());
			System.out.println(this.getWidth());
			
			p1 = new Spaceship(HALF_W, HALF_H, 30, 30, shipImg, 0);
			createAsteroids(level);
		}

		
		if(Spaceship.lives > 0)
		{
			
			p1.drawImage(g);
			p1 = (Spaceship) wrap(p1);
		}
		else
		{
			//Endgame
			JOptionPane.showMessageDialog(this, "GAME OVER. YOUR SCORE IS " + score);
			System.exit(0); //This should end the whole thing.
		}
		//If we have shot, it will draw an image.
		if(shot)
		{
			b1.drawImage(g);
			if(b1.getXPos() > this.getWidth() || b1.getXPos() < 0 || b1.getYPos() > this.getHeight() || b1.getYPos() < 0)
			{
				shot = false;
				b1 = null;
			}
			
		}
		for(int i = 0; i < asteroids.size(); i++)
		{
			
			//this will set the current object to the potential wrapped asteroid.
			asteroids.set(i,wrap(asteroids.get(i)));
					
			try {
				//A super dumb, overly complicated way of determining if things are hitting eachother. Also it doesn't work.
				if((p1.getXPos() + Objectable.box) >= asteroids.get(i).getXPos() && (p1.getXPos() + Objectable.box) <= (asteroids.get(i).getWidth() + asteroids.get(i).getXPos()))
				{
					System.out.println(p1);
					System.out.println(asteroids.get(i));
					System.out.println("x");
					if((p1.getYPos() + Objectable.box) >= asteroids.get(i).getYPos() && (p1.getYPos() + Objectable.box) <= (asteroids.get(i).getHeight() + asteroids.get(i).getYPos()))
					{
						System.out.println("hit");
						Spaceship.lives -=1;
						dead = true;
						asteroids.removeAll(asteroids); //This clears this array.
						break;
						
					}
						//So this is stupid long and I apologize. Also it doesn't work. very cool.
						if(((b1.getXPos()) >= asteroids.get(i).getXPos() && (b1.getXPos()) <= (asteroids.get(i).getXPos() + asteroids.get(i).getWidth())) 
						&& ((b1.getYPos()) >= asteroids.get(i).getYPos() && (b1.getYPos()) <= (asteroids.get(i).getHeight() + asteroids.get(i).getYPos())))
						{					
								System.out.println("bullet");
								asteroids.remove(i);
								score += 100;
								break; //Putting this in so you never have the loop break on you.				
						}
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			asteroids.get(i).drawImage(g);
		}
		
		//All asteroids are gone, either by you dying or them getting destroyed.
		if(asteroids.isEmpty())
		{
			//This will reset everything.
			if(!dead)
			{
				level++;
			}
			p1 = null;
			b1 = null;
			shot = false;
		}

		try
		{
		Thread.sleep(50);
		}
		catch(InterruptedException ie)
		{
			System.out.println("Interrupted.");
		}
		
		repaint();
		
	}
	
	private void createAsteroids(int i)
	{
		//So we need to devise a way to develop the proper amount of asteroids
		//
		int x = 0;
		int y = 0;
		int vx = 0;
		int vy = 0;
		
		//This determines that there is at least 3 and no more than 9 asteroids at all times.
		final int buffer = 60;
		if(i <= 2)
		{
			i = 3;
			
		}
		else if( i >10)
		{
			i = 9;
		}
		
		for(int j = 0; j < i; j++ )
		{
			
			//So bear with me. This is a complicated way to find which coordinate section we are in, and then choosing based off that. I could probably do this more concisely.
			switch(ayn.nextInt(4))
			{
			case 0:
				x = ayn.nextInt(HALF_W-buffer);
				y = ayn.nextInt(HALF_H-buffer);
				break;
			case 1:
				//So this will choose a random number from half the length minus the buffer. We will then add in the buffer back, and add the other half of the length.  That way we get the first quadrant of a cartisian plane.
				x = ayn.nextInt(HALF_W-buffer)+buffer+HALF_W;
				y = ayn.nextInt(HALF_H-buffer);
				break;
			case 2:
				x = ayn.nextInt(HALF_W-buffer)+buffer+HALF_W;
				y = ayn.nextInt(HALF_H-buffer) + buffer + HALF_H;
				break;
			case 3:

				x = ayn.nextInt(HALF_W-buffer);
				y = ayn.nextInt(HALF_H-buffer) + buffer + HALF_H;
				break;
			default:
				break;
				
			}
			//This is to determine the velocities randomly. 

			vx = ayn.nextInt(20)-10;
			vy = ayn.nextInt(20)-10;
			//This keeps the asteroids moving in a somewhat interesting fashion.
			if(vx == 0)
			{
				vx = 1;
			}
			if(vy == 0);
			{
				vy = 1;
			}
			
			asteroids.add(new Objectable(x, y, 30, 30, asteroidImg,vx, vy));
		}
	}
	
	//This will allow objects to wrap around the screen.
	private Objectable wrap(Objectable o)
	{
		//We check the positions and if it is more than half off the side of the map, then we move it.
		if(o.getXPos() > this.getWidth() - Objectable.box) 
		{
			o.setXPos(-1*Objectable.box);
		}
		else if(o.getXPos() < -1* Objectable.box)
		{
			o.setXPos(this.getWidth() - Objectable.box);
		}
		
		if(o.getYPos() > this.getHeight() - Objectable.box)
		{
			o.setYPos(-1*Objectable.box);
		}
		else if(o.getYPos() < -1*Objectable.box)
		{
			o.setYPos(this.getHeight() - Objectable.box);
		}
			return o;
		}
	
	//So we can use the spaceship object.
	public Spaceship getShip()
	{
		return p1;
	}
	
	//Makes a bullet.
	public void shoot()
	{
		b1 = new Bullet1((p1.getXPos() + Objectable.box), (p1.getYPos() + Objectable.box), p1.getRotation());
		shot = true;
	}
}
