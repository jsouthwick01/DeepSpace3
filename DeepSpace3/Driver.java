import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
public class Driver extends JFrame {

public Driver()
{
	
	setResizable(false);
	getContentPane().setLayout(null);
	
	DrawLayer panel = new DrawLayer();
	
	addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed");
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				System.out.println("Left");
				panel.getShip().setRotation(-1);
				if(!panel.getShip().isVZero())
				{
					panel.getShip().setV(false);
				}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				panel.getShip().setRotation(1);
				System.out.println("Right");
				if(!panel.getShip().isVZero())
				{
					panel.getShip().setV(false);
				}
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				System.out.println("Forward");
				panel.getShip().setV(false);
				break;
			case KeyEvent.VK_SPACE:
				//Shoot method
				panel.shoot();
				break;
			default:
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W))
			{
				panel.getShip().setV(true);
			}
		}
	});
	panel.setBackground(Color.BLACK);
	panel.setBounds(0, 0, 750, 750);
	getContentPane().add(panel);
	panel.setLayout(null);

}


public static void main(String[] args) {
	Driver d = new Driver();
	//m.getContentPane().add(m.bg);// added code
	d.setSize(750, 750);
	d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	d.setVisible(true);
}
}
