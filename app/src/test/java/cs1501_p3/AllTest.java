/**
 * Basic tests for CS1501 Project 3
 * @author	Dr. Farnan
 */
package cs1501_p3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;
import java.util.HashMap;

import static java.time.Duration.ofSeconds;

class Autograde {
	final int DEFAULT_TIMEOUT = 10;
	CarsPQ cpq;

	@BeforeEach
	void init_cpq() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			cpq = new CarsPQ("build/resources/test/cars.txt");
		});		
	}

	@Test
	@DisplayName("Add/Get")
	void add_get() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			assertEquals("Red", cpq.get("PUAF85WU5R6L6H1P9").getColor());
			assertEquals("Green", cpq.get("X1U2PEJSC361L10MZ").getColor());
			assertEquals("Yellow", cpq.get("16Z2DPEHSUK5KCMEH").getColor());
		});
	}

	@Test
	@DisplayName("Update")
	void update() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			String vin = "1Y5NWYGLY5F4PX4HH";
			String newColor = "White";
			cpq.updateColor(vin, newColor);
			assertEquals(newColor, cpq.get(vin).getColor());
		});
	}

	@Test
	@DisplayName("Remove")
	void remove() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			String vin = "X1U2PEJSC361L10MZ";
			cpq.get(vin);
			cpq.remove(vin);
			assertThrows(NoSuchElementException.class, () -> cpq.get(vin));
		});
	}

	@Test
	@DisplayName("Get from all")
	void get_all() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			Car c = cpq.getLowPrice();
			boolean r = c.getVIN().equals("UTJYU67091B71NGZ3")
				|| c.getVIN().equals("RAMM7ZJBSFZ0HRTTN")
				|| c.getVIN().equals("SY719WJ4MMYVN0XNG");
			assertTrue(r);

			c = cpq.getLowMileage();
			r = c.getVIN().equals("PUAF85WU5R6L6H1P9")
				|| c.getVIN().equals("GNX5TS04SM5V5EXP8");
			assertTrue(r);
		});
	}

	@Test
	@DisplayName("Get for specific make/model")
	void get_make_model() {
		assertTimeoutPreemptively(ofSeconds(DEFAULT_TIMEOUT), () -> {
			Car c = cpq.getLowPrice("Ford", "Escort");
			assertEquals("8BSM1K0A6GXY2CHD7", c.getVIN());

			c = cpq.getLowMileage("Hyundai", "Elantra");
			assertEquals("GNX5TS04SM5V5EXP8", c.getVIN());
		});
	}
	
	
}
