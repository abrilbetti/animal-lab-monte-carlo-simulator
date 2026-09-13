package aed.CSVAdministration;

import aed.Exceptions.CSVExceptions;
import aed.Exceptions.InputExceptions;
import aed.Exceptions.MouseExceptions;
import aed.Exceptions.PopulationExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Gender;
import aed.Laboratory.Mouse;
import aed.Laboratory.Population;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV is the class that is in charge of opening a file and reading its information, saving a file and 'saving as' a file.
 * In summary, this class has several functions that represent options number 1, 8 and 9 from the menu shown to the user.
 * @author abrilbetti
 */
public class CSV {
    /**
     * Function that opens a file using the path stored in the variable current file path and prints a message showing that the operation was
     * successful or throws an error if it was not.
     * @param filePath stores the file path the user introduced in the main
     * @return the read population which is created and loaded with the mice if there were any in the CSV file. If not, the function returns null
     */
    public static Population openPopulationFromFile(String filePath) {
        Population populationRead = null;
        try(BufferedReader bfR = new BufferedReader(new FileReader(filePath))){
            String line = bfR.readLine();
            //If there is something written in the file, I try to read it
            if(line != null){
                try {
                    populationRead = isLineAPopulation(line);
                } catch(CSVExceptions csve) {
                    System.out.println("ERROR: " + csve);
                }
                if(populationRead != null) {
                    //I keep reading until the read line is null (when there are no more lines with content to read)
                    while ((line = bfR.readLine()) != null) {
                        try {
                            //I call the method that verifies if the read line is a mouse and, if it is, creates it and returns it
                            Mouse mouseRead = isLineAMouse(line);
                            //Then, I add the mouse to the population we read and created previously.
                            populationRead.addMiceToList(mouseRead);
                        } catch (CSVExceptions | PopulationExceptions pe) {
                            System.out.println("ERROR: " + pe);
                        }
                    }
                }
            }
        } catch(IOException | NumberFormatException | DateTimeParseException e){
            //DateTimeParseException can be thrown if the parse function fails when it converts the read information into a 'LocalDate' variable
            System.out.println("ERROR: there was an error reading the file.");
        }
        return populationRead;
    }

