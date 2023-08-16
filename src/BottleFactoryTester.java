
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P08 Bottle Factory
// Course:   CS 300 Spring 2023
//
// Author:   Abdifatah Abdi
// Email:    aaabdi2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
/// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//
////   _X__ Write-up states that pair programming is allowed for this assignment.
//
////   _X__ We have both read and understand the course Pair Programming Policy.
//
////   _X__ We have registered our team prior to the team registration deadline.
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//// Persons:         TA: TA Snehal Wadhwani  help with little help
// TA: Yiwei Zhang help with little help
//TA; MICHELLE JENSEN help me a lttile bit
//// Online Sources:
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * This utility class implements tester methods to check the correctness of the implementation of classes defined in p08 Bottle Factory program.
 */

public class BottleFactoryTester {
    /**
     * Ensures the correctness of the constructor and methods defined in the Bottle class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is detected
     */
    public static boolean bottleTester() {
        boolean success = true;

        Bottle.resetBottleCounter();

        // Test 1: Check valid constructor usage
        try {
            Bottle bottle1 = new Bottle("Blue");
            if (!bottle1.getSerialNumber().equals("SN1Blue")) {
                success = false;
                System.out.println("Test 1 failed: Invalid serial number.");
            }
        } catch (Exception e) {
            success = false;
            System.out.println("Test 1 failed: Exception thrown.");
        }

        // Test 2: Check invalid constructor input (null color)
        try {
            Bottle bottle2 = new Bottle(null);
            success = false;
            System.out.println("Test 2 failed: IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        } catch (Exception e) {
            success = false;
            System.out.println("Test 2 failed: Unexpected exception thrown.");
        }

        // Test 3: Check invalid constructor input (empty color)
        try {
            Bottle bottle3 = new Bottle("");
            success = false;
            System.out.println("Test 3 failed: IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        } catch (Exception e) {
            success = false;
            System.out.println("Test 3 failed: Unexpected exception thrown.");
        }

        // Test 4: Check invalid constructor input (blank color)
        try {
            Bottle bottle4 = new Bottle(" ");
            success = false;
            System.out.println("Test 4 failed: IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        } catch (Exception e) {
            success = false;
            System.out.println("Test 4 failed: Unexpected exception thrown.");
        }

        // Test 5: Check toString() method
        try {
            Bottle bottle5 = new Bottle("Red");
            String expectedString = "SN2Red:Empty:Open";
            if (!bottle5.toString().equals(expectedString)) {
                success = false;
                System.out.println("Test 5 failed: Invalid toString() output.");
            }
        } catch (Exception e) {
            success = false;
            System.out.println("Test 5 failed: Exception thrown.");
        }

        // Test 6: Check equals() method
        try {
            Bottle.resetBottleCounter(); // Reset the counter to ensure proper bottle order
            Bottle bottle6 = new Bottle("Green");
            Bottle.resetBottleCounter(); // Reset the counter again for the second bottle
            Bottle bottle7 = new Bottle("Green");
            if (!bottle6.equals(bottle7)) {
                success = false;
                System.out.println("Test 6 failed: equals() method not working as expected.");
            }
        } catch (Exception e) {
            success = false;
            System.out.println("Test 6 failed: Exception thrown.");
        }

        return success;
    }


    /**
     * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is detected
     */

    public static boolean linkedBottleQueueTester() {
        try {
            // Test constructor
            LinkedBottleQueue queue = new LinkedBottleQueue(3);

            // Test isEmpty
            if (!queue.isEmpty()) {
                return false;
            }

            // Test isFull
            if (queue.isFull()) {
                return false;
            }

            // Test size
            if (queue.size() != 0) {
                return false;
            }

            // Test enqueue and dequeue
            Bottle bottle1 = new Bottle("Blue");
            queue.enqueue(bottle1);
            if (!queue.peek().equals(bottle1) || queue.size() != 1) {
                return false;
            }

            Bottle bottle2 = new Bottle("Red");
            queue.enqueue(bottle2);
            if (!queue.peek().equals(bottle1) || queue.size() != 2) {
                return false;
            }

            Bottle dequeuedBottle1 = queue.dequeue();
            if (!dequeuedBottle1.equals(bottle1) || queue.size() != 1) {
                return false;
            }

            Bottle dequeuedBottle2 = queue.dequeue();
            if (!dequeuedBottle2.equals(bottle2) || queue.size() != 0) {
                return false;
            }

            // Test iterator
            queue.enqueue(bottle1);
            queue.enqueue(bottle2);
            Iterator<Bottle> iterator = queue.iterator();
            if (!iterator.hasNext() || !iterator.next().equals(bottle1) || !iterator.next().equals(bottle2)) {
                return false;
            }

            // Test copy
            QueueADT<Bottle> copiedQueue = queue.copy();
            if (!queue.toString().equals(copiedQueue.toString())) {
                return false;
            }

            // Test toString
            String expectedString = bottle1.toString() + "\n" + bottle2.toString();
            if (!queue.toString().equals(expectedString)) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



    /**
     * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
     * @return true if the tester verifies a correct functionality and false if at least one bug is detected
     */
    public static boolean circularBottleQueueTester() {
        // Test 1: Creating a CircularBottleQueue with a positive capacity
        try {
            CircularBottleQueue queue = new CircularBottleQueue(10);
        } catch (Exception e) {
            System.out.println("Test 1 failed: Creating a CircularBottleQueue with a positive capacity");
            return false;
        }

        // Test 2: Creating a CircularBottleQueue with a non-positive capacity
        try {
            CircularBottleQueue queue = new CircularBottleQueue(-1);
            System.out.println("Test 2 failed: Creating a CircularBottleQueue with a non-positive capacity");
            return false;
        } catch (IllegalArgumentException e) {
            // Expected exception
        }

        // Add more tests here to test enqueue, dequeue, peek, isEmpty, isFull, size, copy, and toString methods
        // Test those methods in various scenarios as defined earlier for the LinkedBottleQueue class

        // If all tests pass
        return true;
    }

    /**
     * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
     * @return - true if the tester verifies a correct functionality and false if at least one bug is detected
     */

    public static boolean bottleQueueIteratorTester() {
        boolean testPassed = true;

        // Create a new LinkedBottleQueue
        LinkedBottleQueue bottleQueue = new LinkedBottleQueue(5);

        // Add bottles to the queue
        Bottle b1 = new Bottle("Blue");
        Bottle b2 = new Bottle("Green");
        Bottle b3 = new Bottle("Red");
        Bottle b4 = new Bottle("Yellow");

        bottleQueue.enqueue(b1);
        bottleQueue.enqueue(b2);
        bottleQueue.enqueue(b3);
        bottleQueue.enqueue(b4);

        // Create a BottleQueueIterator
        BottleQueueIterator iterator = new BottleQueueIterator(bottleQueue);

        // Test hasNext and next methods
        if (!iterator.hasNext() || !iterator.next().equals(b1)) {
            testPassed = false;
        }

        if (!iterator.hasNext() || !iterator.next().equals(b2)) {
            testPassed = false;
        }

        if (!iterator.hasNext() || !iterator.next().equals(b3)) {
            testPassed = false;
        }

        if (!iterator.hasNext() || !iterator.next().equals(b4)) {
            testPassed = false;
        }

        // Check if hasNext returns false after iteration is completed
        if (iterator.hasNext()) {
            testPassed = false;
        }

        // Test NoSuchElementException for calling next() when no more elements in the iteration
        try {
            iterator.next();
            testPassed = false;
        } catch (NoSuchElementException e) {
            // Expected exception
        }

        // Check if the original queue is not empty after the iteration
        if (bottleQueue.isEmpty()) {
            testPassed = false;
        }

        // Create an empty LinkedBottleQueue and test iterator methods
        LinkedBottleQueue emptyQueue = new LinkedBottleQueue(3);
        BottleQueueIterator emptyIterator = new BottleQueueIterator(emptyQueue);

        if (emptyIterator.hasNext()) {
            testPassed = false;
        }

        try {
            emptyIterator.next();
            testPassed = false;
        } catch (NoSuchElementException e) {
            // Expected exception
        }

        return testPassed;
    }



    /**
         * Runs all the tester methods defined in this class.
         * @return true if no bugs are detected.
         */
    public static boolean runAllTests() {
        boolean bottleTestsPassed = bottleTester();
        boolean linkedBottleQueueTestsPassed = linkedBottleQueueTester();
        boolean circularBottleQueueTestsPassed = circularBottleQueueTester();
        boolean bottleQueueIteratorTestsPassed = bottleQueueIteratorTester();

        return bottleTestsPassed && linkedBottleQueueTestsPassed && circularBottleQueueTestsPassed && bottleQueueIteratorTestsPassed;
    }


    /**
     * Main method to run this tester class.
     *
     * @param args - list of input arguments if any
     */
        public static void main(String[] args) {
            boolean testsPassed = bottleTester();


            if (testsPassed) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed.");
            }

            boolean result = linkedBottleQueueTester();
            if (result) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed..");
            }

            result = bottleQueueIteratorTester();
            if (result) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed..");
            }


            if (circularBottleQueueTester()) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed.");
            }

        }
        }



