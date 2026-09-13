package aed.Comparators;

import aed.Laboratory.Mouse;
import java.util.Comparator;

/**
 * Class that implements the interface 'Comparator' in order to compare Mouse objects based on their weight.
 * @author abrilbetti
 */
public class ComparatorByWeight implements Comparator<Mouse>{
    /**
     * Overridden method that defines the natural order to sort the mice in increasing order of their weights in grams.
     * @param mouse1 the first object to be compared.
     * @param mouse2 the second object to be compared.
     * @return a negative integer, a call to the compareTo method of the Mouse class (which evaluates if the objects are equal)
     * or a positive integer as the first argument's weight (mouse1) is less, equal or greater than the second argument's weight (mouse2), respectively.
     */
    @Override
    public int compare(Mouse mouse1, Mouse mouse2) {
        int comparison = mouse1.getWeightGr() - mouse2.getWeightGr();
        if(comparison == 0){
            //If the weights of the instances are the same, I call the compare to method of Mouse in order to check equality
            return mouse1.compareTo(mouse2);
        }
        else{ //If the weights of the objects are different, then I return the value I got when comparing them.
            return comparison;
        }
    }
}