package views;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import enums.NodeConnectionType;
import models.File;
import models.FileSystemObject;
import models.Folder;

public class Drawer extends JFrame
{
	public static final int RECTANGLE_WIDTH = 100;
	public static final int RECTANGLE_HEIGHT = 100;
	
	private static Drawer instance;
	private Rectangle lastDrawnRectangle;
	private ArrayList<FileSystemObject> fSObjects;
	private static int objectsCount;
	
	private Drawer()
	{
		lastDrawnRectangle = new Rectangle();
	}
	
	public static Drawer getInstance()
	{
		if(instance == null)
		{
			instance = new Drawer();
		}
		
		return instance;
	}
	
	public void init(Rectangle windowBounds)
	{
		this.setBounds(windowBounds);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		this.fSObjects = new ArrayList<FileSystemObject>();
	}
	
	public void addFSObject(FileSystemObject fSObject)
	{
		this.fSObjects.add(fSObject);
	}
	
	public Rectangle createNextFSObjectRectangle()
	{
		Rectangle rect = new Rectangle(this.lastDrawnRectangle.x,
									   this.lastDrawnRectangle.y + this.lastDrawnRectangle.height,
									   RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		
		this.lastDrawnRectangle = rect;
		return rect;
	}
	
	public void drawFSObjectView(FileSystemObjectView fSObjectView)
	{
		this.add(fSObjectView);
	}
	
	public void addJComponent(JComponent comp)
	{
		if(comp != null)
		{
			this.add(comp);	
		}
	}
	
	public void paintComponent(Graphics graphics)
	{
		this.lastDrawnRectangle = new Rectangle(0, 0, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		
		FileSystemObject[] fSObjectsArray = new FileSystemObject[this.fSObjects.size()];
		
		for(int i = 0; i < this.fSObjects.size(); i++)
		{
			fSObjectsArray[i] = this.fSObjects.get(i);
		}
		
		Folder folder = new Folder("root", fSObjectsArray);
		
		drawFolder(folder, graphics, lastDrawnRectangle);
	}
	
	public void repaint()
	{
		if(this.getGraphics() != null)
		{
			System.out.println("Draw");
			this.paintComponent(this.getGraphics());
		}
	}
	
	private void drawFolder(Folder folder, Graphics graphics, Rectangle rect)
	{
		Rectangle tempRect, newRect;
		
		this.add(new FolderView(rect, folder));
		Folder currentFolder;
		int lastObjectChildrenCount = 0;
		boolean areThereFolders = false;
		
		for(int i = 0; i < folder.getIndex(); i++)
		{
			tempRect = this.createNextFSObjectRectangle();
			tempRect.x = rect.x;
			
			if(i + 1 == folder.getIndex())
			{
				this.addJComponent(new NodeConnectionView(tempRect, NodeConnectionType.ParentChild));
			}
			else
			{
				this.addJComponent(new NodeConnectionView(tempRect, NodeConnectionType.ParentChildren));
			}
				
			newRect = new Rectangle(tempRect.x, tempRect.y,
								    tempRect.width, tempRect.height);
				
			newRect.x += newRect.width;
			
			if(folder.getFiles()[i].getClass().equals(Folder.class))
			{
				currentFolder = (Folder) folder.getFiles()[i]; 
				drawFolder(currentFolder, graphics, newRect);
				objectsCount += currentFolder.getIndex();
				
				if(i + 1 == folder.getIndex())
				{
					lastObjectChildrenCount = currentFolder.getIndex();
				}
				else
				{
					areThereFolders = true;
				}
			}
			else if(folder.getFiles()[i].getClass().equals(File.class))
			{
				this.add(new FileView(newRect, (File) folder.getFiles()[i]));
			}
		}
		
		System.out.println(folder.getName() + " : " + objectsCount);
		
		if(areThereFolders)
		{
			for(int i = 0; i < objectsCount - lastObjectChildrenCount + folder.getIndex() - 1; i++)
			{
				System.out.println("i: " + i);
				System.out.println(rect);
				this.addJComponent(new NodeConnectionView(new Rectangle(rect.x, rect.y + (i + 1) * rect.height,
																		rect.width, rect.height),
														  NodeConnectionType.Line));
			}	
		}
	}
}
