package aed.Exceptions;

/**
 *  Class that contains personalised exceptions in order to handle with unexpected scenarios regarding the use of the instances
 *  of the "Family" class and its daughter classes.
 * @author abrilbetti
 */
public class FamilyExceptions extends RuntimeException {
    /**
     * Enumerate that contains the different types of exceptions we can find when dealing with instances of the class Family or its daughter classes.
     */
    public enum ErrorTypeFamily{
        INVALID_GENDER_FATHER, INVALID_GENDER_MOTHER, NO_FERTILE_MALE_ASSIGNED, MUTATION_MALE_LACKS_POLYGAMY, MUTATION_MALE_LACKS_STERILITY,
        MUTATION_MALE_HAS_POLYGAMY, MUTATION_MALE_HAS_STERILITY, NO_MOTHER_ASSIGNED
    }

    public ErrorTypeFamily errorTypeFamily;

    /**
     * Constructor that initialises the only attribute of this class, "errorTypeFamily" with a value which is received as parameter.
     * @param errorTypeFamily contains the type of exception, which is determined when creating an instance of 'Family' and encountering diverse unexpected situations.
     */
    public FamilyExceptions(ErrorTypeFamily errorTypeFamily) {
        this.errorTypeFamily = errorTypeFamily;
    }
    /**
     * Getter for the attribute of the "FamilyExceptions" class which makes the attribute of this class accessible.
     * @return the attribute of the class.
     */
    public ErrorTypeFamily getErrorTypeFamily() {
        return errorTypeFamily;
    }
    /**
     * Method to string which provides the string representation of the instances of these classes. It contains a switch case which
     * determines a specific string for each type of exception, which is to be returned in order to let the user know why was the exception thrown.
     * @return a string which explains what was the exception.
     */
    public String toString() {
        switch (getErrorTypeFamily()) {
            case INVALID_GENDER_FATHER:
                return "The gender of the father of the family is not male, and therefore it is not valid.";
            case INVALID_GENDER_MOTHER:
                return "The gender of the mother of the family is not male, and therefore it is not valid.";
            case NO_FERTILE_MALE_ASSIGNED:
                return "This family will produce 0 offspring as it lacks a fertile male.";
            case MUTATION_MALE_LACKS_POLYGAMY:
                return "The male mouse of the family does not present the mutation of polygamy and he should have it.";
            case MUTATION_MALE_LACKS_STERILITY:
                return "The male mouse of the family does not present the mutation of sterility and he should have it.";
            case MUTATION_MALE_HAS_POLYGAMY:
                return "The male mouse of the family has the gen of polygamy and he should not have it.";
            case MUTATION_MALE_HAS_STERILITY:
                return "The male mouse of the family has the gen of sterility and he should not have it.";
            case NO_MOTHER_ASSIGNED:
                return "This family was not assigned a female mouse.";
            default:
                return "An error has occurred.";
        }
    }
}