package aed.Main;

import aed.Exceptions.CSVExceptions;
import aed.Exceptions.FamilyExceptions;
import aed.Exceptions.InputExceptions;
import aed.Exceptions.PopulationExceptions;
import aed.InputOutput.Input;
import aed.InputOutput.Output;
import aed.Laboratory.Mouse;
import aed.Laboratory.Population;
import aed.CSVAdministration.CSV;

import java.util.List;

public class MainAnimalLab {
    //I create a static instance of the population class which I set to null
    private static Population populationCreated = null;
    public static String filePath = null;
    private static boolean openedFile = false;
    private static boolean savedAs = false;

    public static void main(String[] args) {
        //I create an integer variable that stores the input the user introduces when asked what option of the menu wishes to perform
        int menuOption;
        //Boolean variable I create in order to stop the program when the user wants to exit it
        boolean keepRunning = true;
        //The while runs until the user chooses the option 0 of the menu, which is when the "keepRunning" boolean variable is set to false and the while loop is exited
        while (keepRunning) {
            Output.showMenu(); //I call the function that shows the menu to the user
            menuOption = Input.readInteger(); //read the input introduced by the user
            switch (menuOption) {
                case 1:
                    //I ask the user to introduce the path of the CSV file
                    System.out.println("Please introduce the path of the file you want to open: ");
                    filePath = Input.readString();
                    //To use as an example in my computer "/Users/abrilbetti/Desktop/Algoritmos/Programas/Practica/populationTextFile.CSV";
                    //I call the method that opens CSV files and pass as a parameter the path of my file
                    populationCreated = CSV.openPopulationFromFile(filePath);
                    if (populationCreated != null) {
                        Output.printPopulationOpened(populationCreated);
                        //If the population is != null it is because we successfully opened the file. Therefore, I set the variable 'openedFile' to true.
                        openedFile = true;
                        //I print a message showing the operation was successful
                        System.out.println("The population has been successfully opened and loaded.");
                    }
                    break;
                case 2: //Create a new population
                    populationCreated = Input.createPopulation();
                    if (populationCreated != null) {
                        System.out.println("The population has been created successfully.");
                        Output.printPopulationOpened(populationCreated);
                    }
                    break;
                case 3: //Create a virtual population
                    populationCreated = Input.createVirtualPopulation();
                    if (populationCreated != null) {
                        System.out.println("The virtual population has been created successfully.");
                        Output.printPopulationOpened(populationCreated);
                    }
                    break;
                case 4: //Add a new mouse to an existing population
                    try {
                        //I check that the user has created a population
                        isPopulationNull(populationCreated);
                        Mouse mouseCreated = Input.createMouse();
                        populationCreated.addMiceToList(mouseCreated);
                        System.out.println("The mouse has been created and added successfully to the population.");
                    } catch (PopulationExceptions | InputExceptions pe) {
                        System.out.println("ERROR: " + pe);
                    }
                    break;
                case 5: //List the mice of the population
                    try{
                        //I check that the user has created a population and added at least one mouse to t.
                        isPopulationNull(populationCreated);
                        isLisOfMiceEmpty(populationCreated);
                        //If the user has already created a population and at least one mouse has been added to it , we list the reference codes of the mice/mouse.
                        //I call the function that outputs the different ways in which the mice can be ordered and read the number the user introduces by calling the appropriate function of the input class
                        Output.showOrdinationCriteria();
                        int ordinationCriteria = Input.readInteger();
                        //Once I read the type of ordination, I call the function that orders the mice of the population and returns a copy of the array list which is ordered according to the chosen criteria.
                        List<Mouse> listOfMice = Input.orderMiceOfPopulation(ordinationCriteria, populationCreated);
                        System.out.println("Sorted mice of the population: ");
                        //I call the function that prints the mice of the population sorted with the type of order the user chose
                        Output.showOrderedMiceOfPopulation(listOfMice);
                    } catch(InputExceptions ie){
                        System.out.println("ERROR: " + ie);
                    }
                    break;
                case 6: //Delete a mouse from a population
                    /*We will only delete mice (or try to, because the mouse the user wants to delete may not exist) if the user has already created a population
                    and if it has added at least one mouse to the population*/
                    try{
                        isPopulationNull(populationCreated);
                        isLisOfMiceEmpty(populationCreated);
                        System.out.println("Introduce the reference code of the mouse you wish to eliminate from the population (integer): ");
                        int refCodeOfMouseToEliminate = Input.readInteger();
                        try {
                            //I call the method that eliminates mice from a population and pass as parameter the reference code the user introduced
                            populationCreated.deleteMouse(refCodeOfMouseToEliminate);
                            System.out.println("The mouse has been successfully eliminated from the population.");
                        } catch (PopulationExceptions pe) {
                            System.out.println("ERROR: " + pe);
                        }
                    } catch(InputExceptions ie){
                        System.out.println("ERROR: " + ie);
                    }
                    break;
                case 7: //Modify a mouse's data
                    //If the population exists and mice has been added to it, we go on to modify a mouse's data
                    try {
                        isPopulationNull(populationCreated);
                        isLisOfMiceEmpty(populationCreated);
                        //I call the function that modifies the mouse's data
                        Input.modifyMouseData(populationCreated);
                    } catch(InputExceptions ie){
                        System.out.println("ERROR: " + ie);
                    }
                    break;
                case 8: //View detailed information about a mouse
                    //If the user has created a population and at least 1 mouse has been added to it, we go on
                    try {
                        isPopulationNull(populationCreated);
                        isLisOfMiceEmpty(populationCreated);
                        //I call the function that prints the information of the mouse
                        Input.seeInformationOfMice(populationCreated);
                    } catch(InputExceptions ie){
                        System.out.println("ERROR: " + ie);
                    }
                    break;
                case 9: //Simulate the evolution of the population over time
                    //If the user has created a population and at least 1 mouse has been added to it, we go on
                    try {
                        isPopulationNull(populationCreated);
                        isLisOfMiceEmpty(populationCreated);
                        //For that runs as many times as cycles the introduced days in lab represent (by dividing the amount of days by 45)
                        for (int i = 0; i < populationCreated.getDaysProcreation() / 45; i++) {
                            try {
                                populationCreated.assignMiceToFamilies();
                                populationCreated.addBabyMiceToPopulation();
                            } catch (FamilyExceptions fe) {
                                System.out.println("ERROR: " + fe);
                            }
                            //I call the method that computes and prints the statistics of the population's mice.
                            Output.simulatePopulationsEvolution(populationCreated, i);
                        }

                    } catch(InputExceptions ie){
                        System.out.println("ERROR: " + ie);
                    }
                    break;
                case 10: //Save
                    try {
                        CSV.savePopulation(populationCreated, filePath, openedFile, savedAs);
                        System.out.println("The population was correctly saved in the file you indicated!");
                    } catch (CSVExceptions | InputExceptions ie) {
                        System.out.println("ERROR: " + ie);
                    }
                    if (!savedAs && openedFile) {
                        System.out.println("Introduce the file path where you want to save your population: ");
                        String readPath = Input.readString();
                        CSV.savePopulationAs(populationCreated, readPath, openedFile, savedAs);
                        savedAs = true;
                        System.out.println("The population was correctly saved in the file you indicated!");
                    }
                    break;
                case 11: //Save as
                    System.out.println("Enter the new file path: ");
                    String newFilePath = Input.readString();
                    try {
                        CSV.savePopulationAs(populationCreated, newFilePath, openedFile, savedAs);
                        System.out.println("The population was correctly saved in the file you indicated!");
                        //If function 'save as' was performed successfully, I update the value of the 'current path' variable by setting it to the new file path the user introduced.
                        //I also print a message in order to let the user know that the operation was performed correctly, and set 'savedAs' as true.
                        filePath = newFilePath;
                        savedAs = true;
                    } catch (CSVExceptions | InputExceptions ie) {
                        System.out.println("ERROR: " + ie);
                    }
                    if(savedAs){
                        CSV.savePopulation(populationCreated, newFilePath, openedFile, savedAs);
                        System.out.println("The population was correctly saved in the file you indicated!");
                    }
                    break;
                case 0: //EXIT (end programme)
                    System.out.println("Closing the program...");
                    // I set the boolean "keepRunning" variable to false in order to stop the while loop and finish the program
                    keepRunning = false;
                    break;
                default:
                    //If the integer introduced by the user is none of the cases presented before, I print a message
                    System.out.println("The option you chose is invalid. Try again, now entering a valid number (from 0 to 9).");
            }
        }
    }

    /**
     * Function that checks if any mice had been added to the population it receives as parameter.
     * @param population object of Population.
     * @throws InputExceptions when the list of mice of the population is empty.
     */
    private static void isLisOfMiceEmpty(Population population) throws InputExceptions {
        if(population.isListOfMiceEmpty()){
            throw new InputExceptions(InputExceptions.ErrorTypeInput.EMPTY_LIST_OF_MICE);
        }
    }

    /**
     * Function that checks if a population has been created, by analysing if it is equal or not to null.
     * @param population object of Population.
     * @throws InputExceptions when the population is equal to null (it has not been created).
     */
    private static void isPopulationNull(Population population) throws InputExceptions {
        if(population == null){
            throw new InputExceptions(InputExceptions.ErrorTypeInput.NULL_POPULATION);
        }
    }
}