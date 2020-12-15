package models;

public abstract class FileSystemObject
{
	private String name;
	
	public FileSystemObject(String nameValue)
	{
		this.setName(nameValue);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String nameValue)
	{
		if(nameValue != null && !nameValue.isEmpty())
		{
			this.name = nameValue;
		}
	}
	
	public abstract int getSize();
	
	public FileSystemObject()
	{
		this.name = "File";
	}
}
