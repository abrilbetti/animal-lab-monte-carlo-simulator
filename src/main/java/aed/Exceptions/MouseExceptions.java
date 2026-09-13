package aed.Exceptions;

/**
 * Class that contains personalised exceptions in order to handle with unexpected scenarios regarding the use of "Mouse" instances.
 * @author abrilbetti
 */
public class MouseExceptions extends RuntimeException {
    /**
     * Enumerate that contains the different types of exceptions we can find when dealing with instances of the class mouse.
     */
    public enum ErrorTypeMouse{
        INVALID_WEIGHT, INVALID_TEMPERATURE, INVALID_CHROMOSOME_1, INVALID_CHROMOSOME_2, NON_CONCORDANT_GENDER, INVALID_BIRTHDAY
    }

    public ErrorTypeMouse errorTypeMouse;

    /**
     * Constructor which initialises the only attribute of this class, "errorTypeMouse" with a value which is received as parameter.
     * @param errorTypeMouse contains the type of exception, which is determined when creating an instance of 'Mouse' and encountering diverse unexpected situations.
     */
    public MouseExceptions(ErrorTypeMouse errorTypeMouse){
        this.errorTypeMouse = errorTypeMouse;
    }
    /**
     * Getter for the attribute of the "MouseExceptions" class which makes the attribute of this class accessible
     * @return the attribute of the class
     */
    public ErrorTypeMouse getErrorTypeMouse(){
        return errorTypeMouse;
    }
    /**
     * Method to string which provides the string representation of the instances of this class. It contains a switch case which
     * determines a specific string for each type of exception, which is to be returned in order to let the user know what was the error
     * @return a string which explains what was the exception
     */
    public String toString(){
        switch(getErrorTypeMouse()){
            case INVALID_WEIGHT:
                return "The introduced value of the mouse's weight is invalid.";
            case INVALID_TEMPERATURE:
                return "The introduced value of the mouse's temperature is invalid.";
            case INVALID_CHROMOSOME_1:
                return "The first chromosome you introduced is not valid.";
            case INVALID_CHROMOSOME_2:
                return "The second chromosome you introduced is not valid.";
            case INVALID_BIRTHDAY:
                return "The introduced date of the mouse's birthday is invalid.";
            case NON_CONCORDANT_GENDER:
                return "The introduced gender does not match the introduced chromosomes.";
            default:
                return "An error has occurred.";
        }
    }
}