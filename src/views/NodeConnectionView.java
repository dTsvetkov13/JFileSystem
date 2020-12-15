package views;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

import enums.NodeConnectionType;

public class NodeConnectionView extends JComponent
{
	private NodeConnectionType type;
	
	public NodeConnectionView(Rectangle rect, NodeConnectionType type)
	{
		this.type = type;
		
		if(rect != null)
		{
			this.setBounds(rect);
		}
	}
	
	public void paintComponent(Graphics graphics)
	{	
		switch(type)
		{
			case ParentChild: 
			{
				graphics.drawLine(this.getWidth() / 2, 0,
						  		  this.getWidth() / 2, this.getHeight() / 2);
				graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2,
				  		  		  this.getWidth(), this.getHeight() / 2);
				break;
			}
			case ParentChildren:
			{
				graphics.drawLine(this.getWidth() / 2, 0,
								  this.getWidth() / 2, this.getHeight());
				graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2,
				  		  		  this.getWidth(), this.getHeight() / 2);
				break;
			}
			case Line:
				System.out.println("Line");
				graphics.drawLine(this.getWidth() / 2, 0,
								  this.getWidth() / 2, this.getHeight());
				break;
		}
	}
	
	public void repaint()
	{
		if(this.getGraphics() != null)
		{
			this.paintComponent(this.getGraphics());
		}
	}
}
