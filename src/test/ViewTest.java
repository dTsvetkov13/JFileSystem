package test;

import java.awt.Rectangle;

import javax.swing.JFrame;

import models.File;
import models.FileSystemObject;
import models.Folder;
import views.Drawer;

public class ViewTest
{
	public static void main(String[] args)
	{	
		File file1 = new File("Parks.txt", 20);
		FileSystemObject[] files = {file1};
		Folder folder = new Folder("Green files", files);
		FileSystemObject[] data = {folder, folder, file1};
		Folder folder2 = new Folder("Folder2", data);
		
		Drawer.getInstance().init(new Rectangle(50, 50, 500, 500));
		Drawer.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Drawer.getInstance().addFSObject(folder2);
		Drawer.getInstance().addFSObject(file1);
		
		Drawer.getInstance().repaint();
	}
}
