package aed.Laboratory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenderTest {
    @Test
    void genderIsConcordant(){
            boolean expectedValue = true;
            boolean realValue = Gender.isGenderConcordant(Chromosome.Y, Gender.MALE);
            assertEquals(expectedValue, realValue);
    }
    @Test
    void genderIsNotConcordant(){
        boolean expectedValue = false;
        boolean realValue = Gender.isGenderConcordant(Chromosome.Y, Gender.FEMALE);
        assertEquals(expectedValue, realValue);
    }
    @Test
    void assignGenderToMaleVirtualMouse(){
        Gender realGender = Gender.assignGenderToVirtualMouse(Chromosome.Y);
        Gender expectedGender = Gender.MALE;
        assertEquals(expectedGender, realGender);
    }
    @Test
    void assignGenderToMutatedMaleVirtualMouse(){
        Gender realGender = Gender.assignGenderToVirtualMouse(Chromosome.Ymut);
        Gender expectedGender = Gender.MALE;
        assertEquals(expectedGender, realGender);
    }
    @Test
    void assignGenderToFemaleVirtualMouse(){
        Gender realGender = Gender.assignGenderToVirtualMouse(Chromosome.X);
        Gender expectedGender = Gender.FEMALE;
        assertEquals(expectedGender, realGender);
    }
    @Test
    void assignGenderToMutatedFemaleVirtualMouse(){
        Gender realGender = Gender.assignGenderToVirtualMouse(Chromosome.Xmut);
        Gender expectedGender = Gender.FEMALE;
        assertEquals(expectedGender, realGender);
    }
}