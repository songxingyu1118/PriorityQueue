/**
 * A driver for CS1501 Project 3
 * @author	Dr. Farnan
 */
package cs1501_p3;

public class App {
	public static void main(String[] args) {
		CarsPQ cpq = new CarsPQ("build/resources/test/cars.txt");
		Car c = new Car("5", "Ford", "Fiesta", 20, 2, "White");
		cpq.add(c);
		cpq.remove("5");
		cpq.remove("PUAF85WU5R6L6H1P9");
		cpq.remove("UTJYU67091B71NGZ3");
		cpq.remove("SY719WJ4MMYVN0XNG");
		cpq.updateMileage("16Z2DPEHSUK5KCMEH", 23);
		cpq.updatePrice("GNX5TS04SM5V5EXP8", 10);
		cpq.updateMileage("RAMM7ZJBSFZ0HRTTN", 5);
		cpq.updateMileage("RAMM7ZJBSFZ0HRTTN", 800);
		System.out.println("The lowest price now is " + cpq.getLowPrice().getPrice());
		System.out.println("The lowest mileage now is " + cpq.getLowMileage().getMileage());
	}
}
