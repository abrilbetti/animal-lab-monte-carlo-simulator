package aed.InputOutput;

import aed.Comparators.ComparatorByBirthdate;
import aed.Comparators.ComparatorByWeight;
import aed.Exceptions.MouseExceptions;
import aed.Exceptions.PopulationExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Gender;
import aed.Laboratory.Population;
import aed.Laboratory.Mouse;
import aed.Main.MainAnimalLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.Collections;
import java.util.List;

/**
 * Input is the class that deals with reading the information the user introduces as inputs and making sure it is the data type the program expects. Also,
 * some of its functions output lines of codes in order to ask the user to introduce an input.
 * This class has functions that represent options number 2, 3, 6 and 7 from the menu shown to the user.
 * @author abrilbetti
 */
public class Input {
    /**
     * Function that creates a population with the information the user introduces as inputs (the attributes of the class population)
     * @return the population created
     */
    public static Population createPopulation(){
        //Ask the user to introduce the information needed in order to create the population
        System.out.println("Introduce the population's name (string): ");
        String namePopulation = Input.readString();
        System.out.println("Introduce the name of the responsible of the population (string): ");
        String nameResponsible = Input.readString();
        System.out.println("Introduce the amount of days of procreation of the population (integer smaller or equal to 630 and multiple of 45): ");
        int daysProcreation = Input.readInteger();
        Population usersPopulation = null;
        try {
            usersPopulation = new Population(namePopulation, nameResponsible, daysProcreation);
            MainAnimalLab.filePath = null; //If the population was properly created, I also set the current path to null, which is
            // useful in the 'save' function in order to check whether this new population has already been 'saved as' or not
        } catch(PopulationExceptions pe){
            System.out.println("ERROR: " + pe);
        }
        return usersPopulation;
    }
    /**
     * Function that creates a virtual population with the information the user introduces as inputs (the attributes of the class population) and the percentages of the virtual mice.
     * @return the virtual population created.
     */
    public static Population createVirtualPopulation(){
        //Ask the user to introduce the information needed in order to create the virtual population
        System.out.println("Introduce the population's name (string): ");
        String namePopulation = Input.readString();
        System.out.println("Introduce the name of the responsible of the population (string): ");
        String nameResponsible = Input.readString();
        System.out.println("Introduce the amount of days of procreation of the population (integer smaller or equal to 630 and multiple of 45): ");
        int daysProcreation = Input.readInteger();
        System.out.println("Introduce the total number of mice you want the virtual population to have: ");
        int totalNumberMice = Input.readInteger();
        System.out.println("Introduce the percentage of male mice in the population: ");
        int percentageMaleMice = Input.readInteger();
        System.out.println("Introduce the percentage of female mice in the population: ");
        int percentageFemaleMice = Input.readInteger();
        System.out.println("Introduce the percentage of sterile male mice in the population: ");
        int percentageSterileMaleMice = Input.readInteger();
        System.out.println("Introduce the percentage of polygamous male mice in the population: ");
        int percentagePolygamousMaleMice = Input.readInteger();
        System.out.println("Introduce the percentage of mutated X chromosomes among the female mice of the population: ");
        int percentageXmutInFemales = Input.readInteger();
        Population virtualPopulation = null;
        try {
            //I call the constructor of virtual populations
            virtualPopulation = new Population(namePopulation, nameResponsible, daysProcreation, totalNumberMice, percentageMaleMice, percentageSterileMaleMice, percentagePolygamousMaleMice, percentageFemaleMice, percentageXmutInFemales);
            MainAnimalLab.filePath = null; //If the population was properly created, I also set the current path to null, which is
            // useful in the 'save' function in order to check whether this new population has already been 'saved as' or not
            //I call the method that fills the virtual population with virtual mice
            virtualPopulation.fillVirtualPopulation(totalNumberMice, percentageFemaleMice, percentageMaleMice, percentageSterileMaleMice, percentagePolygamousMaleMice, percentageXmutInFemales);
        } catch(PopulationExceptions | MouseExceptions pe){
            System.out.println("ERROR: " + pe);
        }
        //I return the virtual population
        return virtualPopulation;
    }

