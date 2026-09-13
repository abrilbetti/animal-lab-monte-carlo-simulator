package aed.Laboratory;

/**
 * Enum that contains the different values that the gender field of instances of the mouse class can take.
 * @author abrilbetti
 */
public enum Gender {
    MALE, FEMALE;

    /**
     * Method that checks whether the introduced gender and chromosomes are concordant between one another
     * @param chromosome2 chromosome of the mouse that we want to analyse in order to determine what is the appropriate gender for the mouse.
     * @param gender introduced gender, which we will see if it is concordant with the expected gender.
     * @return true if the gender is concordant
     */
    public static boolean isGenderConcordant(Chromosome chromosome2, Gender gender){
        if(chromosome2 == Chromosome.X || chromosome2 == Chromosome.Xmut){
            return gender == Gender.FEMALE;
        }
        else if(chromosome2 == Chromosome.Y || chromosome2 == Chromosome.Ymut){
            return gender == Gender.MALE;
        }
        return false;
    }
    /**
     * Method that determines the gender of the virtual mouse by analysing its second chromosome.
     * @param chromosome2 chromosome of the mouse that we want to analyse in order to assign the appropriate gender to the virtual mouse.
     * @return the gender.
     */
    public static Gender assignGenderToVirtualMouse(Chromosome chromosome2){
        if(chromosome2 == Chromosome.X || chromosome2 == Chromosome.Xmut){
            return Gender.FEMALE;
        } else{
            return Gender.MALE;
        }
    }
}