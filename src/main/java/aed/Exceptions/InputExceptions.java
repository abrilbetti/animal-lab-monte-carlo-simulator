package aed.Exceptions;
/**
 * Class that contains personalised exceptions in order to handle with unexpected scenarios regarding the use of the
 * methods of "Input" class.
 * @author abrilbetti
 */
public class InputExceptions extends RuntimeException {
    /**
     * Enumerate that contains the different types of exceptions we can find when using the methods of Input.
     */
    public enum ErrorTypeInput{
        NULL_POPULATION, EMPTY_LIST_OF_MICE
    }
    public ErrorTypeInput errorTypeInput;

    /**
     * Constructor that initialises the only attribute of this class, "errorTypeInput" with a value which is received as parameter.
     * @param errorTypeInput contains the type of exception, which is determined when working with the class "Input" and encountering diverse unexpected situations.
     */
    public InputExceptions(ErrorTypeInput errorTypeInput){
        this.errorTypeInput = errorTypeInput;
    }
    /**
     * Getter for the attribute of the "InputExceptions" class which makes the attribute of this class accessible.
     * @return the attribute of the class.
     */
    public ErrorTypeInput getErrorTypeInput() {
        return errorTypeInput;
    }
    /**
     * Method to string which provides the string representation of the instances of these classes. It contains a switch case which
     * determines a specific string for each type of exception, which is to be returned in order to let the user know why was the exception thrown.
     * @return a string which explains what was the exception.
     */
    public String toString() {
        switch (getErrorTypeInput()) {
            case NULL_POPULATION:
                return "You have not created a population yet. Please make sure you do before you try to use this operation.";
            case EMPTY_LIST_OF_MICE:
                return "You have not added mice to your population yet. Please do so prior to using this operation.";
            default:
                return "An error has occurred.";
        }
    }
}