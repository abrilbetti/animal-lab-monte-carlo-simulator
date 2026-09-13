package aed.Laboratory;

import aed.Exceptions.FamilyExceptions;
import aed.Exceptions.MouseExceptions;
import aed.Exceptions.PopulationExceptions;
import aed.Families.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Population class defines the "blueprint" that its objects (populations) will have in this program. The class is composed of 5
 * attributes that define the class' state and several methods that define its behaviour.
 * @author abrilbetti
 */
public class Population{
    private String namePopulation;
    private String nameResponsible;
    private int daysProcreation;
    private int daysProcreationPassed = 0;
    private List<Mouse> miceInPoupulation = new ArrayList<>(); //I create an array list of mice
    private List<Mouse> femaleMiceInPopulation = new ArrayList<>();
    private List<Mouse> maleMiceInPopulation = new ArrayList<>();
    private List<Mouse> sexuallyImmatureMice = new ArrayList<>();
    private TreeSet<Family> setOfFamilies = new TreeSet<>();


    //Constructor of Population class
    /**
     * Constructor of the population class, which receives as parameter a value per each attribute of the class which are used in order to initialise them.
     * The constructor checks that the values of certain variables received as parameters (such as the days of procreation) are valid before initialising the
     * attributes of the object.
     * @param namePopulation string variable that stores the name of the population.
     * @param nameResponsible string variable that stores the name of the responsible for the population.
     * @param daysProcreation integer variable that stores the amount of days a population can spend in a laboratory in order to procreate.
     * @throws PopulationExceptions an exception is rethrown if any of the called methods throws one
     */
    public Population(String namePopulation, String nameResponsible, int daysProcreation) throws PopulationExceptions {
        setNamePopulation(namePopulation);
        setNameResponsible(nameResponsible);
        setDaysProcreation(daysProcreation);
    }

