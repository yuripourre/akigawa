package akigawa.model;

public class Profile {
	
	private String name = "";
	
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	public Profile(){
		super();
		this.name = "Ninja";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
}
