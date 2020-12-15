package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import models.FileSystemObject;

public class FileSystemObjectView extends JComponent
{	
	public static final Color TEXT_COLOR = Color.black;
	private FileSystemObject fsObject;
	
	public FileSystemObjectView(Rectangle rect, FileSystemObject fileSystemObject)
	{
		this.fsObject = fileSystemObject;
		this.setBounds(rect);
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.drawRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
		
		String fsObjectInfo = String.format("%s (%d bytes)",
				this.fsObject.getName(), this.fsObject.getSize());
		
		g.setColor(TEXT_COLOR);
		g.drawString(fsObjectInfo, 10, 30);
	}
	
	public FileSystemObject getFSObject()
	{
		return this.fsObject;
	}
}
