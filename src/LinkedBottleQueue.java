
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
 * This class implements a linked queue storing objects of type Bottle.
 * Bottles are added and removed with respect to the First In First Out (FIFO) scheduling policy.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    /**
     * indicating the begin and end bottles in the linked list queue
     */
    private LinkedNode<Bottle> front;
    /**
     *indicating the begin and end bottles in the linked list queue
     */
    private LinkedNode<Bottle> back;
    /**
     *indicating the number of bottles in the queue
     */
    private int size;
    /**
     *defining the max number of bottles the queue can hold
     */
    private int capacity;

    /**
     * A private static inner class, Node, represents a node in the singly
     * linked list. It contains a Bottle object and a reference to the next
     * Node in the list.
     */

    private class LinkedListQueueIterator implements Iterator<Bottle> {
        /**
         * currentNode is a reference to the current node in the linked list during iteration.
         */
        private LinkedNode<Bottle> currentNode;
        /**
         * Constructor for LinkedListQueueIterator initializes the current node to the front of the queue.
         */

        public LinkedListQueueIterator() {
            currentNode = front;

            /**
             * hasNext method checks if there are more elements left to iterate in the queue.
             * @return true if there are more elements to iterate, false otherwise.
             */}

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }
        /**
         * next method retrieves the next Bottle in the queue and advances the iterator.
         * @return the next Bottle object in the queue.
         * @throws NoSuchElementException if there are no more elements in the queue.
         */

        @Override
        public Bottle next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the queue.");
            }
            Bottle bottle = currentNode.getData();
            currentNode = currentNode.getNext();
            return bottle;
        }
    }




    /**
     * Initializes the fields of this queue including its capacity. A newly created queue must be empty, meaning
     * that both its front and back are null and its size is zero
     *
     * @param capacity - Positive integer defining the max number of bottles the queue can hold
     * @throws IllegalArgumentException - when the capacity is not positive (meaning less or equal to zero)
     */
    public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer.");
        }
        this.capacity = capacity;
        this.front = null;
        this.back = null;
        this.size = 0;
    }


    /**
     * Returns a string representation of the queue from the front to its back with the string representation of each Bottle in a separate line. For instance,
     * SN:Filled/Empty:Capped/Open
     * ...
     * SN:Filled/Empty:Capped/Open
     * The string should not contain a newline character at the end.
     *
     * @return -String in expected format, empty string when queue is empty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Bottle bottle : this) {
            sb.append(bottle.toString());
            sb.append('\n');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // Remove the last newline character
        }
        return sb.toString();
    }


    /**
     * Returns the first bottle in the queue without removing it
     *
     * @return Top/ First bottle in the queue
     * @throws NoSuchElementException - When queue is empty
     */
    @Override
    public Bottle peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return front.getData();
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
        return size == capacity;

    }


    /**
     * Returns the number iof bottles in the queue
     *
     * @return size of the queue
     */


    @Override
    public int size() {
        return size;

    }


    /**
     * Removes and returns the first bottle in the queue
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException - when queue is empty
     */
    @Override
    public Bottle dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Bottle bottle = front.getData();
        front = front.getNext();
        size--;
        if (isEmpty()) {
            back = null;
        }
        return bottle;
    }


    /**
     * Add a bottle to the end of the queue
     *
     * @param bottle - bottle to add to the queue
     * @throws IllegalStateException - when queue is full
     *                               NullPointerException - when bottle to add is null
     */
    @Override
    public void enqueue(Bottle bottle) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        if (bottle == null) {
            throw new NullPointerException("Bottle to add cannot be null.");
        }
        LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            back.setNext(newNode);
            back = newNode;
        }
        size++;
    }


    /**
     * Returns an iterator for traversing the queue's items
     *
     * @return iterator to traverse the LinkedListQueue
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this);
    }


    /**
     * Returns a deep copy of this queue
     *
     * @return deep copy of this queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        LinkedBottleQueue copiedQueue = new LinkedBottleQueue(capacity);
        LinkedNode<Bottle> currentNode = front;
        while (currentNode != null) {
            copiedQueue.enqueue(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        return copiedQueue;
    }

}