    /**
     * Function that calls the constructor of the mouse class and creates a mouse with the data introduced by the user.
     * @return the mouse that was created
     */
    public static Mouse createMouse(){
        Mouse mouse1 = null;
        //I ask the user to introduce the information needed to create a new mouse
        System.out.println("Introduce the mouse's birthday (integer): ");
        LocalDate date = Input.readLocalDate();
        System.out.println("Introduce the weight of the mouse (integer between 10 (inclusive) and 500 (inclusive)): ");
        int weightMouse1 = Input.readInteger();
        System.out.println("Introduce the temperature in C of the mouse (floating-point number between 1 (inclusive) and 45 (inclusive)): ");
        float temperatureInC = Input.readFloat();
        System.out.println("Introduce the gender of the mouse (FEMALE or MALE): ");
        Gender gender;
        //I check that the information introduced by the user is valid (in the cases of gender and chromosomes)
        while(true) {
            try {
                gender = Input.readGender();
                break;
            } catch (IllegalArgumentException iae) {
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing an appropriate value for the gender (male or female)!");
            }
        }
        System.out.println("Introduce any description of the mouse (String): ");
        String descriptionMouse = Input.readString();
        System.out.println("Introduce X or Xmut for the first chromosome of the mouse: ");
        Chromosome chromosome1Mouse = null;
        try{
            chromosome1Mouse = Input.readChromosome();
        } catch(IllegalArgumentException iae){
            System.out.println("ERROR: the type of data you introduced is incorrect. Try again, now introducing an appropriate value for the first chromosome (X or Xmut)!");
        }
        System.out.println("Introduce X, Xmut, Y or Ymut for the gender-determining chromosome of the mouse: ");
        Chromosome chromosome2Mouse = null;
        try {
            chromosome2Mouse = Input.readChromosome();
        } catch(IllegalArgumentException iae){
            System.out.println("ERROR: the type of data you introduced is incorrect. Try again, now introducing an appropriate value for the second chromosome!");
        }
        try{
            //I create the mouse with the data provided by the user
            mouse1 = new Mouse(date, weightMouse1, chromosome1Mouse, chromosome2Mouse, gender, temperatureInC, descriptionMouse);
        } catch(MouseExceptions me){
            System.out.println("ERROR:" + me);
        }
        return mouse1;
    }

    /**
     * Function that receives 2 parameters and, depending on the value of the "ordinationCriteria" parameter, calls the appropriate ordination method of population class.
     * @param ordinationCriteria integer that stores a number between 1 and 3 that the user introduced, which states the type of ordination the user wants to perform.
     * @param populationCreated instance of population that stores the previously created population.
     */
    public static List<Mouse> orderMiceOfPopulation(int ordinationCriteria, Population populationCreated){
        //I create a switch and a case for each possible value that "ordinationCriteria" can store
        List<Mouse> arrayListMice = populationCreated.getCopyOfMiceInPopulation();
        switch(ordinationCriteria){
            case 1: //order the mice in increasing order of their reference codes
                Collections.sort(arrayListMice);
                break;
            case 2: //order the mice in chronological order
                //populationCreated.orderMiceChronologically();
                Collections.sort(arrayListMice, new ComparatorByBirthdate());
                break;
            case 3: //order the mice in increasing order of their weights
                Collections.sort(arrayListMice, new ComparatorByWeight());
                break;
            default:
                //If the number the user introduced is not 1, 2 nor 3, I print an error message.
                System.out.println("The option you introduced is invalid. ");
        }
        return arrayListMice;
    }

