package aed.InputOutput;
import aed.Laboratory.Mouse;
import aed.Laboratory.Population;
import java.util.List;

/**
 * Output is a class that is composed by 2 functions that describe its behaviour.One of them outputs the menu of options, which is printed in the console. The other
 * function prints a population that was read from a file.
 * @author abrilbetti
 */
public class Output {
    /**
     * Function that prints the menu which is offered to the user in the main.
     */
    public static void showMenu() {
        System.out.println("\nPlease, introduce the number of the operation you wish the program to perform from the following menu. \n------ MENU ------ \n" +
                "1. Open a population from a text file. \n2. Create a new population of mice. \n3. Create a virtual population of mice from the percentages of male, female and mutated mice." +
                "\n4. Add a new mouse to an existing population." + "\n5. List the mice of the population. \n6. Delete a mouse of the population. \n" +
                "7. Modify a mouse's data. \n8. Detailed information about a mouse. \n9. Simulate the population's evolution over time.\n10. Save \n11. Save as. \n0. EXIT");
    }

    /**
     * Function that prints the 3 different sorting criteria the user can choose when selects option 5 (list the mice of the population) in the main.
     */
    public static void showOrdinationCriteria() {
        System.out.println("What type of ordination do you wish to perform? (introduce 1, 2 or 3) \n1. Depending on the reference codes of the mice (from lower reference codes to higher ones). " +
                "\n2. Depending on the birthdays of the mice. \n3. Depending on the weight of the mice, from lighter-weighted mice to the heaviest ones.");
    }

    /**
     * Function that calls the method of population that prints the mouse that are in the population.
     *
     * @param listOfMice array list that contains the mice of the population.
     */
    public static void showOrderedMiceOfPopulation(List<Mouse> listOfMice) {
        //population1.printMiceInPopulation();
        for(int i=0; i<listOfMice.size(); i++) {
            System.out.println((listOfMice.get(i)));
        }
    }

    /**
     * Function that prints the data of the population that was read from a CSV file. Both the population data and their data ot their mice (if it has any) are printed
     *
     * @param populationOpened stores the population that was read from the file that was opened (the CSV file)
     */
    public static void printPopulationOpened(Population populationOpened) {
        //I call the toString method of the class Population
        System.out.println(populationOpened);
        //If the population is not empty, we print its mice
        if (!populationOpened.isListOfMiceEmpty()) {
            System.out.println("\nMice in the population: ");
            //I call the method that returns a copy of the array list that contains the mice present in the population
            List<Mouse> arrayListMice = populationOpened.getCopyOfMiceInPopulation();
            for (int i = 0; i < populationOpened.amountOfMiceInPopulation(); i++) {
                //I store the instance of mouse stored in position i of the array list in mouse in order to print its information by using dot operators
                Mouse mouse = arrayListMice.get(i);
                //I call the method toString of Mouse
                System.out.println(mouse);
            }
        } else {
            System.out.println("The population does not have any mice.");
        }
    }

    /**
     * Function that simulates how a population evolves through the amount of days it spends in the laboratory. At the end of each cycle of reproduction,
     * the percentages that illustrate the amount of female, male, sterile, polygamous and normal mice that there are in the population.
     * @param populationCreated object of Population that contains the population that was created in the main (either a virtual one or one created by the user's inputs)
     * @param numberOfCycle integer variable that stores the number of the cycle whose information we are about to print.
     */
    public static void simulatePopulationsEvolution(Population populationCreated, int numberOfCycle) {
        int numberCycle = numberOfCycle+1;
        int amountMiceInPopulation = populationCreated.amountOfMiceInPopulation();
        int amountFemaleMiceInPopulation = populationCreated.getAmountFemaleMice();
        int amountMaleMiceInPopulation = populationCreated.getAmountMaleMice();
        int amountSterileMaleMiceInPopulation = populationCreated.getAmountSterileMaleMice();
        int amountPolygamousMaleMiceInPopulation = populationCreated.getAmountPolygamousMaleMice();
        int amountNormalMaleMiceInPopulation = populationCreated.getAmountNormalMaleMice();
        int amountSterileFemaleMiceInPopulation = populationCreated.getAmountSterileFemaleMice();
        int amountFertileWithMutationFemaleMiceInPopulation = populationCreated.getAmountFemaleMiceWithOneMutation();
        int amountNormalFemaleMiceInPopulation = amountFemaleMiceInPopulation - (amountFertileWithMutationFemaleMiceInPopulation + amountSterileFemaleMiceInPopulation);

        float percentageMales = Math.round(((float)amountMaleMiceInPopulation * 100) * 10 / amountMiceInPopulation)/10.0f;
        float percentageFemales = Math.round(((float)amountFemaleMiceInPopulation * 100) * 10 / amountMiceInPopulation)/10.0f;
        System.out.println("\nThe total number of mice of cycle number " + numberCycle + " is: " + amountMiceInPopulation);
        if(amountFemaleMiceInPopulation > 0) {
            float percentageSterileFemales = Math.round(((float)amountSterileFemaleMiceInPopulation * 100) * 10 / amountFemaleMiceInPopulation)/10.0f;
            float percentageFertileFemalesWithMutation = Math.round(((float)amountFertileWithMutationFemaleMiceInPopulation * 100) * 10 / amountFemaleMiceInPopulation)/10.0f;
            float percentageNormalFemales = Math.round(((float)amountNormalFemaleMiceInPopulation * 100) * 10 / amountFemaleMiceInPopulation) / 10.0f;
            System.out.println("The percentage of female mice is " + percentageFemales + "%, of which: \n- " + percentageSterileFemales + "% are sterile. \n- " + percentageFertileFemalesWithMutation +
                    "% present the mutation but are fertile. \n- " + percentageNormalFemales + "% don't present any mutation.");
        }
        if (amountMaleMiceInPopulation > 0) {
            float percentageSterileMales = Math.round(((float)amountSterileMaleMiceInPopulation * 100) * 10 / amountMaleMiceInPopulation)/10.0f;
            float percentagePolygamousMales = Math.round(((float)amountPolygamousMaleMiceInPopulation * 100) * 10 / amountMaleMiceInPopulation)/10.0f;
            float percentageNormalMales = Math.round(((float)amountNormalMaleMiceInPopulation * 100) * 10 / amountMaleMiceInPopulation)/10.0f;
            System.out.println("The percentage of male mice is " + percentageMales + "%, of which: \n- " + percentageSterileMales + "% are sterile. \n- " + percentagePolygamousMales +
                    "% have the mutation of polygamy. \n- " + percentageNormalMales + "% don't present any mutation.");
        }
    }
}