    /**
     * Constructor of the population class, which is used in order to create a virtual population. The constructor receives as parameter the name of the population and its responsible, the days the population will spend in the laboratory and many percentages which describe
     * the composition of the virtual population.
     * @param namePopulation string that stores the introduced name of the population.
     * @param nameResponsible string that stores the introduced name of the responsible for the population.
     * @param daysProcreation int that stores the amount of days that the population will spend in the laboratory in order to procreate.
     * @param numberOfMice integer that stores the total amount of mice that the population has to have.
     * @param percentageMales integer that stores the percentage of male mice that the population has to have.
     * @param percentageSterileMales integer that stores the percentage of sterile male mice that the population has to have.
     * @param percentagePolygamousMales integer that stores the percentage of polygamous male mice that the population has to have.
     * @param percentageFemales integer that stores the percentage of female mice that the population has to have.
     * @param percentageXmutInFemales integer that stores the percentage of mutated X chromosomes that there have to be among the female mice of the population.
     * @throws PopulationExceptions is any of the percentages or the total number of mice is invalid. It also rethrows the exceptions the setters throw, if any.
     */
    public Population(String namePopulation, String nameResponsible, int daysProcreation, int numberOfMice, int percentageMales, int percentageSterileMales, int percentagePolygamousMales, int percentageFemales, int percentageXmutInFemales) throws PopulationExceptions {
        setNamePopulation(namePopulation);
        setNameResponsible(nameResponsible);
        setDaysProcreation(daysProcreation);
        if(numberOfMice <= 0)
        {
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_NUMBER_OF_MICE);
        }
        if(percentageMales <= 0 || percentageMales >= 100)
        {
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PERCENTAGE_MALES);
        }
        if(percentageFemales <= 0 || percentageFemales >= 100){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PERCENTAGE_FEMALES);
        }
        if(percentagePolygamousMales < 0 || percentagePolygamousMales >= 100){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PERCENTAGE_POLYGAMOUS_MALES);
        }
        if(percentageSterileMales < 0 || percentageSterileMales >= 100){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PERCENTAGE_STERILE_MALES);
        }
        if(percentageXmutInFemales < 0 || percentageXmutInFemales >= 100){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PERCENTAGE_XMUT);
        }
        if(percentageMales + percentageFemales != 100){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_MALE_FEMALE_PERCENTAGES);
        }
    }

    //Getters
    /**
     * Getter for the name of the population.
     * @return a string variable that stores the name of the population.
     */
    public String getNamePopulation() {
        return namePopulation;
    }

    /**
     * Getter for the days the population will be in the lab.
     * @return an integer variable that stores the amount of days the population will spend in the lab.
     */
    public int getDaysProcreation() {
        return daysProcreation;
    }

    /**
     * Getter for the name of the responsible for the population.
     * @return a string variable that stores the name of the responsible for the population.
     */
    public String getNameResponsible() {
        return nameResponsible;
    }

    /**
     * Getter of a copy of the array list that stores the mice present in the population.
     * @return the copy of the array list that contains the mice of the population.
     */
    public List<Mouse> getCopyOfMiceInPopulation() {
        //I create the copy of the array list that contains the mice of the population
        ArrayList<Mouse> copiedListOfMiceInPopulation = new ArrayList<>();
        //I add the objects stored in the original list of mice in the population to the copy list
        copiedListOfMiceInPopulation.addAll(miceInPoupulation);
        //I return the copy of the array list in order tp avoid violating the encapsulation principle of the original array list that contains the mcie
        return copiedListOfMiceInPopulation;
    }

    /**
     * Method that returns the amount of female mice in the population by going over the list that stores all the mice in the population and counting how many of them are female.
     * @return the counter that is incremented each time a female mouse is found in the list.
     */
    public int getAmountFemaleMice(){
        int counterFemales = 0;
        for(int i = 0; i < miceInPoupulation.size(); i++){
            if (miceInPoupulation.get(i).getGender() == Gender.FEMALE){
                counterFemales++;
            }
        }
        return counterFemales;
    }
    /**
     * Method that returns the amount of male mice in the population by going over the list that stores all the mice in the population and counting how many of them are male.
     * @return the counter that is incremented each time a male mouse is found in the list.
     */
    public int getAmountMaleMice(){
        int counterMales = 0;
        for(int i = 0; i < miceInPoupulation.size(); i++){
            if (miceInPoupulation.get(i).getGender() == Gender.MALE){
                counterMales++;
            }
        }
        return counterMales;    }
    /**
     * Method that returns the amount of sterile male mice in the population by going over the list that stores the mice of the population and counting how many of them are male and sterile.
     * @return an integer that represents how many sterile mice there are among the male population.
     */
    public int getAmountSterileMaleMice(){
        int counterSterileMales = 0;
        for(int i=0; i<miceInPoupulation.size(); i++){
            if(miceInPoupulation.get(i).getGender() == Gender.MALE && miceInPoupulation.get(i).isSterile()){
                counterSterileMales++;
            }
        }
        return counterSterileMales;
    }
    /**
     * Method that returns the amount of polygamous male mice in the population by going over the list that stores the mice and counting how many of them are polygamous and male.
     * @return an integer that represents how many polygamous mice there are among the male population.
     */
    public int getAmountPolygamousMaleMice(){
        int counterPolygamousMales = 0;
        for(int i=0; i<miceInPoupulation.size(); i++){
            if(miceInPoupulation.get(i).isPolygamous()){
                counterPolygamousMales++;
            }
        }
        return counterPolygamousMales;
    }
    /**
     * Method that returns the amount of normal male mice in the population by going over the list that stores the mice of the population and counting how many of them are non-sterile, not polygamous and male.
     * @return an integer that represents how many normal mice there are among the male population.
     */
    public int getAmountNormalMaleMice(){
        int counterNormalMales = 0;
        for(int i=0; i<miceInPoupulation.size(); i++){
            if(miceInPoupulation.get(i).getGender() == Gender.MALE && !miceInPoupulation.get(i).isPolygamous() && !miceInPoupulation.get(i).isSterile()){
                counterNormalMales++;
            }
        }
        return counterNormalMales;
    }

    /**
     * Method that returns the amount of sterile female mice in the population by going over the list that stores the mice of the population and counting how many of them are sterile and female.
     * @return an integer that represents how many sterile mice there are among the female population.
     */
    public int getAmountSterileFemaleMice(){
        int counterSterileFemales = 0;
        for(int i=0; i<miceInPoupulation.size(); i++){
            if(miceInPoupulation.get(i).getGender() == Gender.FEMALE && miceInPoupulation.get(i).isSterile()){
                counterSterileFemales++;
            }
        }
        return counterSterileFemales;
    }
    /**
     * Method that returns the amount of fertile female (that have one mutated chromosome) mice in the population by going over the list that stores the mice of the population and counting how many of them fulfill this.
     * @return an integer that represents how many fertile mice that have one mutation there are among the female population.
     */
    public int getAmountFemaleMiceWithOneMutation(){
        int counterXmutFemales = 0;
        for(int i=0; i<miceInPoupulation.size(); i++){
            int counterMutations = 0;
            if(miceInPoupulation.get(i).getGender() == Gender.FEMALE && miceInPoupulation.get(i).getChromosome1() == Chromosome.Xmut){
                counterMutations++;
            } else if(miceInPoupulation.get(i).getChromosome2() == Chromosome.Xmut){
                counterMutations++;
            }
            if(counterMutations == 1){
                counterXmutFemales++;
            }
        }
        return counterXmutFemales;
    }


    //Setters for the name of the population and its responsible, and the days of procreation of the population.
    /**
     * Setter for the name of the responsible. In order for the value stored in the variable received as a parameter to be set as the name of
     * the responsible, it needs to be composed only by letters. If it includes numbers or other invalid characters, an exception is thrown.
     * @param nameResponsible variable that stores the string we want to set as the number of the responsible.
     * @throws PopulationExceptions an exception of type "invalid name" is thrown if the string received as parameter is not only composed of letters.
     */
    public void setNameResponsible(String nameResponsible) throws PopulationExceptions {
        //I check that the string introduced by the user is composed only by letters (upper or lower case)
        // ^ and $ state that this applies to all the string introduced by the user (these symbols mark the beginning and end of the string)
        //The + indicates that the patter must match at least one character (in this way, empty strings will be rejected too)
        if(nameResponsible.matches("^[a-zA-Z]+$")) {
            this.nameResponsible = nameResponsible;
        }
        else{
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_NAME);
        }
    }

    /**
     * Setter for the name of the population.
     * @param namePopulation variable of string type that stores the new name of the population.
     */
    public void setNamePopulation(String namePopulation) {
        this.namePopulation = namePopulation;
    }

    /**
     * Setter for the days in the lab that a population will spend. In order to set a value to this attribute, it needs to fulfill certain conditions such as:
     * being greater than 0, lower than 630 and a multiple of 45.
     * @param daysProcreation integer variable that stores the value we want to set as the 'daysProcreation'.
     * @throws PopulationExceptions an exception of type 'invalid procreation days' or 'non-multiple of 45 procreation days'
     * is thrown if the value we want to set for this attribute does not meet the aforementioned requirements.
     */
    public void setDaysProcreation(int daysProcreation) throws PopulationExceptions {
        //I create an if-else in order to check that the days of procreation value introduced by the user is among the valid range of numbers (days)
        if(daysProcreation > 0 && daysProcreation < 631 && daysProcreation%45==0){
            this.daysProcreation = daysProcreation;
        }
        //If the value is not between the expected range or is not a number, then I throw an exception of type "invalid procreation days"
        else if(daysProcreation <= 0 || daysProcreation > 630){

            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PROCREATION_DAYS);
        }
        //If the introduced number is not a multiple of 45 then I throw the appropriate type of exception.
        else if(daysProcreation%45 != 0){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.NON_MULTIPLE_45_PROCREATION_DAYS);
        }
        //In any other case, I throw an exception that states that the data type introduced is invalid (not an int)
        else{
            throw new PopulationExceptions(PopulationExceptions.ErrorType.INVALID_PROCREATION_DAYS);
        }
    }

    /**
     * Method that adds the mouse (passed as parameter) to the population by adding it to the appropriate lists, which is determined by the gender of the mouse.
     * @param mouse instance of Mouse which represents a virtual mouse which was created in the 'fillVirtualPopulation' method.
     * @throws PopulationExceptions if the mouse we are trying to add is equal to null.
     */
    public void addMiceToList(Mouse mouse) throws PopulationExceptions {
        if(mouse == null){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.FAILED_TO_ADD_MOUSE);
        }
        if(!isMouseInPopulation(mouse)) {
            //I add the created mice to the array list that contains them all (regardless of their sex) only if it is not present in it.
            miceInPoupulation.add(mouse);
        }
        //I add the created mouse to the appropriate list depending on their gender
        if (mouse.getGender() == Gender.MALE) {
            maleMiceInPopulation.add(mouse);
        }
        if (mouse.getGender() == Gender.FEMALE) {
            femaleMiceInPopulation.add(mouse);
        }
    }

    /**
     * Method that runs the ArrayList and looks for the mouse with the reference code it receives as parameter. If it is found it returns
     * the respective mouse and, if it is not, it throws an exception. This method implements binary search.
     * @param refCode of the mouse the user wants to look for.
     * @return the mouse if the reference code is associates with one.
     * @throws PopulationExceptions if a mouse with the reference code is not found.
     */
    public Mouse identifyMouse(int refCode) throws PopulationExceptions{
        //I define 3 integer variables: start, end (the size of the list of mice) and middle.
        int start = 0;
        int end = miceInPoupulation.size();
        int middle = (start + end) / 2;
        //While the start number is smaller than the 'end' one and the reference code of the mouse stored in the position 'middle' of the list is not the one we are looking for, we continue the search.
        while((start <= end) && miceInPoupulation.get(middle).getRefCode() != refCode){
            //If the reference code we are looking for is smaller than that of the mouse stored in position 'middle', we set the 'end' to one position before the middle.
            if(refCode < miceInPoupulation.get(middle).getRefCode()){
                end = middle - 1;
            } else if(refCode > miceInPoupulation.get(middle).getRefCode()){
                //I set the 'start' position to one position after the middle if the reference code we are looking for is not smaller than the one stored in the 'middle' position.
                start = middle + 1;
            }
            //I update the value of 'middle' to the middle number between the new end and start.
            middle = (start + end) / 2;
        }
        //I check that the reference code is equal to that stored in the 'middle' position and, if it is, I return the mouse stored in that position.
        if(refCode == miceInPoupulation.get(middle).getRefCode()){
            return miceInPoupulation.get(middle);
        }
        //If the mouse was not found (because it doesn't exist a mouse with the introduced reference code), an exception is thrown.
        throw new PopulationExceptions(PopulationExceptions.ErrorType.MOUSE_NOT_FOUND);
    }

    /**
     * Method that removes a mouse from the population by removing it from the dynamic list. This method receives a reference code, calls the method
     * that identifies the mouse whose reference code is the one provided by the user and eliminates the mouse from the list of mice of the population.
     * @param refCode integer that stores the reference code of the mouse that the user wishes to delete.
     * @throws PopulationExceptions if the mouse is not found and the method 'identifyMouse' throws an exception of this type; then this method rethrows the exception.
     * Also, if the mouse is found but not removed from the population, an exception is thrown.
     */
    public void deleteMouse(int refCode) throws PopulationExceptions{
        Mouse mouseToRemove = identifyMouse(refCode);
        boolean removed = miceInPoupulation.remove(mouseToRemove);
        //If the mouse was found but not removed from the population (when removed is false), I throw an exception
        if(!removed){
            throw new PopulationExceptions(PopulationExceptions.ErrorType.FAILED_TO_REMOVE_MOUSE);
        }
    }

    /**
     * Method that prints all the information about the mouse that has the reference code that was introduced as parameter.
     * @param refCode of the mouse whose information is displayed.
     * @return all the information of the mouse.
     * @throws PopulationExceptions if an exception is thrown in the identify mouse method.
     */
    public String seeMouseInformation(int refCode) throws PopulationExceptions{
        //I call the method identify mouse in order to look for the mouse whose reference code is the one the user introduced
        Mouse mouse = identifyMouse(refCode);
        return "Reference code: " + mouse.getRefCode() + "\nBirthday: " + mouse.getBirthDate() + "\nWeight: " + mouse.getWeightGr()
                + "\nGender: " + mouse.getGender() + "\nTemperature: " + mouse.getTemperatureInC() + "\nDescription: " + mouse.getDescription()
                + "\nIs polygamous: " + mouse.isPolygamous() + "\nIs sterile: " + mouse.isSterile();
    }

    /**
     * Method that checks whether the array list that contains the mice of the population is empty or not.
     * @return a boolean which is true when the list is empty and false when it is not.
     */
    public boolean isListOfMiceEmpty(){
        return miceInPoupulation.isEmpty();
    }

    /**
     * Method that returns the size of the array list that stores the mice tht were added to the population.
     * @return the length of the list that contains the mice of the population, which is dynamic and therefore coincides with the amount of mice the population has.
     */
    public int amountOfMiceInPopulation(){
        return miceInPoupulation.size();
    }

    /**
     * Method that receives the total number of mice in the population and the percentages and computes how many mice we will have in the population with each condition, generates random numbers which
     * are analysed in order to obtain the gender and chromosomes of the virtual mice (making sure that we are creating the accurate amount of each 'type' of mouse (male and sterile, male and polygamous, female, etc.)
     * Finally, the method calls another method which adds the mice to the appropriate array list.
     * @param totalNumberOfMice integer that stores the introduced value of the total amount of mice in the population.
     * @param percentageFemale integer that stores the percentage of female mice that the population has.
     * @param percentageMale integer that stores the percentage of male mice that the population has.
     * @param percentageSterileMales integer that stores the percentage of sterile male mice that the population has.
     * @param percentagePolygamousMales integer that stores the percentage of polygamous male mice that the population has.
     * @param percentageXmutInFemales integer that stores the percentage of mutated X chromosomes among the female mice of the population.
     * @throws MouseExceptions if an exception is thrown when we call the constructor of Mouse and the object is created (this method rethrows it)
     * @throws PopulationExceptions is the method 'addMiceToList' throws an exception, then this method rethrows it.
     */
    public void fillVirtualPopulation(int totalNumberOfMice, int percentageFemale, int percentageMale, int percentageSterileMales, int percentagePolygamousMales, int percentageXmutInFemales) throws MouseExceptions, PopulationExceptions{
        //I compute the amount of mice with each condition present in the population
        int amountOfFemaleMice = totalNumberOfMice * percentageFemale / 100;
        int amountOfMaleMice = totalNumberOfMice * percentageMale / 100;
        int amountOfSterileMales = amountOfMaleMice * percentageSterileMales / 100;
        int amountOfPolygamousMales = amountOfMaleMice * percentagePolygamousMales / 100;
        int amountXmutInFemales = (amountOfFemaleMice * 2 * percentageXmutInFemales) / 100;
        //I create as many counter as I need to make sure that I am creating the appropriate amount of mice depending on the percentages
        int counterCreatedMales = 0;
        int counterCreatedFemales = 0;
        int counterCreatedSterileMales = 0;
        int counterCreatedPolygamousMales = 0;
        int counterUsedXmutInFemales = 0;
        int counterNonSterileMales = 0;
        int counterNonPolygamousMales = 0;
        int counterUsedXInFemales = 0;
        //I create an instance of the Random class, which I need in order to generate 3 random numbers between 0 and 99
        Random random1 = new Random();
        boolean keepGeneratingRandom1;
        boolean keepGeneratingRandom2;
        boolean keepGeneratingRandom3;

        //For that runs as many times as mice the population will have
        for(int i = 0; i<totalNumberOfMice; i++) {
            //I create chromosomes that will store certain values depending on the random numbers I generated
            Chromosome chromosome1 = null;
            Chromosome chromosome2 = null;
            //I generate 3 random numbers between 0 and 99
            int randNum1 = random1.nextInt(100); //I am generating 3 random numbers between 0 and 99
            int randNum2 = random1.nextInt(100);
            int randNum3 = random1.nextInt(100);
            keepGeneratingRandom1 = true;
            keepGeneratingRandom2 = true;
            keepGeneratingRandom3 = true;

            //I analyse the random numbers I got, increasing the appropriate counters and initialising the gender and chromosomes accordingly.
            /*If the first random number is smaller than the percentage of male mice and the amount of male mice created is smaller than the
            total amount of male mice that the percentage represents, we enter the 'if' and create another male mouse.
             */
            //While loop that runs until we have created either a female or male mouse.
            while(keepGeneratingRandom1) {
                if (randNum1 < percentageMale && counterCreatedMales < amountOfMaleMice) {
                    //The mouse will be a male; I increase by one the counter of male mice already created.
                    counterCreatedMales++;
                    keepGeneratingRandom1 = false;
                    //I create a while loop that will iterate until a value is assigned to the first chromosome of the mouse.
                    while (keepGeneratingRandom2) {
                        /*If the second random number is smaller than the percentage of sterile male mice in the population and if the amount of sterile male mice
                        I have already created is smaller than the total amount of sterile male mice I need to have in my population, we enter the 'if' and set the
                        first chromosome of the mouse as mutated (Xmut) in order for it to be sterile.
                        */
                        if (randNum2 < percentageSterileMales && counterCreatedSterileMales < amountOfSterileMales) {
                            //I set the first chromosome of the mouse as mutated (Xmut) and add one to the counter of sterile males created
                            chromosome1 = Chromosome.Xmut;
                            counterCreatedSterileMales++;
                            keepGeneratingRandom2 = false; //I set it to false in order to exit the while
                        }
                        /*If the second random number is equal or greater than the percentage of sterile male mice, or if we have reached the amount of sterile male mice
                        we had to build, we create non-sterile male mice. In addition, I check that I'm not creating more non-sterile male mice than I should as the consequences
                        would involve having less sterile male mice than asked. Because of that, I check that the created amount of non-sterile males is smaller than the wanted amount
                        sterile males (which is equal to the total amount of male mice minus the wanted amount of sterile males)*/
                        else if (randNum2 >= percentageSterileMales && counterNonSterileMales < amountOfMaleMice - amountOfSterileMales) {
                            //The mouse will not be sterile. chromosome1 = X
                            chromosome1 = Chromosome.X;
                            counterNonSterileMales++;
                            keepGeneratingRandom2 = false;
                        }
                        /*If the type of male mice I should build according to the value of the random number has reached the limit (for instance we've already built the wanted amount
                        of sterile males) I should build a non-sterile male. However, this won't happen because the random number does not meet the condition of the 'if'; therefore, I
                        created the while and the else in which the random number's value is updated.*/
                        else {
                            //I update the value of the random number and keep trying to assign values to the chromosomes of the male mouse
                            randNum2 = random1.nextInt(100);
                        }
                    }
                    /*If the third random number is smaller than the percentage of polygamous male mice in the population and if the amount of polygamous male mice
                    I have already created is smaller than the total amount of polygamous male mice I need to have in my population, we enter the 'if' and set the
                    second chromosome of the mouse as mutated (Ymut) in order for it to have a tendency of polygamy.
                     */
                    while (keepGeneratingRandom3) {
                        if (randNum3 < percentagePolygamousMales && counterCreatedPolygamousMales < amountOfPolygamousMales) {
                            //The mouse is polygamous. The male chromosome is mutated (Ymut)
                            chromosome2 = Chromosome.Ymut;
                            //I add one to the counter of polygamous male mice created
                            counterCreatedPolygamousMales++;
                            keepGeneratingRandom3 = false;
                        }
                        /*If the third random number is equal or greater than the percentage of polygamous male mice, or if we have reached the amount of polygamous male mice
                        we had to build, we create non-polygamous male mice.*/
                        else if (randNum3 >= percentagePolygamousMales && counterNonPolygamousMales < amountOfMaleMice - amountOfPolygamousMales) {
                            //The mouse is not polygamous. chromosome2 = Y
                            chromosome2 = Chromosome.Y;
                            counterNonPolygamousMales++;
                            keepGeneratingRandom3 = false;
                        } else {
                            //I update the value of the third random number
                            randNum3 = random1.nextInt(100);
                        }
                    }
                }
                /*If the first random number is equal or greater than the percentage of males, the mice will be female. I create the female mice only if I have
                created less than the total amount of females I have to create according to the percentage of female mice.*/
                else if (randNum1 >= percentageMale && counterCreatedFemales < amountOfFemaleMice) {
                    //The mouse will be female. I add one to the counter of created female mice.
                    counterCreatedFemales++;
                    keepGeneratingRandom1 = false;
                    while (keepGeneratingRandom2) {
                        /*If the second random number is smaller than the percentage of mutated X chromosomes among the female population and if the amount of used Xmut
                        is smaller than the "available" amount of Xmut, we enter the 'if' and set the first chromosome of the mouse as mutated (Xmut).*/
                        if (randNum2 < percentageXmutInFemales && counterUsedXmutInFemales < amountXmutInFemales) {
                            //The first chromosome of the female mouse will be mutated. chromosome1 = Xmut
                            chromosome1 = Chromosome.Xmut;
                            keepGeneratingRandom2 = false;
                            //I increase by one the counter of how many mutated X chromosomes we have assigned to female mice in the created population.
                            counterUsedXmutInFemales++;
                        }
                        /*If the second random number is equal or greater than the percentage of mutated X chromosomes present among the female population, or if we have
                        used all the mutated X chromosomes we had available, we create female mice with normal a normal (X) 'chromosome1'.*/
                        else if (randNum2 >= percentageXmutInFemales && counterUsedXInFemales < (2 * amountOfFemaleMice - amountXmutInFemales)) {
                            //The first chromosome of the female mouse will be normal. chromosome1 = X
                            chromosome1 = Chromosome.X;
                            counterUsedXInFemales++;
                            keepGeneratingRandom2 = false;
                        } else {
                            randNum2 = random1.nextInt(100);
                        }
                    }
                    while (keepGeneratingRandom3) {
                        /*If the third random number is smaller than the percentage of mutated X chromosomes among the female population and if the amount of used Xmut
                        is smaller than the "available" amount of Xmut, we enter the 'if' and set the second chromosome of the mouse as mutated (Xmut).*/
                        if (randNum3 < percentageXmutInFemales && counterUsedXmutInFemales < amountXmutInFemales) {
                            // The second chromosome of the female mouse will be mutated. chromosome2 = Xmut.
                            chromosome2 = Chromosome.Xmut;
                            //I increase by one the counter of how many mutated X chromosomes we have assigned to female mice in the created population.
                            counterUsedXmutInFemales++;
                            keepGeneratingRandom3 = false;
                        }
                        /*If the third random number is equal or greater than the percentage of mutated X chromosomes present among the female population, or if we have
                        used all the mutated X chromosomes we had available, we create female mice with normal a normal (X) 'chromosome2'.*/
                        else if (randNum3 >= percentageXmutInFemales && counterUsedXInFemales < (2 * amountOfFemaleMice - amountXmutInFemales)) {
                            //The second chromosome of the female mouse will be normal. chromosome2 = X.
                            chromosome2 = Chromosome.X;
                            counterUsedXInFemales++;
                            keepGeneratingRandom3 = false;
                        } else {
                            randNum3 = random1.nextInt(100);
                        }
                    }
                }
                else{
                    randNum1 = random1.nextInt(100);
                }
            }
            //I make sure that the chromosomes are not null in order to build appropriate mice.
            if (chromosome1!=null && chromosome2!=null) {
                //I create a mouse instance with the obtained gender and chromosomes
                Mouse mouse = new Mouse(chromosome1, chromosome2);
                addMiceToList(mouse);
            }
        }
    }

    /**
     * Method that checks if a mouse is present in a list, by calling the equals method and going over the list of mice.
     * @param mouse Mouse instance we want to check if it has been added or not to the list.
     * @return true if the mouse is in the list or false otherwise.
     */
    protected boolean isMouseInPopulation(Mouse mouse){
        for(int i=0; i<miceInPoupulation.size(); i++) {
            if(miceInPoupulation.get(i).equals(mouse)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that assigns the mice stored in the array lists of female, male and sexually immature mice in the population to the appropriate families.
     * The appropriate family for each mouse is determined by the method 'createFamily'.
     * @throws FamilyExceptions rethrows exceptions thrown when creating objects of Family in the method 'createFamily'.
     * @throws PopulationExceptions if the method 'addMiceToList' throws an exception, this method rethrows it.
     */
    public void assignMiceToFamilies() throws FamilyExceptions, PopulationExceptions{
        daysProcreationPassed = daysProcreationPassed + 45;
        adjustFamilies();
        adjustMiceLists(maleMiceInPopulation);
        adjustMiceLists(femaleMiceInPopulation);
        //While there are mice left in the lists of female and male mice, I keep assigning them to families.
        while(!maleMiceInPopulation.isEmpty() && !femaleMiceInPopulation.isEmpty()) {
            //I create the father mouse which will be the mouse stored in position 0 of the array list that contains the male mice of the virtual population.
            Mouse fatherMouse = findSexuallyMatureMouse(maleMiceInPopulation);
            Mouse motherMouse = findSexuallyMatureMouse(femaleMiceInPopulation);
            //I create a set that will store 1 or more female mice according to his couple's tendency to polygamy.
            TreeSet<Mouse> mothersMice = new TreeSet<>();
            if (fatherMouse != null && motherMouse != null) {
                //I add the female mouse stored in the position 0 of the list of female mice within the virtual population to the tree set.
                mothersMice.add(motherMouse);
                //I call the method that creates the families.
                createFamily(fatherMouse, mothersMice);
            } else{
                //If the family couldn't be created because either the female or male mouse were null, I add the one that wasn't null to the lists of the population.
                if(fatherMouse != null){
                    addMiceToList(fatherMouse);
                } else if (motherMouse != null) {
                    addMiceToList(motherMouse);
                }
            }
        }
    }

    /**
     * Method that checks that the mice present in the families are younger than 2.5 years. If they are not, I make them die by eliminating them from the lists and removing the families from the
     * set of families, if necessary (if the dead mouse was the father or if there are no females left in the family).
     * @throws PopulationExceptions if the method 'addMiceToList' throws an exception, then this method rethrows it.
     */
    private void adjustFamilies() throws PopulationExceptions{
        //I create a clone of the set of families because inside the for I'm modifying the set of families.
        TreeSet<Family> clonedSetOfFamilies = (TreeSet<Family>) setOfFamilies.clone();
        //For each that runs the cloned set of families.
        for (Family family : clonedSetOfFamilies) {
            //I obtain the mothers, the father and the biological father of the family by calling the getters of Family.
            TreeSet<Mouse> mothers = family.getMothersMice();
            Mouse father = family.getFatherMouse();
            Mouse biologicalFather = family.getBiologicalFather();
            //I store the set of mothers in a list, so I can have direct access to the elements it contains.
            List<Mouse> listOfMothers = new ArrayList<>(mothers);
            //For that runs the list of female mice (mothers of the family)
            for(int i = 0; i < listOfMothers.size(); i++) {
                //If the mouse in position 'i' is not young enough to continue living, I remove it from the list that contains the mice in the population and the list that contains the mothers of the family.
                if (!family.shouldBeAlive(listOfMothers.get(i), daysProcreationPassed)) {
                    miceInPoupulation.remove(listOfMothers.get(i));
                    listOfMothers.remove(listOfMothers.get(i));
                }
            }
            if(listOfMothers.isEmpty()) {
                if(family.shouldBeAlive(father, daysProcreationPassed)) {
                    addMiceToList(father);
                }
                if(biologicalFather != null && family.shouldBeAlive(biologicalFather, daysProcreationPassed)){
                    addMiceToList(biologicalFather);
                }
                setOfFamilies.remove(family);
            }
            if (!family.shouldBeAlive(father, daysProcreationPassed)) {
                miceInPoupulation.remove(father);
                maleMiceInPopulation.remove(father);
                //If the younger male is null it is because we didn't find a mouse to replace the dead one. Therefore, I run the list of mothers (the ones in there will have already
                //passed the 'youth' check and are young enough to continue living) and call the method that adds them to the list of mice of the population (I re-insert them so they can
                //form another family).
                for(int k=0; k<listOfMothers.size(); k++) {
                    addMiceToList(listOfMothers.get(k));
                }
                //Also, if the biological father is not null and is young enough to continue living, I call the method that re-inserts mice in the mice of the population that can form families.
                if(biologicalFather != null && family.shouldBeAlive(biologicalFather, daysProcreationPassed)) {
                    addMiceToList(biologicalFather);
                }
                //Finally, I remove the family from the set of families.
                setOfFamilies.remove(family);
            }
            if (biologicalFather != null && !family.shouldBeAlive(biologicalFather, daysProcreationPassed)) {
                miceInPoupulation.remove(biologicalFather);
                maleMiceInPopulation.remove(biologicalFather);
                //I set the male mouse as the fertile male of the family. If we did not find a biological father to replace the dead one, the biological father will be null and the family
                //will continue to work. However, the family will produce 0 baby mice as biological fathers are needed in sterile families (if one male is null and the other is sterile,
                //the family lacks a fertile male mouse)
            }
        }
    }

    /**
     * Method that receives as parameter the list whose mice longevity we wish to analyse. If any mouse is older than 2.5 years, I remove
     * it from the list received as parameter and from the general list 'miceInPopulation'.
     * @param listOfMice list whose mice we want to analyse.
     */
    private void adjustMiceLists(List<Mouse> listOfMice){
        if(!listOfMice.isEmpty()) {
            //I create a copy of the receives list in order to use it to iterate (so I can change the original one)
            List<Mouse> copyListOfMice = new ArrayList<>(listOfMice);
            for (Mouse mouse : copyListOfMice){
                LocalDate birthday = mouse.getBirthDate();
                //LocalDate eldestDate = birthday.minusDays((long) (2.5 * 365));
                LocalDate simulatedDate = LocalDate.now().plusDays(daysProcreationPassed);
                LocalDate eldestDate = simulatedDate.minusDays((long) (2.5 * 365));
                if (birthday.isBefore(eldestDate)){
                    listOfMice.remove(mouse);
                    miceInPoupulation.remove(mouse);
                }
            }
        }
    }

    /**
     * Method that receives by parameter a list whose elements are checked to be sexually mature or not. Either way, the element is removed from the list received as parameter
     * and returned or added to another list that stores the immature mice.
     * @param listOfCandidateMice list that contains the mice whose maturity we will check.
     * @return the mouse. If the mouse is sexually mature the returned object will contain the object. If it is not, the returned object will contain null.
     */
    private Mouse findSexuallyMatureMouse(List<Mouse> listOfCandidateMice){
        Mouse mouse = null;
        while(mouse == null && !listOfCandidateMice.isEmpty()) {
            mouse = listOfCandidateMice.get(0);
            listOfCandidateMice.remove(0);
            if (mouse.isSexuallyMature(daysProcreationPassed)) {
                break;
            } else {
                sexuallyImmatureMice.add(mouse);
                mouse = null;
            }
        }
        return mouse;
    }

    /**
     * Method that determines the appropriate family for each male mouse by analysing its sterility or tendency to polygamy.
     * Also, the created families are added to the set of families of the population.
     * @param fatherMouse Mouse object that represents a sexually mature male mouse to be assigned as the father of the appropriate family.
     * @param mothersMice Mouse object that represents a sexually mature female mouse to be assigned as the mother of the appropriate family.
     * @throws FamilyExceptions if an exception is thrown when creating an object of any family class, this method rethrows it.
     */
    protected void createFamily(Mouse fatherMouse, TreeSet<Mouse> mothersMice) throws FamilyExceptions {
        //I create an instance of the Random class which I will use in order to generate random numbers.
        Random rand = new Random();
        Mouse fertileMale = null; //I create another mouse for the cases in which the father of a family is sterile.
        //If the male mouse is sterile, we assign it as the father of the family with a sterile male.
        if (fatherMouse.isSterile() && !fatherMouse.isPolygamous()) {
            //I create a for that goes over the list of male mice in the population looking for a fertile male mouse.
            for(int i=0; i<maleMiceInPopulation.size(); i++){
                //If we find a non-sterile and sexually mature male mouse, we store it in 'fertileMale' and remove it from the list of male mice in the population.
                if(!maleMiceInPopulation.get(i).isSterile() && !maleMiceInPopulation.get(i).isPolygamous() && maleMiceInPopulation.get(i).isSexuallyMature(daysProcreationPassed)){
                    fertileMale = maleMiceInPopulation.get(i);
                    maleMiceInPopulation.remove(fertileMale);
                    break;
                }
            }
            //I call the constructor of the sterile family with a sterile dad, a fertile male and the female and add the family to the set of families.
            SterileMaleFamily family = new SterileMaleFamily(fatherMouse, fertileMale, mothersMice);
            //The 'fertile male' may store a male mouse or null, depending on whether a fertile male has been found or not. If it is null, the family will be created anyway but will produce 0 babies.
            setOfFamilies.add(family);
        }
        //If the male mouse is polygamous, I assign him as the father of the polygamous family.
        else if (fatherMouse.isPolygamous()) {
            //I create an integer variable that stores the random number generated between 0 and 9.
            int keepAddingMothers = rand.nextInt(10);
            //If the random number is greater or equal to 5, we add one more female to the set of mothers of the polygamous family.
            while(keepAddingMothers >= 5) {
                //I call the method that looks for sexually mature mice
                Mouse motherMouseFound = findSexuallyMatureMouse(femaleMiceInPopulation);
                if(motherMouseFound == null){
                    //There are no more sexually mature female mice in the list. Therefore, I stop looking for females by setting 'keepAddingFemales' to a number smaller than 5.
                    keepAddingMothers = 4;
                } else {
                    //A sexually mature female mouse was found. I add it to the set of mothers.
                    mothersMice.add(motherMouseFound);
                    //And I update the random number by re-generating it between 0 and 9.
                    keepAddingMothers = rand.nextInt(10);
                }
            }
            //When I get a random number smaller than 5, I call the constructor of the polygamous family and create it with the father mouse and the set of females.
            if(!fatherMouse.isSterile()){
                //If the father mouse is non-sterile, I create a polygamous family and add it to the set of families of the population.
                PolygamousFamily family = new PolygamousFamily(fatherMouse, mothersMice);
                setOfFamilies.add(family);
            }
            else{
                //If the father mouse is sterile, I create a polygamous family with a sterile father and add it to the set of families of the population.
                //I create a 'for' that goes over the list of male mice in the population looking for a fertile male mouse
                for(int i=0; i<maleMiceInPopulation.size(); i++){
                    //If we find a non-sterile and sexually mature male mouse, we store it in 'fertileMale' and remove it from the list of male mice in the population.
                    if(!maleMiceInPopulation.get(i).isSterile() && maleMiceInPopulation.get(i).isPolygamous() && maleMiceInPopulation.get(i).isSexuallyMature(daysProcreationPassed)){
                        fertileMale = maleMiceInPopulation.get(i);
                        maleMiceInPopulation.remove(fertileMale);
                        //I create the family only if we have found a fertile male.
                        SterilePolygamousMaleFamily family = new SterilePolygamousMaleFamily(fatherMouse, fertileMale, mothersMice);
                        setOfFamilies.add(family);
                        break;
                    }
                }
            }
        }
        else {
            //If the male mouse is neither sterile nor polygamous, I assign him as the father of a normal family.
            //I call the constructor of the normal family and add it to the set of families of the population.
            NormalFamily family = new NormalFamily(fatherMouse, mothersMice);
            setOfFamilies.add(family);
        }
    }

    /**
     * Method that calls the reproduce method of the Family class and adds the offspring to the lists of the population, by calling the method 'addMiceToList'. We pass an integer
     * to the reproduce method which is a variable that stores how many cycles have been simulated (it increases by 45 its value every time this method is implemented).
     */
    public void addBabyMiceToPopulation(){
        for(Family family : setOfFamilies){
            TreeSet<Mouse> offspring = family.reproduce(daysProcreationPassed);
            for(Mouse mouse : offspring){
                addMiceToList(mouse);
            }
        }
        //I add the mice that in the previous cycle were sexually immature to the lists of female and male mice of the population (by calling the appropriate method)
        for(int i = 0 ; i < sexuallyImmatureMice.size() ; i++) {
            addMiceToList(sexuallyImmatureMice.get(i));
        }
        //I remove all the elements from the array list
        sexuallyImmatureMice.clear();
    }

    /**
     * Overridden toString method that establishes how the population objects are outputted.
     * @return the lines to be outputted.
     */
    @Override
    public String toString() {
        return "Population '" + namePopulation+ "'"+
                "\n - Days in lab to procreate = " + daysProcreation +
                "\n - Name of the responsible person = " + nameResponsible;
    }
    /**
     * Overridden equals method that defines the conditions under which two population objects will be considered as equals. This is, only when their names, the names of their responsible person, the
     * amount of days they will spend in the laboratory to procreate, the amount of mice and the amount of each type of mouse the population has, are the same.
     * @param o Population instance to be compared.
     * @return true if all the attributes analysed of the 2 populations are equal, or false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Population that)) return false;
        return daysProcreation == that.daysProcreation && Objects.equals(namePopulation, that.namePopulation) && Objects.equals(nameResponsible, that.nameResponsible) && Objects.equals(this.amountOfMiceInPopulation(), that.amountOfMiceInPopulation())
        && Objects.equals(this.getAmountFemaleMice(), that.getAmountFemaleMice()) && Objects.equals(this.getAmountMaleMice(), that.getAmountMaleMice()) && Objects.equals(this.getAmountNormalMaleMice(), that.getAmountNormalMaleMice())
                && Objects.equals(this.getAmountPolygamousMaleMice(), that.getAmountPolygamousMaleMice()) && Objects.equals(this.getAmountSterileMaleMice(), that.getAmountSterileMaleMice()) && Objects.equals(this.getAmountSterileFemaleMice(), that.getAmountSterileFemaleMice())
                && Objects.equals(this.getAmountFemaleMiceWithOneMutation(), that.getAmountFemaleMiceWithOneMutation());
    }
    /**
     * Overridden hashCode method that generates a hash code for every population depending on its name, the name of its responsible, the amount of days it will spend in the laboratory to procreate, the amount of mice and the amount of each type of mouse the population has.
     * @return the hash code of the population.
     */
    @Override
    public int hashCode() {
        return Objects.hash(namePopulation, nameResponsible, daysProcreation, this.amountOfMiceInPopulation(), this.getAmountFemaleMice(), this.getAmountMaleMice(), this.getAmountNormalMaleMice(), this.getAmountPolygamousMaleMice(), this.getAmountSterileMaleMice(),
                this.getAmountSterileFemaleMice(), this.getAmountFemaleMiceWithOneMutation());
    }
}