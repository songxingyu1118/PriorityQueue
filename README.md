[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-f4981d0f882b2a3f0472912d15f9806d57e124e0fc890972558857b51b24a6f9.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10498517)
# CS 1501 Project 3

## Goal:
To explore an advanced application of priority queues in order to gain a deeper
understanding of the data structure.


## High-level description:
You will be writing a basic application to help a user select a car to buy.
You should write a PQ-based data structure that stores objects according to the
relative priorities of two of their attributes, making it efficient to retrieve
objects with the minimum value of either attribute. Your data structure should
further be indexable to allow for efficient updates of entered items. You will
want users to be able to enter details about cars that they are considering
buying. The user should then be able to efficiently retrieve the car with the
lowest mileage or lowest price.  These retrievals should be possible on the set
of all entered cars or on the set of all cars of a specific make and model
(e.g., "lowest price Ford Fiesta", "lowest mileage Cadillac Escalade").


## Specifications:
1. First you must create a class to store data about cars to buy. You must name
  this class `Car` and store it in `./app/src/main/java/cs1501_p3/Car.java`.
  Your `Car` class must implement the interface `Car_Inter` as defined in
  `./app/src/main/java/cs1501_p3/Car_Inter.java`. Specifically, this class
  must track the following information (and your `Car` constructor should
  accept values for these items in this order):

	* A unique VIN number (17 character string of numbers and capital letters
	  (but no I (i), O (o), or Q (q) to avoid confusion with numerals 1 and
	  0)
	* The car's make (e.g., Ford, Toyota, Honda)
	* The car's model (e.g., Fiesta, Camry, Civic)
	* The price to purchase (in whole dollars)
	* The mileage of the car (in whole miles)
	* The color of the car

1. You must then write a data structure based around the use of heaps with
  indirection (making them indexable) to track the lowest cost or mileage car
  under consideration.  You must name this class `CarsPQ` and store it in
  `./app/src/main/java/cs1501_p3/CarsPQ.java`.  This must implement the
  interface `CarsPQ_Inter` as defined in
  `./app/src/main/java/cs1501_p3/CarsPQ_Inter.java`, specifically all of the
  following methods (for descriptions of what these methods should do, refer to
  the JavaDoc comments in `CarsPQ_Inter.java`):
	* `add(Car c)`
	* `get(String vin)`
	* `updatePrice(String vin, int newPrice)`
	* `updateMileage(String vin, int newMileage)`
	* `updateColor(String vin, String newColor)`
	* `remove(String vin)`
	* `getPrice()`
	* `getPrice(String make, String model)`
	* `getMileage()`
	* `getMileage(String make, String model)`

1. To aid in the testing of your application, you will find an example file
  with some test data in `./app/src/test/resources/cars.txt`. Your `CarsPQ`
  class should include a constructor that takes in a single (String) file name
  to initialize the list of cars under consideration from that file.  You do
  not need to write an updated version of the contents of your `CarsPQ` data
  sturcture back to the file at any point.

1. Operations on either attribute (e.g., retrieve minimum price, retrieve
  minimum mileage) should have a O(lg n) runtime (both for all cars and for a
  specific make and model). Updates and removals should also have a O(lg n)
  runtime. Take care in selecting your approach to the indirection data
  structure to account for the types of keys you will need to store and the
  type and number operations that you will need to perform on them.

1. Your solution must support an arbitrary number of entries, and must resize
  to be arbitrarily large. Resize operations can take O(n) time.


## Submission Guidelines:
* **DO NOT** add the `./app/build/` diectory to your repository.
	* Leave the `./app/build.gradle` file there, however

* Be sure to remember to push the latest copy of your code back to your GitHub
	repository before submitting. To submit, log into GradeScope from Canvas and
	have GradeScope pull your repository from GitHub.


## Additional Notes/Hints:
* You are free to use code provided by the book authors in implementing your
  solution. It is up to you to decide if it would be easier to modify the
  provided code to meet the requirements of this project or if it would be
  easier to start with a clean slate with all of your own code. You cannot use
  any JCL classes (with the following exceptions: file I/O, and using ArrayList
  as needed by the provided interfaces).


## Grading Rubric:
| Feature | Points
| ------- | ------:
| `add`/`get` work properly | 10
| `updatePrice`/`updateMileage`/`updateColor` work properly | 15
| `remove` works properly | 15
| Retrieval for all cars works properly (by price or mileage) | 20
| Retrieval for a given make/model works properly (by price or mileage) | 20
| Operations on either attribute are efficient due to heap-backed data structure | 15
| Proper assignment submission | 5
