package aed.Families;

import aed.Exceptions.FamilyExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Gender;
import aed.Laboratory.Mouse;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

public abstract class Family implements Comparable<Family> {
    protected Mouse fatherMouse;
    protected TreeSet<Mouse> mothersMice;
    protected Mouse biologicalFather;
    private final int familyID;
    private static int numFamily = 0;

    /**
     * Constructor of the class Family, in which an instance of Mouse and a set of Mouse objects are set as the father and the mothers
     * of the family.
     * @param _fatherMouse object of Mouse whose gender must be 'MALE' in order to be established as the father of the family.
     * @param _mothersMice set of Mouse objects whose genders must be 'FEMALE' in order to be established as the mother/mothers of the family.
     * @throws FamilyExceptions any of the parameters have a gender different to the one they should have or if the tree set of mothers is empty.
     */
    public Family(Mouse _fatherMouse, TreeSet<Mouse> _mothersMice) throws FamilyExceptions{
        if(_mothersMice.isEmpty()){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.NO_MOTHER_ASSIGNED);
        }
        if(!checkMothersGender(_mothersMice)){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.INVALID_GENDER_MOTHER);
        }
        if(_fatherMouse.getGender() != Gender.MALE){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.INVALID_GENDER_FATHER);
        }
        familyID = generateFamilyID();
        this.fatherMouse = _fatherMouse;
        this.mothersMice = _mothersMice;
    }

    /**
     * Getter for the attribute that contains the father of the family (a male mouse).
     * @return the male mouse.
     */
    public Mouse getFatherMouse() {
        return fatherMouse;
    }
    /**
     * Getter for the attribute that contains the biological father of the family (a male mouse).
     * @return the male mouse.
     */
    public Mouse getBiologicalFather() {
        return biologicalFather;
    }

    /**
     * Getter for the tree set that contains the mothers of the family.
     * @return the tree set of mothers.
     */
    public TreeSet<Mouse> getMothersMice() {
        return mothersMice;
    }

    /**
     * Setter for the biological father that receives a mouse as parameter and sets it as the biological father of the family.
     * @param biologicalFather mouse we want to set as the biological father.
     */
    public void setBiologicalFather(Mouse biologicalFather) {
        this.biologicalFather = biologicalFather;
    }

    /**
     * Method that simulates the reproduction of the family.
     * @return a tree set that contains the offspring of the family.
     */
    public abstract TreeSet<Mouse> reproduce(int daysPassed);

    /**
     * Method that computes the amount of offspring a family will produce.
     * @return an integer that represents how many baby mice will be born in the family.
     */
    protected abstract int amountOfBabyMice();

    /**
     * Method that creates a random number and, based on the value of this number, establishes what chromosome will the baby mouse inherit from the parent "mouse".
     * @return the inherited chromosome.
     */
    protected Chromosome inheritChromosome(Mouse mouse){
        Random rand = new Random();
        int randNum = rand.nextInt(100);

        if(randNum < 50){
            //The baby mouse inherits the parent's first chromosome.
            return mouse.getChromosome1();
        } else{
            //The baby mouse inherits the parent's second chromosome.
            return mouse.getChromosome2();
        }
    }

    /**
     * Method that receives an integer by parameter and converts the tree set into an array in order to have direct access to the elements that are contained in the tree set
     * (we are not accessing them through the tree set as it is not possible, which is why we need to convert the tree set into an array)
     * @param position integer that stores the position in which the needed mouse is stored in the tree set of mothers.
     * @return the mouse stored in the "position" position of the array.
     */
    protected Mouse retrieveMouseFromMothersSet(int position){
        if(mothersMice.size() > position) {
            Mouse[] mothers = mothersMice.toArray(new Mouse[mothersMice.size()]);
            return mothers[position];
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Method that checks that the gender of all the mice in the tree set is female. If not, it returns false.
     * @return true if all the mice in the tree set are females; return false if any of the mice in the tree set is a male.
     */
    private boolean checkMothersGender(TreeSet<Mouse> mothers){
        /*I create an iterator instance in order to access the elements of the TreeSet (because TreeSets implement the Set interface,
        which does not provide a direct method to access elements)
         */
        Iterator<Mouse> iterator = mothers.iterator();
        //While the iterator has an element in the next position, I keep entering the 'for' and checking the mouse's gender.
        while(iterator.hasNext()) {
            for (int i = 0; i < mothers.size(); i++) {
                Mouse mother = iterator.next();
                if(mother.getGender() == Gender.MALE){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Static method that increments by one the variable that stores the amount of families that have been created, in order to initialise the ID of the family in
     * the constructor of Family.
     * @return an integer incremented by one, which represents how many families have been created.
     */
    private static int generateFamilyID(){
        numFamily++;
        return numFamily;
    }

    /**
     * Method that receives a mouse and checks whether the mouse received as parameter is more than 2.5 years old. If it is, it returns false (the mouse should die)
     * or returns true otherwise (the mouse can continue living)
     * @param mouse Mouse object whose birthday we want to check in order to determine whether it has to die or not.
     * @return true if the mouse can continue living or false if it is too old and needs to die.
     */
    public boolean shouldBeAlive(Mouse mouse, int daysPassed){
        if(mouse != null) {
            LocalDate birthday = mouse.getBirthDate();
            //LocalDate eldestDate = birthday.minusDays((long) (2.5 * 365));
            LocalDate simulatedDate = LocalDate.now().plusDays(daysPassed);
            LocalDate eldestDate = simulatedDate.minusDays((long) (2.5 * 365));

            if(birthday.isBefore(eldestDate)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Overridden equals method that establishes the criteria under which two families are considered to be equals (when their ID of family coincide).
     * @param o object we wish to check for equality with the object from which the method is called.
     * @return true if they are equal or false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family family)) return false;
        return familyID == family.familyID;
    }
    /**
     * Overridden hashCode method that generates a hash code for every family depending on their family IDs.
     * @return the hash code of the family with family ID "familyID"
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(familyID);
    }
    /** Overridden compareTo method that compares two objects of the class Family and returns a 0 if they are equal or a positive or negative number when they are different.
     * The method is final in order to prevent the child classes of Family to override it.
     * @param family instance of Family class that we wish to compare
     * @return 0 if the objects, according to the equals method, are equal or the difference of the two reference codes of the mice if they are not equal.
     */
    @Override
    public final int compareTo(Family family) {
        if(this.equals(family)){
            return 0;
        }
        else{
            return this.familyID - family.familyID;
        }
    }
}