    /**
     * Function that modifies the data of a mouse from the created population by calling the method "changeMouseData" of the class
     * population. It catches exceptions in the case the mouse is not found within the array of mouses of the population or if any
     * of the introduced values (the data to modify of the mouse) are invalid (temperature>45, temperature<=0, weight<=0 or weight>501
     * @param usersPopulation which stores the population the user created
     */
    public static void modifyMouseData(Population usersPopulation){
        //I ask the user to introduce the data I need in order to modify the mouse's data
        System.out.println("Introduce the reference code of the mouse whose information you wish to modify (integer): ");
        int refCodeOfMouseToModify = Input.readInteger();
        System.out.println("Introduce the new weight of the mouse (integer between 10 (inclusive) and 500 (inclusive)): ");
        int newWeightMouse = Input.readInteger();
        System.out.println("Introduce the new temperature in C of the mouse (floating-point number between 1 (inclusive) and 45 (inclusive)): ");
        float newTemperatureInC = Input.readFloat();
        System.out.println("Introduce the new description of the mouse (String): ");
        String newDescriptionMouse = Input.readString();
        try {
            //I call the method of population that modifies a mouse's data
            Mouse mouseToModify = usersPopulation.identifyMouse(refCodeOfMouseToModify);
            mouseToModify.changeMouseData(newWeightMouse, newTemperatureInC, newDescriptionMouse);
            //The following line will only be printed if the mouse's information is properly modified
            System.out.println("The mouse's data has been successfully modified.");
        } catch (PopulationExceptions | MouseExceptions pe) {
            System.out.println("ERROR: " + pe);
        }
    }
    /**
     * Function that shows the information of the desired mouse by calling the method "seeMouseInformation" of the class population.
     * It catches an exception that is thrown if the mouse is not found within the array of mouses the population has.
     * @param usersPopulation which stores the population the user created
     */
    public static void seeInformationOfMice(Population usersPopulation){
        //I ask for the reference code of the mouse whose information the user wants to see
        System.out.println("Introduce the reference code of the mouse whose information you wish to see (integer): ");
        int refCodeOfMouse = Input.readInteger();
        try {
            String mouseInformation = usersPopulation.seeMouseInformation(refCodeOfMouse);
            //I print the information of the mouse
            System.out.println(mouseInformation);
        } catch (PopulationExceptions pe) {
            //An exception is thrown if the mouse with the introduced reference code does not exist.
            System.out.println("ERROR: " + pe);
        }
    }
    /**
     * Function that reads the integer input  the user introduced
     * @return an int variable that stores the input introduced by the user
     */
    public static int readInteger(){
        /*The while is a controlled loop that will run until a "return" if found within the code. This will only happen when the
        input introduced by the user is an int (when the "parse" conversion is accomplished) and this value is returned. On the
        other hand, if the input introduced is not an integer type, it won't be possible to convert it to an int value, which will
         throw an exception, causing the while loop to repeat itself again.*/
        while(true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String readString = br.readLine(); //Can throw an IOException
                if(readString.trim().isEmpty()){
                    //Trim function removes whitespaces, tabs, etc. Once removed, I check whether it is empty or not.
                    throw new IllegalArgumentException("ERROR: input cannot be empty or whitespace.");
                }
                int integer = Integer.parseInt(readString);
                return integer;
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            } catch(IllegalArgumentException iae){
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing an integer!");
            }
        }
    }
    /**
     * Function that reads the string input  the user introduced
     * @return a string variable that stores the input introduced by the user
     */
    public static String readString(){
        while(true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String readString = br.readLine();
                if(readString.trim().isEmpty()){
                    throw new IllegalArgumentException("ERROR: input cannot be empty or whitespace.");
                }
                return readString;
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            } catch(IllegalArgumentException iae){
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing a string!");
            }
        }
    }
    /**
     * Function that reads the float input  the user introduced
     * @return a float variable that stores the input introduced by the user
     */
    public static float readFloat(){
        while(true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String readString = br.readLine();
                if(readString.trim().isEmpty()){
                    throw new IllegalArgumentException("ERROR: input cannot be empty or whitespace.");
                }
                float floatVariable = Float.parseFloat(readString);
                return floatVariable;
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            } catch(IllegalArgumentException iae){
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing a floating-point number!");
            }
        }
    }

    /**
     * Function that reads an integer introduced by the user, checks if it is between the valid range (end and begin integer values it receives as parameters)
     * and returns the valid integer or throws an exception if it is not valid.
     * @param begin states the minimum value the input integer can take
     * @param end states the maximum value the input integer can take
     * @param message string that we want to output to the user
     * @return the read integer (only if it is a valid one)
     */
    public static int readIntegerMessage(int begin, int end, String message){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readString = "";
        /*The buffered reader is declared outside the loop because if not, each readLine() call re-initializes BufferedReader,
        which can lead to input lag or double prompts (such as having to introduce "return" twice after introducing an invalid value
        for day or month or year of a date).
         */
        System.out.println(message);
        while(true){
            try{
                readString = br.readLine();
                if(readString.trim().isEmpty()){
                    //Trim function removes whitespaces, tabs, etc. Once removed, I check whether it is empty or not.
                    throw new IllegalArgumentException("ERROR: input cannot be empty or whitespace.");
                }
                int integer = Integer.parseInt(readString);
                if(integer >= begin && integer <= end){
                    return integer;
                }
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            } catch(IllegalArgumentException iae){
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing an integer between " + begin + " and " + end + "!");
            }
        }
    }

    /**
     * Function that reads the dates the user introduces as inputs. It calls the function "readInputMessage" 3 times in order to read each component of the date (day, month and year).
     * @return the date if it is a valid one
     */
    public static LocalDate readLocalDate(){
        while(true) {
            int dia = readIntegerMessage(1, 31, "Introduce the day: ");
            int mes = readIntegerMessage(1, 12, "Introduce the month: ");
            int year = readIntegerMessage(2022, 2025, "Introduce the year (from 2022): ");
            try {
                //If the date is invalid (for example February 29th or April the 31st) an exception is thrown
                LocalDate date = LocalDate.of(year, mes, dia);
                return date;
            } catch (DateTimeException dateTimeException) {
                System.out.println("ERROR: The date you introduced is incorrect. Try again introducing a valid one.");
            }
        }
    }

    /**
     * Function that reads the input the user introduces for stating the chromosome of the mouse.
     * @return the chromosome if th introduced input is a valid one
     */
    public static Chromosome readChromosome(){
        while(true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String readString = br.readLine();
                if(readString.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                Chromosome chromosome = Chromosome.valueOf(readString.trim());
                return chromosome;
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            } catch(IllegalArgumentException iae){
                System.out.println("ERROR: The type of data you introduced is incorrect. Try again, now introducing an appropriate chromosome!");
            }
        }
    }
    /**
     * Function that reads the input the user introduces for stating the chromosome of the mouse.
     * @return the chromosome if the introduced input is a valid one
     */
    public static Gender readGender(){
        while(true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String readString = br.readLine();
                if(readString.trim().isEmpty()){
                    throw new IllegalArgumentException();
                }
                //The function 'value of' automatically throws an illegal argument exception if it is not able to perform the parse operation
                Gender gender = Gender.valueOf(readString.trim().toUpperCase());
                return gender;
            } catch(IOException e){
                System.out.println("ERROR: there was an error when reading you input, try again!");
            }
        }
    }
}