
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
 * This class models an iterator to iterate over a queue of Bottle objects.
 * When the queue is not empty, Bottle objects are iterated over from the front to the back of the queue.
 * No more Bottle objects are returned by this iterator when all the Bottle objects are traversed (returned).
 * This Iterator iterates over any queue which implements the QueueADT<Bottle> interface.
 * It uses the QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 */
public class BottleQueueIterator implements Iterator<Bottle> {
    /**
     * defines the queue of bottles to be iterated over
     */

    private QueueADT<Bottle> bottleQueue;


    /**
     * Initializes the instance fields.
     * The bottle queue of this iterator MUST be initialized to a deep copy of the queue passed as input.
     *
     * @param queue The queue to iterate over, should implement the QueueADT interface.
     * @throws IllegalArgumentException - when queue is null
     */
    public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
        if (queue == null) {
            throw new IllegalArgumentException("Queue cannot be null.");
        }
        this.bottleQueue = queue.copy();
    }


    /**
     * Returns true if the iteration is not yet exhausted, meaning at least one bottle is not iterated over.
     *
     * @return boolean value
     */
    @Override
    public boolean hasNext() {
        return !bottleQueue.isEmpty();
    }


    /**
     * Returns the next bottle in the iteration.
     *
     * @return Bottle The next bottle in the iteration
     * @throws NoSuchElementException - if there are no more elements in the iteration
     */
    @Override
    public Bottle next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the queue.");
        }
        return bottleQueue.dequeue();
    }
}


