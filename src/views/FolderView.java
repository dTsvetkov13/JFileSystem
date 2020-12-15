package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JPanel;

import models.File;
import models.FileSystemObject;
import models.Folder;

public class FolderView extends FileSystemObjectView
{
	public static final Color FOLDER_COLOR = Color.orange;
	
	public FolderView(Rectangle rect, Folder folder)
	{
		super(rect, folder);
	}
	
	public void paintComponent(Graphics graphics)
	{
		graphics.setColor(FOLDER_COLOR);
		super.paintComponent(graphics);
	}
}
