package aed.Laboratory;

import aed.Exceptions.MouseExceptions;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/**
 * Mouse class defines the "blueprint" that its objects, the mice that this program uses, will have. The class is composed of 9
 * attributes that define the class' state and several methods that define its behaviour.
 * @author abrilbetti
 */
public class Mouse implements Comparable<Mouse> {

    private final int refCode;
    private final LocalDate birthDate;
    private int weightGr;
    private final Gender gender;
    private float temperatureInC;
    private String description;
    private final Chromosome chromosome1;
    private final Chromosome chromosome2;
    private static int numMouse = 0; //Variable that counts how many mouses we have in the array of the population

    /**
     * Constructor of the mouse class, which receives as parameter a value per each attribute of the class which are used in order to initialise them. The constructor checks that the values of certain variables received as parameters
     * are valid before initialising the attributes of the object.
     * @param _birthDate variable of type local date which stores the date in which the mouse was born.
     * @param _weightGr integer variable that stores the weight in grams of the mouse.
     * @param _chromosome1 variable of type chromosome (enumerate) which stores X or Xmut (value of the first chromosome of the mouse).
     * @param _chromosome2 variable of type chromosome (enumerate) which stores X, Xmut, Y or Ymut (value of the second chromosome of the mouse, which is the one that determines the gender of the mouse).
     * @param _gender variable of type gender (enumerate) which stores 'MALE' or 'FEMALE' (value of the gender of the mouse).
     * @param _temperature float variable that stores the temperature of the mouse in Celsius degrees.
     * @param _description string variable that stores a description of the mouse.
     * @param _refCode integer value that stores the reference code of the mouse, which is unique of every mouse.
     * @throws MouseExceptions if any of the values stored in the received variables does not fulfill the required conditions in order to be appropriate to determine the state of the mouse instances, such as an invalid
     * value for the chromosomes or the gender or a future or invalid birthdate.
     */
    public Mouse(LocalDate _birthDate, int _weightGr, Chromosome _chromosome1, Chromosome _chromosome2, Gender _gender, float _temperature, String _description, int _refCode) throws MouseExceptions{
        if(_chromosome1!= Chromosome.X && _chromosome1!= Chromosome.Xmut){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_1);
        }
        if(_chromosome2!=Chromosome.Xmut && _chromosome2!= Chromosome.X && _chromosome2!= Chromosome.Ymut && _chromosome2!= Chromosome.Y){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_2);
        }
        if(!isDateValid(_birthDate)){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_BIRTHDAY);
        }
        if(!Gender.isGenderConcordant(_chromosome2, _gender)){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.NON_CONCORDANT_GENDER);
        }
        this.birthDate = _birthDate;
        setWeightGr(_weightGr);
        setTemperatureInC(_temperature);
        this.description = _description;
        this.chromosome1 = _chromosome1;
        this.chromosome2 = _chromosome2;
        this.gender = _gender;
        this.refCode = _refCode;
    }
    /**
     * Overloaded constructor for the class 'Mouse'. This constructor receives one variable in order to initialise the mouse's attributes by calling the
     * previous constructor. The difference is that this constructor does not receive a reference code as parameter.
     * @param _birthday local date type of variable that stores the date in which the mouse was born.
     * @param _weightGr integer variable that stores the weight of the mouse.
     * @param _chromosome1 chromosome type of variable (enumerate) that stores the value of the first chromosome (either X or Xmut).
     * @param _chromosome2 chromosome type of variable (enumerate) that stores the value of the second and gender determining chromosome (X, Xmut, Y or Ymut).
     * @param _gender gender type of variable (enumerate) that stores the value of the gender of the mouse (either MALE or FEMALE).
     * @param _temperatureInC float variable that stores the value of the temperature in Celsius degrees of a mouse.
     * @param _description string variable that stores a description of the mouse.
     * @throws MouseExceptions an exception is rethrown if the called constructor throws one.
     */
    public Mouse(LocalDate _birthday, int _weightGr, Chromosome _chromosome1, Chromosome _chromosome2, Gender _gender, float _temperatureInC, String _description)throws MouseExceptions{
        this(_birthday, _weightGr, _chromosome1, _chromosome2, _gender, _temperatureInC, _description, generateRefCode());
    }

    /**
     * Constructor of the mouse class that is used to create a virtual population of mice in order to perform the "Montecarlo" simulation.
     * This constructor receives as parameter 2 variables, which are the 2 chromosomes of the mouse. In order to initialise the other attributes of the mice, it generates random
     * numbers and a date within the last 2.5 years.
     * @param chromosome1 stores the value of the first chromosome the mice has according to the random numbers (used in Population) and their relationship with the percentages.
     * @param chromosome2 stores the value of the second chromosome the mice has according to the random numbers (used in Population) and their relationship with the percentages.
     */
    public Mouse(Chromosome chromosome1, Chromosome chromosome2) throws MouseExceptions{
        if(chromosome1!= Chromosome.X && chromosome1!= Chromosome.Xmut){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_1);
        }
        if(chromosome2!=Chromosome.Xmut && chromosome2!= Chromosome.X && chromosome2!= Chromosome.Ymut && chromosome2!= Chromosome.Y){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_2);
        }
        Random random = new Random();
        this.chromosome1 = chromosome1;
        this.chromosome2 = chromosome2;
        this.gender = Gender.assignGenderToVirtualMouse(chromosome2);
        this.refCode = generateRefCode();
        this.birthDate = generateRandomBirthday();
        this.weightGr = random.nextInt(491)+10; //I create a random number between 10 and 500 for the weight of the virtual mouse.
        //I do this by computing a random number between 0 and 490 and shifting the result by 10, so I will get a number between 10 and 500 and not between 0 and 490.
        this.temperatureInC = Math.round((1 + (random.nextFloat()*(45-1)))*10.0f)/10.0f; //I create a random float number between 1 and 45 for the temperature of the mouse.
        /*I do it by multiplying the random float by the range of possible temperatures (which is 44) and shift the result by one in order to obtain a number between 1 and 45 instead of 0 and 44.
        I also round the obtained value multiplied by 10.0f, which is then divided by the same value in order to obtain only 1 digit after the comma.
         */
        this.description = "Mouse " + this.refCode + " weights: " + this.weightGr + " gr.";
    }

    /**
     * Constructor of the mouse class that is used in order to create virtual offspring of the families of mice in order to perform the simulation of the evolution of the mice population.
     * This constructor receives as parameter 3 variables, which are the gender of the mouse and its 2 chromosomes. In order to initialise the other attributes of the mice, it generates random
     * numbers and uses the present date.
     * @param chromosome1 stores the value of the first chromosome the mice has according to the random numbers (used in Population) and their relationship with the percentages.
     * @param chromosome2 stores the value of the second chromosome the mice has according to the random numbers (used in Population) and their relationship with the percentages.
     * @param birthday stores the date in which the baby mice were born (the present date).
     */
    public Mouse(Chromosome chromosome1, Chromosome chromosome2, LocalDate birthday) throws MouseExceptions{
        if(chromosome1!= Chromosome.X && chromosome1!= Chromosome.Xmut){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_1);
        }
        if(chromosome2!=Chromosome.Xmut && chromosome2!= Chromosome.X && chromosome2!= Chromosome.Ymut && chromosome2!= Chromosome.Y){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_CHROMOSOME_2);
        }
        Random random = new Random();
        this.chromosome1 = chromosome1;
        this.chromosome2 = chromosome2;
        this.birthDate = birthday;
        this.gender = Gender.assignGenderToVirtualMouse(chromosome2);
        this.refCode = generateRefCode();
        this.weightGr = random.nextInt(491)+10; //I create a random number between 10 and 500 for the weight of the virtual mouse.
        //I do this by computing a random number between 0 and 490 and shifting the result by 10, so I will get a number between 10 and 500 and not between 0 and 490.
        this.temperatureInC = Math.round((1 + (random.nextFloat()*(45-1)))*10.0f)/10.0f; //I create a random float number between 1 and 45 for the temperature of the mouse.
        /*I do it by multiplying the random float by the range of possible temperatures (which is 44) and shift the result by one in order to obtain a number between 1 and 45 instead of 0 and 44.
        I also round the obtained value multiplied by 10.0f, which is then divided by the same value in order to obtain only 1 digit after the comma.
         */
        this.description = "Mouse " + this.refCode + " weights: " + this.weightGr + " gr.";
    }

    /**
     * Getter for the reference code which makes the attribute accessible from external classes.
     * @return the reference code of the mouse, stored in an integer variable.
     */
    public int getRefCode() {
        return refCode;
    }

    /**
     * Getter for the birthdate of the mouse, in order for it to be accessible from external classes.
     * @return the birthdate of the mouse, which is stored in a variable of type 'LocalDate'.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Getter for the weight of the mouse, in order for it to be accessible from external classes.
     * @return the weight of the mouse, stored in an integer variable.
     */
    public int getWeightGr() {
        return weightGr;
    }

    /**
     * Getter for the gender of the mouse, in order for it to be accessible from external classes.
     * @return a variable of type 'Gender' that stores the gender of the mouse (MALE or FEMALE)
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Getter for the temperature of the mouse, in order for it to be accessible from external classes.
     * @return a variable of type float that stores the temperature in Celsius degrees of the mouse.
     */
    public float getTemperatureInC() {
        return temperatureInC;
    }

    /**
     * Getter for the description of the mouse, in order for it to be accessible from external classes.
     * @return a string variable that stores the description of the mouse.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the first chromosome of the mouse, in order for it to be accessible from external classes.
     * @return a variable of type 'Chromosome' that stores the first chromosome of the mouse (X or Xmut)
     */
    public Chromosome getChromosome1() {
        return chromosome1;
    }

    /**
     * Getter for the second chromosome of the mouse, in order for it to be accessible from external classes.
     * @return a variable of type 'Chromosome' that stores the second chromosome of the mouse (X, Xmut, Y or Ymut)
     */
    public Chromosome getChromosome2() {
        return chromosome2;
    }

    /**
     * Setter in order to set a value for the weight of the mouse. In oder for the value to be set, it needs to be greater
     * than 10 but lower than 500. If it does not fulfill this requirement, an exception is thrown preventing the value to be set.
     * @param weightGr variable of integer type that stores the new value for the weight attribute.
     * @throws MouseExceptions an exception of type 'invalid weight' is thrown if we try to set the attribute to an invalid value.
     */
    public void setWeightGr(int weightGr) throws MouseExceptions{
        if(weightGr < 10 || weightGr > 500){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_WEIGHT);
        }
        this.weightGr = weightGr;
    }

    /**
     * Setter for the temperature of the mouse. In order for the value of the parameter to be set, it needs to be bigger than 1 and
     * smaller than 45. If the value does not fulfill this requirement, an exception is thrown, preventing the value to be set.
     * @param temperatureInC variable of floating type that stores the new value for the temperature in Celsius degrees.
     * @throws MouseExceptions an exceptions of type 'invalid temperature' is thrown if the value received as parameter is invalid.
     */
    public void setTemperatureInC(float temperatureInC) throws MouseExceptions{
        if(temperatureInC < 1 || temperatureInC > 45){
            throw new MouseExceptions(MouseExceptions.ErrorTypeMouse.INVALID_TEMPERATURE);
        }
        //The temperature of the mouse must be between [1,45] in order to be valid
        this.temperatureInC = temperatureInC;
    }

    /**
     * Setter for the description of the mouse.
     * @param description variable of string type that stores the new description of the mouse.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method that generates a unique reference code for each mouse. It does it increasingly.
     * @return the generated reference code.
     */
    public static int generateRefCode(){
        numMouse++;
        return numMouse;
    }

    /**
     * Method that checks whether the birthdate introduced by the user is valid (a past or the current date)
     * @param birthDate date introduced by the user
     * @return true if the date is valid and false if it is not
     */
    private boolean isDateValid(LocalDate birthDate){
        LocalDate now = LocalDate.now();
        //If the birthday is in the future it is not valid and a false will be returned.
        return !birthDate.isAfter(now);
    }

    /**
     * Method tht receives as parameters the new values of the mouse and calls the setter in order to change the data of the mouses.
     * @param weight new value of weight.
     * @param temp new value of the temperature of the mouse.
     * @param description new description of the mouse.
     * @throws MouseExceptions rethrows the exception thrown in the called setters of Mouse (if they were to throw one because the values are invalid).
     */
    public void changeMouseData(int weight, float temp, String description)throws MouseExceptions {
        //I call the setters and pass them the new values for their attributes
        setWeightGr(weight);
        setTemperatureInC(temp);
        setDescription(description);
        /*The user can only modify the weight, temperature and description of a mouse because the other attributes (chromosomes, gender, birthday
        and reference code) are final and, therefore, cannot be modified once initialised */
    }

    /**
     * Method that checks whether a mouse is sterile or not based on the gender of the mouse and its chromosomes
     * @return true if the mouse is sterile and false otherwise
     */
    public boolean isSterile(){
        if(this.gender == Gender.FEMALE){
            if(chromosome1 == Chromosome.Xmut && chromosome2 == Chromosome.Xmut){
                return true; //the female mouse is sterile
            }
        }
        else if(this.gender == Gender.MALE){
            if(chromosome1 == Chromosome.Xmut){
                return true; //the male mouse is sterile
            }
        }
        return false;
    }

    /**
     * Method that checks whether a mouse is polygamous or not.
     * @return true if the mouse is polygamous and false if the mouse is not.
     */
    public boolean isPolygamous(){
        if(this. gender == Gender.MALE && chromosome2 == Chromosome.Ymut){
            return true; //The male mouse is polygamous
        }
        return false;
    }

    /**
     * Method that sets the birthday of the instance to a random date that ranges from the present to 2.5 years in the past.
     */
    private LocalDate generateRandomBirthday(){
        //I declare 2 local dates that will store the maximum and minimum dates that a virtual mouse can be born in.
        LocalDate youngerDate = LocalDate.now();
        LocalDate eldestDate = youngerDate.minusDays((long) (2.5 * 365));
        //I compute the total amount of days between the eldest and younger dates in which the mice can be born.
        long daysBetween = (youngerDate.getYear()-eldestDate.getYear())*365 + (youngerDate.getDayOfYear()-eldestDate.getDayOfYear());
        Random rand = new Random();
        //I generate the random number between 0 and the amount of days between the two dates
        long randomNumberOfDays = rand.nextInt((int) daysBetween + 1);

        return eldestDate.plusDays(randomNumberOfDays);
    }

    /**
     * Method that checks if a mouse is sexually mature or not by analysing whether 70 days have or not passed since the birth of the mouse and the "present" date according to the simulation. The
     * number 70 comes from the condition that states that mice can reproduce after 2 cycles; i.e. after 45 days and the 25 days it takes the mice to
     * get independent (days that belong to the cycle in which the mice are born)
     * @param daysPassedSimulation integer variable that stores the amount of days that have passed since in the simulation, which indicates how many cycles of reproduction have been carried on.
     * @return true if the mouse is sexually mature or false otherwise.
     */
    protected boolean isSexuallyMature(int daysPassedSimulation){
        //I am checking that the mouse was born 2 cycles ago (actually, 70 days before the present date). If this is
        // (45 + 25 days ago (1 cycle and the 25 days it took them to get independent during the cycle in which they were born)
        LocalDate dateOfSexualMaturity = this.getBirthDate().plusDays(70);
        LocalDate simulatedPresentDate = LocalDate.now().plusDays(daysPassedSimulation);
        if(simulatedPresentDate.isAfter(dateOfSexualMaturity)){
            return true;
        }
        return false;
    }

    /**
     * Overridden equals method that defines the conditions under which two mouse objects will be considered as equals. This is, only when their reference codes are the same.
     * @param o Mouse instance to be compared.
     * @return true if the reference codes of the 2 mice are equal, or false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return refCode == mouse.refCode;
    }

    /**
     * Overridden hashCode method that generates a hash code for every mouse depending on their reference codes.
     * @return the hash code of the mouse with reference code "refCode".
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(refCode);
    }

    /** Overridden compareTo method that
     * @param mouse1 instance of Mouse class that we wish to compare.
     * @return 0 if the objects, according to the equals method, are equal or the difference of the two reference codes of the mice if they are not equal.
     */
    @Override
    public int compareTo(Mouse mouse1) {
        if(this.equals(mouse1)){
            return 0;
        }
        else{
            return this.refCode - mouse1.refCode;
        }
    }

    /**
     * Overridden to string method used to state how mouse objects are outputted.
     * @return the output for the attributes of the mouse class
     */
    @Override
    public String toString() {
        return "Mouse {" +
                "Reference Code = " + refCode +
                "; Birthdate = " + birthDate +
                "; Weight in gr = " + weightGr +
                "; Gender = " + gender +
                "; Temperature in C = " + temperatureInC +
                "; Description = '" + description + "'"+
                "; Chromosome 1 = " + chromosome1 +
                "; Chromosome 2 = " + chromosome2 +
                '}';
    }
}