/**
 * Car specification interface for CS1501 Project 3
 * @author	Dr. Farnan
 */
package cs1501_p3;

interface Car_Inter {
	
	/**
	 * Getter for the VIN attribute
	 *
	 * @return 	String The VIN
	 */
	public String getVIN();

	/**
	 * Getter for the make attribute
	 *
	 * @return 	String The make
	 */
	public String getMake();

	/**
	 * Getter for the model attribute
	 *
	 * @return 	String The model
	 */
	public String getModel();

	/**
	 * Getter for the price attribute
	 *
	 * @return 	String The price
	 */
	public int getPrice();

	/**
	 * Getter for the mileage attribute
	 *
	 * @return 	String The mileage
	 */
	public int getMileage();

	/**
	 * Getter for the color attribute
	 *
	 * @return 	String The color
	 */
	public String getColor();

	/**
	 * Setter for the price attribute
	 *
	 * @param 	newPrice The new Price
	 */
	public void setPrice(int newPrice);

	/**
	 * Setter for the mileage attribute
	 *
	 * @param 	newMileage The new Mileage
	 */
	public void setMileage(int newMileage);

	/**
	 * Setter for the color attribute
	 *
	 * @param 	newColor The new color
	 */
	public void setColor(String newColor);
}

