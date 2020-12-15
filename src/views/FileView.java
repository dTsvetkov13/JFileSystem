package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import models.FileSystemObject;

public class FileView extends FileSystemObjectView
{
	public static final Color FILE_COLOR = Color.black;
	
	public FileView(Rectangle rect, FileSystemObject fileSystemObject)
	{
		super(rect, fileSystemObject);
	}
	
	public void paintComponent(Graphics graphics)
	{
		graphics.setColor(FILE_COLOR);
		super.paintComponent(graphics);
	}
}
