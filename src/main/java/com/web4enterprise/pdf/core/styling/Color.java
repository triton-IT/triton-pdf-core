package com.web4enterprise.pdf.core.styling;

/**
 * Defines a color using 3 components (red, green and blue).
 * 
 * @author Régis Ramillien
 */
public class Color {
	/**
	 * The red component of the color from to 255.
	 */
	protected int red;
	/**
	 * The green component of the color from to 255.
	 */
	protected int green;
	/**
	 * The blue component of the color from to 255.
	 */
	protected int blue;

	/**
	 * The constant representing a black color.
	 */
	public static final Color BLACK = new Color(0, 0, 0);

	/**
	 * The constant representing a white color.
	 */
	public static final Color WHITE = new Color(255, 255, 255);
	
	/**
	 * Constructs a color from RGB components.
	 * 
	 * @param red The red component of the color from to 255.
	 * @param green The green component of the color from to 255.
	 * @param blue The blue component of the color from to 255.
	 */
	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	/**
	 * Get the red component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @return The red component.
	 */
	public int getRed() {
		return red;
	}
	
	/**
	 * Set the red component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @param red The red component.
	 */
	public void setRed(int red) {
		this.red = red;
	}
	
	/**
	 * Get the green component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @return The green component.
	 */
	public int getGreen() {
		return green;
	}
	
	/**
	 * Set the green component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @param green The green component.
	 */
	public void setGreen(int green) {
		this.green = green;
	}
	
	/**
	 * Get the blue component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @return The blue component.
	 */
	public int getBlue() {
		return blue;
	}
	
	/**
	 * Set the blue component of the color.
	 * Component is comprised between 0 and 255.
	 * 
	 * @param blue The blue component.
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}
}
