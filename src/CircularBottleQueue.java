
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Bottle Factory
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
 * This class models a circular-indexing array queue which stores elements of type Bottle
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    /**
     * An array to store the Bottle objects in the circular-indexing queue.
     */
    private Bottle[] bottles;
    /**
     *indicating the number of bottles in the queue
     */
    private int size;
    /**
     *indicting the earliest added bottle and recently added bottle respectively
     */
    private int front;
    /**
     *indicting the earliest added bottle and recently added bottle respectively
     */
    private int back;


    /**
     * Constructs a CircularBottleQueue object, initializing its data fields as follows:
     * the bottles oversize array to an empty array of Bottle objects whose length is the input capacity,
     * its size to zero, and
     * both its front and back to -1.
     *
     * @param capacity defining the number of bottles the queue can hold
     * @throws IllegalArgumentException when capacity is not positive
     */
    public CircularBottleQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer.");
        }
        this.bottles = new Bottle[capacity];
        this.size = 0;
        this.front = -1;
        this.back = -1;
    }


    /**
     * Returns an iterator to traverse the queue.
     *
     * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this);
    }


    /**
     * Add a bottle to the end of the queue
     *
     * @param bottle Bottle object to add to the queue
     * @throws IllegalStateException when queue is full
     * @Throws NullPointerException when bottle to add is null
     */
    @Override
    public void enqueue(Bottle bottle) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        if (bottle == null) {
            throw new NullPointerException("Bottle to add cannot be null.");
        }
        back = (back + 1) % bottles.length;
        bottles[back] = bottle;
        size++;

        if (front == -1) {
            front = back;
        }


    }


    /**
     * Removes and returns the first bottle in the queue
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException when queue is empty
     */
    @Override
    public Bottle dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Bottle bottle = bottles[front];
        bottles[front] = null;
        size--;

        if (isEmpty()) {
            front = -1;
            back = -1;
        } else {
            front = (front + 1) % bottles.length;
        }

        return bottle;
    }


    /**
     * Returns the first bottle in the queue without removing it
     *
     * @return Top/ First bottle in the queue
     */
    @Override
    public Bottle peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return bottles[front];
    }


    /**
     * Checks and returns true if the queue is empty
     *
     * @return boolean value
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Checks and returns true if the queue is full
     *
     * @return boolean value
     */
    @Override
    public boolean isFull() {
        return size == bottles.length;
    }


    /**
     * Returns the number of bottles in the queue
     *
     * @return size of the queue
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Returns a string representation of the queue from the front to its back with the string representation of each Bottle in a separate line. For instance,
     * SN:Filled/Empty:Capped/Open
     * ...
     * SN:Filled/Empty:Capped/Open
     * The string should not contain a newline character at the end.
     *
     * @return String in expected format , empty string when queue is empty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = front;
        for (int i = 0; i < size; i++) {
            sb.append(bottles[index].toString());
            sb.append('\n');
            index = (index + 1) % bottles.length;
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // Remove the last newline character
        }
        return sb.toString();
    }


    /**
     * Description copied from interface: QueueADT
     * Returns a deep copy of this queue.
     *
     * @return a deep copy of the queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        CircularBottleQueue copiedQueue = new CircularBottleQueue(bottles.length);
        int index = front;
        for (int i = 0; i < size; i++) {
            copiedQueue.enqueue(bottles[index]);
            index = (index + 1) % bottles.length;
        }
        return copiedQueue;
    }
}


