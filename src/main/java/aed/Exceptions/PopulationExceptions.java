package aed.Exceptions;
/**
 * Class that contains personalised exceptions in order to handle with unexpected scenarios regarding the use of "Population" instances.
 * @author abrilbetti
 */
public class PopulationExceptions extends RuntimeException {
    /**
     * Enumerate that contains the different types of exceptions we can find when dealing with instances of the class population
     */
    public enum ErrorType{
        MOUSE_NOT_FOUND, INVALID_PROCREATION_DAYS, INVALID_NAME, NON_MULTIPLE_45_PROCREATION_DAYS, INVALID_TYPE_PROCREATION_DAYS, FAILED_TO_REMOVE_MOUSE, FAILED_TO_ADD_MOUSE,
        INVALID_NUMBER_OF_MICE, INVALID_PERCENTAGE_MALES, INVALID_PERCENTAGE_FEMALES, INVALID_PERCENTAGE_STERILE_MALES, INVALID_PERCENTAGE_POLYGAMOUS_MALES, INVALID_PERCENTAGE_XMUT,
        INVALID_MALE_FEMALE_PERCENTAGES

    }

    public ErrorType errorType;

    /**
     * Constructor that initialises the only attribute of this class, "errorType" with a value which is received as parameter.
     * @param errorType contains the type of exception, which is determined when creating an instance of 'Population' and encountering diverse unexpected situations.
     */
    public PopulationExceptions(ErrorType errorType){
        this.errorType = errorType;
    }
    /**
     * Getter for the attribute of the "PopulationExceptions" class which makes the attribute of this class accessible
     * @return the attribute of the class
     */
    public ErrorType getErrorType() {
        return errorType;
    }
    /**
     * Method to string which provides the string representation of the instances of this class. It contains a switch case which
     * determines a specific string for each type of exception, which is to be returned in order to let the user know what was the error
     * @return a string which explains what was the exception
     */
    public String toString(){
        switch(getErrorType()){
            case MOUSE_NOT_FOUND:
                return "There is no mouse with that reference code.";
            case INVALID_PROCREATION_DAYS:
                return "The days of procreation of the population is invalid (it must be a number between 1 and 630).";
            case NON_MULTIPLE_45_PROCREATION_DAYS:
                return "The introduced days of procreation of the population is invalid because it is not a multiple of 45.";
            case INVALID_TYPE_PROCREATION_DAYS:
                return "The input you introduced is invalid as it is not an integer.";
            case INVALID_NAME:
                return "The name of the population's responsible introduced is invalid.";
            case FAILED_TO_REMOVE_MOUSE:
                return "There was an error when trying to remove the mouse from the population.";
            case FAILED_TO_ADD_MOUSE:
                return "The mouse you are trying to add to the population is invalid.";
            case INVALID_NUMBER_OF_MICE:
                 return "The introduced value of the total number of mice is invalid.";
            case INVALID_PERCENTAGE_MALES:
                 return "The introduced value of the percentage of male mice is invalid.";
            case INVALID_PERCENTAGE_FEMALES:
                 return "The introduced value of the percentage of female mice is invalid.";
            case INVALID_PERCENTAGE_STERILE_MALES:
                 return "The introduced value of the percentage of sterile mice is invalid.";
            case INVALID_PERCENTAGE_POLYGAMOUS_MALES:
                 return "The introduced value of the percentage of polygamous mice is invalid.";
            case INVALID_PERCENTAGE_XMUT:
                 return "The introduced value of the percentage of muted 'x' chromosomes is invalid.";
            case INVALID_MALE_FEMALE_PERCENTAGES:
                 return"The introduced percentages of male and female mice do not add up to 100 and, therefore, are invalid.";
            default:
                return "An error has occurred.";
        }
    }
}