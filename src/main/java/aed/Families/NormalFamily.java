package aed.Families;

import aed.Exceptions.FamilyExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Mouse;

import java.time.LocalDate;
import java.util.Random;
import java.util.TreeSet;

public class NormalFamily extends Family {
    /**
     * Constructor of the class Family, in which an instance of Mouse and a set of Mouse objects are set as the father and the mother/mothers
     * of the family.
     *
     * @param fatherMouse object of Mouse whose gender must be 'MALE' in order to be established as the father of the family.
     * @param mothersMice set of Mouse objects whose genders must be 'FEMALE' in order to be established as the mother/mothers of the family.
     * @throws FamilyExceptions if the gender of the father or any of the mothers is not the appropriate one.
     */
    public NormalFamily(Mouse fatherMouse, TreeSet<Mouse> mothersMice) throws FamilyExceptions {
        super(fatherMouse, mothersMice);
        if(fatherMouse.isSterile()){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.NO_FERTILE_MALE_ASSIGNED);
        }
        if(fatherMouse.isPolygamous()){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.MUTATION_MALE_HAS_POLYGAMY);
        }
    }

    /**
     * Overridden method reproduce, that generates the virtual baby mice and adds them to the list that contains the offspring of the family.
     * @return the tree set that stores the virtual offspring of the family.
     */
    @Override
    public TreeSet<Mouse> reproduce(int daysPassed) {
       TreeSet<Mouse> breedingMice = new TreeSet<>();
        Chromosome babyMouseChromosome1;
        Chromosome babyMouseChromosome2;
        //I get the female mouse stored in the position 'j' of the set of mothers.
        Mouse biologicalMother = retrieveMouseFromMothersSet(0);
        //I create a variable tha will store the amount of baby mice that the couple produces and set it to 0.
        int babies = 0;
        //I will change the value of the 'babies' variable by calling the appropriate method only if the female mouse is non-sterile.
        if(!biologicalMother.isSterile()) {
            babies = amountOfBabyMice();
        }
        for(int i = 0; i < babies; i++) {
            //The baby mouse inherits the first chromosome from its mother and its second one from the father.
            babyMouseChromosome1 = inheritChromosome(biologicalMother);
            babyMouseChromosome2 = inheritChromosome(fatherMouse);
            //The birthdate of the virtual offspring will be the present date plus the amount of days that have passed (according to our simulation) minus 25 days because the
            //cycle's duration is 45 days but during its last 25 days the mice get independent (i.e. when a cycle finishes, its offspring are 25 days old)
            Mouse babyMouse = new Mouse(babyMouseChromosome1, babyMouseChromosome2, LocalDate.now().plusDays(daysPassed - 25));
            //I add the created baby mouse to the set of the offspring.
            breedingMice.add(babyMouse);
        }
        return breedingMice;
    }

    /**
     * Overridden method that calculates the amount of offspring the family will produce by generating a random number.
     * @return an integer which represents the amount of baby mice to be born.
     */
    @Override
    public int amountOfBabyMice() {
        Random rand = new Random();
        //I generate a random number from 0 to 99 and use it in order to determine the amount of offspring the family will have.
        int randNum = rand.nextInt(100);
        if(randNum < 5){
            return 2;
        } else if(randNum >= 5 && randNum < 15){
            return 3;
        } else if(randNum >= 15 && randNum < 30){
            return 4;
        } else if(randNum >= 30 && randNum < 50){
            return 5;
        } else if(randNum >= 50 && randNum < 70){
            return 6;
        } else if(randNum >= 70 && randNum < 85){
            return 7;
        } else if(randNum >= 85 && randNum < 95){
            return 8;
        } else { //randNum >= 95 && randNum <= 99
            return 9;
        }
    }
}