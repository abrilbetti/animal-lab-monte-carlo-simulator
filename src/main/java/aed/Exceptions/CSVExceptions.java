package aed.Exceptions;

/**
 * Class that contains personalised exceptions in order to handle with unexpected scenarios regarding the use of files in the
 * "CSV" class, which may arise when trying to either read or save files.
 * @author abrilbetti
 */
public class CSVExceptions extends RuntimeException {
  /**
   * Enumerate that contains the different types of exceptions we can find when dealing with files.
   */
  public enum ErrorTypeCSV{
    INVALID_FORMAT_OF_LINE_POPULATION, INVALID_FORMAT_OF_LINE_MOUSE, POPULATION_WAS_NEVER_SAVED, NO_FILE_OPENED, POPULATION_WAS_ALREADY_SAVED_AS
  }
  public ErrorTypeCSV errorTypeCSV;

  /**
   * Constructor that initialises the only attribute of this class, "errorTypeCSV" with a value which is received as parameter.
   * @param errorTypeCSV contains the type of exception, which is determined when working with the class "CSV" and encountering diverse unexpected situations.
     */
  public CSVExceptions(ErrorTypeCSV errorTypeCSV){
    this.errorTypeCSV = errorTypeCSV;
  }
  /**
   * Getter for the attribute of the "CSVExceptions" class which makes the attribute of this class accessible.
   * @return the attribute of the class.
   */
  public ErrorTypeCSV getErrorTypeCSV() {
    return errorTypeCSV;
  }
  /**
   * Method to string which provides the string representation of the instances of these classes. It contains a switch case which
   * determines a specific string for each type of exception, which is to be returned in order to let the user know why was the exception thrown.
   * @return a string which explains what was the exception.
   */
  public String toString() {
    switch (getErrorTypeCSV()) {
      case INVALID_FORMAT_OF_LINE_POPULATION:
        return "The format of the line read from the file does not coincide with the format of a population.";
      case INVALID_FORMAT_OF_LINE_MOUSE:
        return "The format of the line read from the file does not coincide with the format of a mouse.";
      case POPULATION_WAS_NEVER_SAVED:
        return "The population was never saved. Redirecting to 'Save as'...";
      case NO_FILE_OPENED:
        return "You have not opened a file yet. Please do so before you try to save it.";
      case POPULATION_WAS_ALREADY_SAVED_AS:
        return "The population has already been saved as. Redirecting to 'Save'...";
      default:
        return "An error has occurred.";
    }
  }
}