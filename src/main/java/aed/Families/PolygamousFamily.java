package aed.Families;

import aed.Exceptions.FamilyExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Mouse;

import java.time.LocalDate;
import java.util.Random;
import java.util.TreeSet;

public class PolygamousFamily extends Family {

    /**
     * Constructor of the class Family, in which an instance of Mouse and a set of Mouse objects are set as the father and the mother/mothers
     * of the family.
     *
     * @param fatherMouse object of Mouse whose gender must be 'MALE' in order to be established as the father of the family.
     * @param mothersMice set of Mouse objects whose genders must be 'FEMALE' in order to be established as the mother/mothers of the family.
     * @throws FamilyExceptions is the gender of the father or any of the mothers is not the appropriate one.
     */
    public PolygamousFamily(Mouse fatherMouse, TreeSet<Mouse> mothersMice) throws FamilyExceptions {
        super(fatherMouse, mothersMice);
        if(fatherMouse.isSterile()){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.MUTATION_MALE_HAS_STERILITY);
        }
        if(!fatherMouse.isPolygamous()){
            throw new FamilyExceptions(FamilyExceptions.ErrorTypeFamily.MUTATION_MALE_LACKS_POLYGAMY);
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
        int babies = 0;
        //Nested 'for' loops that generate the offspring each female produces.
        for(int j=0; j<mothersMice.size(); j++) {
            //I get the female mouse stored in the position 'j' of the set of mothers.
            Mouse biologicalMother = retrieveMouseFromMothersSet(j);
            //I call the method to compute the amount of babies this female will produce only if it is non-sterile. If it is sterile, the amount of babies will be 0 and the following for will be skipped
            if(!biologicalMother.isSterile()) {
                //Each female mouse can have a different amount of offspring, which is why the value of the variable 'baby' is changed every time.
                babies = amountOfBabyMice();
            }
            for (int i = 0; i < babies; i++) {
                //The baby mouse inherits its first chromosome from the mother and the second one from its father.
                babyMouseChromosome1 = inheritChromosome(biologicalMother);
                babyMouseChromosome2 = inheritChromosome(fatherMouse);
                //I create the mice, passing as parameter the inherited chromosomes and the appropriate birthdate.
                //The birthdate of the virtual offspring will be the present date plus the amount of days that have passed (according to our simulation) minus 25 days because the
                //cycle's duration is 45 days but during its last 25 days the mice get independent (i.e. when a cycle finishes, its offspring are 25 days old)
                Mouse babyMouse = new Mouse(babyMouseChromosome1, babyMouseChromosome2, LocalDate.now().plusDays(daysPassed - 25));
                breedingMice.add(babyMouse);
            }
        }
        return breedingMice;
    }

    /**
     * Overridden method that calculates the amount of offspring the family will produce by generating a random number. In addition, it is
     * checked that the biological father is not null (i.e. that a fertile mouse was assigned to this family)
     * @return an integer which represents the amount of baby mice to be born.
     */
    @Override
    public int amountOfBabyMice() {
        Random rand = new Random();
        //I generate a random number from 0 to 99 and use it in order to determine the amount of offspring the family will have.
        int randNum = rand.nextInt(100);
        if(randNum < 10){
            return 2;
        } else if(randNum >= 10 && randNum < 25){
            return 3;
        } else if(randNum >= 25 && randNum < 45){
            return 4;
        } else if(randNum >= 45 && randNum < 60){
            return 5;
        } else if(randNum >= 60 && randNum < 75){
            return 6;
        } else if(randNum >= 75 && randNum < 95){
            return 7;
        } else {
            return 8;
        }
    }
}