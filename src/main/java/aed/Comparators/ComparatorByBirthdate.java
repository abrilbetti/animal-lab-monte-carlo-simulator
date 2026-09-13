package aed.Comparators;

import aed.Laboratory.Mouse;
import java.util.Comparator;

/**
 * Class that defines the comparator that we use to order the mice chronologically.
 * @author abrilbetti
 */
public class ComparatorByBirthdate implements Comparator<Mouse> {
    /**
     * Overridden method that defines the natural order to sort the mice chronologically, depending on their birthdays.
     * @param mouse1 the first object to be compared.
     * @param mouse2 the second object to be compared.
     * @return a negative integer, a call to the compareTo method of the Mouse class or a positive integer as the first argument's birthday (mouse1)
     * was before, on the same date or after the second argument's birthday (mouse2), respectively.
     */
    @Override
    public int compare(Mouse mouse1, Mouse mouse2) {
        int comparison = mouse1.getBirthDate().compareTo(mouse2.getBirthDate());
        //If the birthdays of the instances coincide, I call the method compare to of Mouse class in order to check for equality.
        if(comparison == 0){
            return mouse1.compareTo(mouse2);
        }
        else{
            //If the birthdays are not the same, I return the value of the comparison of the dates.
            return comparison;
        }
    }
}