    /**
     * Method that receives a string (the line read from the introduced file) and checks that its length is the appropriate one (in order for it to
     * be a population). If it is, then it creates a population with the information in the string received as parameter, which is returned.
     * @param readLine string that contains a line that was read from the file whose file path the user introduced.
     * @return the population created with the line read from the file.
     * @throws CSVExceptions when the length of the read line does not coincide with the length a population should have.
     */
    private static Population isLineAPopulation(String readLine) throws CSVExceptions {
        //I create an array which is filled with the content of the line read of the file. Positions store whatever is written between the commas.
        String[] populationData = readLine.split(",");
        /*If the array contains more or less than 3 positions (the amount of attributes the population class has
        and therefore the amount of data that should be introduced in the file in order for it to be a population),
        then I throw an exception.
        */
        //If the read line is a population, then we create a population with its data.
        Population populationCreated = null;
        if(populationData.length == 9) {
            //I assign each position of the array I created to an attribute of population
            String namePopulation = populationData[0].trim();
            String nameResponsible = populationData[1].trim();
            int daysInLab = Integer.parseInt(populationData[2].trim());
            int amountMice = Integer.parseInt(populationData[3].trim());
            int percentageMales = Integer.parseInt(populationData[4].trim());
            int percentageSterileMales = Integer.parseInt(populationData[5].trim());
            int percentagePolygamousMales = Integer.parseInt(populationData[6].trim());
            int percentageFemales = Integer.parseInt(populationData[7].trim());
            int percentageXmutFemales = Integer.parseInt(populationData[8].trim());
            //I call the method that creates populations and create one with the data I read from the file
            try {
                populationCreated = new Population(namePopulation, nameResponsible, daysInLab, amountMice, percentageMales, percentageSterileMales, percentagePolygamousMales, percentageFemales, percentageXmutFemales);
            } catch (PopulationExceptions pe) {
                System.out.println("ERROR: " + pe);
            }
        } else if(populationData.length == 3) {
            //I assign each position of the array I created to an attribute of population
            String namePopulation = populationData[0].trim();
            String nameResponsible = populationData[1].trim();
            int daysInLab = Integer.parseInt(populationData[2].trim());
            //I call the method that creates populations and create one with the data I read from the file
            try {
                populationCreated = new Population(namePopulation, nameResponsible, daysInLab);
            } catch (PopulationExceptions pe) {
                System.out.println("ERROR: " + pe);
            }
        } else{
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.INVALID_FORMAT_OF_LINE_POPULATION);
        }
        return populationCreated;
    }

    /**
     * Method that receives a string (the line read from the introduced file) and checks that its length is the appropriate one (in order for it to
     * be a mouse). If it is, then it creates a mouse with the information in the string received as parameter, which is returned.
     * @param readLine string that contains a line that was read from the file whose file path the user introduced.
     * @return the created mouse.
     * @throws CSVExceptions when the length of the read line does not coincide with the length a mouse should have.
     */
    private static Mouse isLineAMouse(String readLine) throws CSVExceptions {
        //I create an array which is filled with the content of the line read of the file. Positions store whatever is written between the commas.
        String[] mouseData = readLine.split(",");
        // If the array contains an amount of positions different to the one that we should read if we are reading a mouse, I throw an exception.
        if(mouseData.length != 7) {
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.INVALID_FORMAT_OF_LINE_MOUSE);
        }
        LocalDate birthday = LocalDate.parse(mouseData[0].trim());
        int weight = Integer.parseInt(mouseData[1].trim());
        Chromosome chromosome1 = Chromosome.valueOf(mouseData[2].trim());
        Chromosome chromosome2 = Chromosome.valueOf(mouseData[3].trim());
        Gender gender = Gender.valueOf(mouseData[4].toUpperCase().trim());
        float temperature = Float.parseFloat(mouseData[5].trim());
        String description = mouseData[6];
        Mouse mouseRead = null;
        //I create the mouse with the information read from the CSV file and add it to the population that was also read from the CSV file
        try {
            mouseRead = new Mouse(birthday, weight, chromosome1, chromosome2, gender, temperature, description);
        } catch(MouseExceptions me){
            System.out.println("ERROR:" + me);
        }
        if(mouseRead == null){
            throw new IllegalArgumentException("ERROR: The mouse could not be created.");
        }
        return mouseRead;
    }

    /**
     * Function that saves a population only if it has already been saved as in the past. First, it checks if the population
     * was already saved as in the past and, if it wasn't it calls the function 'save as'. If it was, it writes the data to save
     * in the file, just as it is done in the 'save as' method.
     * @param filePath stores the path where the file has already been saved as, or null if it hasn't been 'saved as' yet
     * @param populationCreated stores the population the user has created (if any) or null otherwise
     * @throws CSVExceptions if the population was not saved as or if the user has not opened a file.
     * @throws InputExceptions if the user has not created a population.
     */
    public static void savePopulation(Population populationCreated, String filePath, boolean openedFile, boolean savedAs) throws CSVExceptions {
        /*'If' that checks whether the user has already created a population and if the file path is null (if it is null it is either because the file
        has not been saved yet or the program has just started). In any of these scenarios, a message is printed stating what the problem is */
        if (populationCreated == null) {
            throw new InputExceptions(InputExceptions.ErrorTypeInput.NULL_POPULATION);
        }else if(!openedFile){ //If the boolean variable is false, it is because we have not opened a population yet
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.NO_FILE_OPENED);
        } else if(!savedAs){
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.POPULATION_WAS_NEVER_SAVED);
        }
        else{ //The file is saved
            try(BufferedWriter bfW = new BufferedWriter(new FileWriter(filePath))){
                //I write the data of the population in the file, separating each field by a comma
                bfW.write(populationCreated.getNamePopulation() + ", " + populationCreated.getNameResponsible() + ", " + populationCreated.getDaysProcreation());
                bfW.newLine();
                //I create a copy of the array of mice of the population I am saving which is as long as the amount of mice the population has
                List<Mouse> arrayListMice = new ArrayList<>(populationCreated.getCopyOfMiceInPopulation());

                //For each that runs as many times as mice were added to the population
                for(Mouse mouse : arrayListMice){
                    //I write the data of the mouse stored in the position i of the copied array in the file, separating each field with a comma
                    bfW.write(mouse.getBirthDate() + ", " + mouse.getWeightGr() + ", " + mouse.getChromosome1() + ", " + mouse.getChromosome2()
                            + ", " + mouse.getGender() + ", " + mouse.getTemperatureInC() + ", " + mouse.getDescription() + ", " + mouse.getRefCode());
                    bfW.newLine();
                }
            } catch(IOException | NullPointerException e){
                System.out.println("ERROR: there was an error when saving the file.");
            }
        }
    }

    /**
     * Function that saves the created population in a text file.
     * @param newFilePath path introduced by the user
     * @param populationCreated stores the population the user wants to 'save as' if he/she has created one or null otherwise
     * @throws InputExceptions if the user has not created a population yet.
     * @throws CSVExceptions if the user has not opened a file.
     */
    public static void savePopulationAs(Population populationCreated, String newFilePath, boolean fileOpened, boolean savedAs) throws InputExceptions, CSVExceptions {
        if(populationCreated == null){
            throw new InputExceptions(InputExceptions.ErrorTypeInput.NULL_POPULATION);
        }
        else if(!fileOpened){ //If the boolean variable is false, it is because we have not opened a population yet
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.NO_FILE_OPENED);
        }
        else if(savedAs){
            throw new CSVExceptions(CSVExceptions.ErrorTypeCSV.POPULATION_WAS_ALREADY_SAVED_AS);
        }
        else {
            try (BufferedWriter bfW = new BufferedWriter(new FileWriter(newFilePath))) {
                bfW.write(populationCreated.getNamePopulation() + ", " + populationCreated.getNameResponsible() + ", " + populationCreated.getDaysProcreation());
                bfW.newLine();
                //I create a copy of the list of mice in the population by calling the method of population that does so.
                List<Mouse> arrayListMice = new ArrayList<>(populationCreated.getCopyOfMiceInPopulation());
                //For each that runs the list of mice
                for(Mouse mouse : arrayListMice) {
                    bfW.write(mouse.getBirthDate() + ", " + mouse.getWeightGr() + ", " + mouse.getChromosome1() + ", " + mouse.getChromosome2()
                            + ", " + mouse.getGender() + ", " + mouse.getTemperatureInC() + ", " + mouse.getDescription() + ", " + mouse.getRefCode());
                    bfW.newLine();
                }
            } catch (IOException | NullPointerException e) {
                System.out.println("ERROR: there was an error saving the file.");
            }
        }
    }
}