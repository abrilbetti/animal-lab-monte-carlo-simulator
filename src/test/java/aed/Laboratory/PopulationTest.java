package aed.Laboratory;

import aed.Exceptions.PopulationExceptions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {
    @Test
    void constructor631ProcreationDays(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 631));
    }
    @Test
    void constructor0ProcreationDays(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 0));
    }
    @Test
    void constructorNegativeProcreationDays(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", -100));
    }
    @Test
    void constructor630ProcreationDays(){
        try {
            Population p1 = new Population("Population 1", "John", 630);
            int expectedProcreationDays = 630;
            assertEquals(expectedProcreationDays, p1.getDaysProcreation());
        } catch(PopulationExceptions pe) {
            System.out.println("ERROR: " + pe);
        }
    }
    @Test
    void constructorMultipleOf45ProcreationDays(){
        try {
            Population p1 = new Population("Population 1", "John", 450);
            int expectedProcreationDays = 450;
            assertEquals(expectedProcreationDays, p1.getDaysProcreation());
        } catch(PopulationExceptions pe) {
            System.out.println("ERROR: " + pe);
        }
    }
    @Test
    void constructorNonMultipleOf45ProcreationDays(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 449));
    }
    @Test
    void constructorResponsibleNameNumbers(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John123", 90));
    }
    @Test
    void constructorResponsibleNameEmpty(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", " ", 90));
    }
    //public Population(String namePopulation, String nameResponsible, int daysProcreation, int numberOfMice, int percentageMales, int percentageSterileMales, int percentagePolygamousMales, int percentageFemales, int percentageXmutInFemales) throws PopulationExceptions {
    @Test
    void constructorAmountMice0(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 0, 10, 50, 40, 10, 70));
    }
    @Test
    void constructorInvalidAmountMice(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, -10, 10, 50, 40, 10, 70));
    }
    @Test
    void constructorZeroPercentageMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 0, 50, 40, 10, 70));
    }
    @Test
    void constructorBiggerPercentageMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 100, 50, 40, 10, 70));
    }
    @Test
    void constructorZeroPercentageFemales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 40, 0, 70));
    }
    @Test
    void constructorBiggerPercentageFemales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 40, 100, 70));
    }
    @Test
    void constructorPercentageMalesAndFemalesInvalid(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 50, 50, 40, 60, 70));
    }
    @Test
    void constructorZeroPercentageSterileMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 0, 40, 10, 70));
    }
    @Test
    void constructorBiggerPercentageSterileMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 100, 40, 10, 70));
    }
    @Test
    void constructorZeroPercentagePolygamousMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 0, 10, 70));
    }
    @Test
    void constructorBiggerPercentagePolygamousMales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 100, 10, 70));
    }
    @Test
    void constructorZeroPercentageXmutFemales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 40, 10, 0));
    }
    @Test
    void constructorBiggerPercentageXmutFemales(){
        assertThrows(PopulationExceptions.class, () -> new Population("Population 1", "John", 45, 10, 10, 50, 40, 50, 100));
    }
    @Test
    void addMiceToListCorrect(){
        Population p1 = new Population("Population 1", "John", 90);
        Mouse mouse = new Mouse(Chromosome.X, Chromosome.Y, LocalDate.now());
        p1.addMiceToList(mouse);
        List<Mouse> miceInPopulation1 = p1.getCopyOfMiceInPopulation();
        int amountMiceInPopulation = miceInPopulation1.size();
        int expectedResult = 1;
        assertEquals(expectedResult, amountMiceInPopulation);
    }
    @Test
    void addMiceToListUnsuccessfully(){
        Population p1 = new Population("Population 1", "John", 90);
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        Mouse mouse = null;
        assertThrows(PopulationExceptions.class, () -> {p1.addMiceToList(mouse);});
    }
    @Test
    void identifyMouseExistingRefCode(){
        try {
            Population p1 = new Population("Population 1", "John", 90);
            LocalDate date1 = LocalDate.of(2020, 1, 1);
            LocalDate date2 = LocalDate.of(2020, 1, 2);
            LocalDate date3 = LocalDate.of(2020, 1, 3);
            Mouse mouse7 = new Mouse(date1, 350, Chromosome.X, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
            Mouse mouse8 = new Mouse(date2, 50, Chromosome.Xmut, Chromosome.X, Gender.FEMALE, 37.1f, "Bad mouse");
            Mouse mouse9 = new Mouse(date3, 150, Chromosome.Xmut, Chromosome.Ymut, Gender.MALE, 35.1f, "Fat mouse");
            p1.addMiceToList(mouse7);
            p1.addMiceToList(mouse8);
            p1.addMiceToList(mouse9);
            Mouse mouse4 = p1.identifyMouse(mouse8.getRefCode());
            assertEquals(mouse8, mouse4);
        } catch(PopulationExceptions pe) {
            System.out.println("ERROR: " + pe);
        }
    }
   /* @Test
    void identifyMouseNonExistingRefCode(){
        try {
            Population p1 = new Population("Population 1", "John", 90);
            LocalDate date1 = LocalDate.of(2020, 3, 1);
            LocalDate date2 = LocalDate.of(2020, 3, 2);
            LocalDate date3 = LocalDate.of(2020, 3, 3);
            Mouse mouse10 = new Mouse(date1,350, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
            Mouse mouse11 = new Mouse(date2, 50, Chromosome.Xmut, Chromosome.X, Gender.FEMALE, 37.1f, "Bad mouse");
            Mouse mouse12 = new Mouse(date3, 150, Chromosome.Xmut, Chromosome.Ymut, Gender.MALE, 35.1f, "Fat mouse");
            p1.addMiceToList(mouse10);
            p1.addMiceToList(mouse11);
            p1.addMiceToList(mouse12);
            assertThrows(PopulationExceptions.class, () -> p1.identifyMouse(150));
        } catch(PopulationExceptions pe){
            System.out.println("ERROR: " + pe);
        }
    }*/
    @Test
    void deleteMouseSuccessfully(){
        Population p1 = new Population("Population 1", "John", 90);
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        Mouse mouse = new Mouse(date1, 360, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
        p1.addMiceToList(mouse);
        p1.deleteMouse(mouse.getRefCode());
        int expectedValue = 0;
        assertEquals(expectedValue, p1.amountOfMiceInPopulation());
    }
   /* @Test
    void deleteMouseNotFound(){
        Population p1 = new Population("Population 1", "John", 90);
        assertThrows(PopulationExceptions.class, () -> {p1.deleteMouse(300);});
    }*/
    @Test
    void isListOfMiceEmptyFalse(){
        Population p1 = new Population("Population 1", "John", 90);
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        Mouse mouse = new Mouse(date1, 350, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
        p1.addMiceToList(mouse);
        boolean expectedResult = false;
        assertEquals(expectedResult, p1.isListOfMiceEmpty());
    }
    @Test
    void isListOfMiceEmptyTrue(){
        Population p1 = new Population("Population 1", "John", 90);
        boolean expectedResult = true;
        assertEquals(expectedResult, p1.isListOfMiceEmpty());
    }
    @Test
    void amountOfMiceInPopulation(){
        Population p1 = new Population("Population 1", "John", 90);
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        Mouse mouse = new Mouse(date1, 350, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
        p1.addMiceToList(mouse);
        int expectedValue = 1;
        assertEquals(expectedValue, p1.amountOfMiceInPopulation());
    }
    @Test
    void amountOfMiceInEmptyPopulation(){
        Population p1 = new Population("Population 1", "John", 90);
        int expectedValue = 0;
        assertEquals(expectedValue, p1.amountOfMiceInPopulation());
    }
    @Test
    void fillVirtualPopulation(){
        Population p1 = new Population("Population 1", "John", 45, 10, 50, 50, 40, 50, 70);
        p1.fillVirtualPopulation(10, 50, 50, 50, 40, 70);
        int expectedAmountOfMice = p1.amountOfMiceInPopulation();
        assertEquals(expectedAmountOfMice, 10);
    }
    @Test
    void mouseIsInPopulation(){
        Population p1 = new Population("Population 1", "John", 45);
        Mouse mouse = new Mouse(LocalDate.now(), 332, Chromosome.X, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
        Mouse mouse1 = new Mouse(LocalDate.now(), 223, Chromosome.X, Chromosome.Y, Gender.MALE, 43.4f, "Mouse");
        p1.addMiceToList(mouse);
        p1.addMiceToList(mouse1);
        boolean result = p1.isMouseInPopulation(mouse);
        assertEquals(result, true);
    }
    @Test
    void mouseIsNotInPopulation(){
        Population p1 = new Population("Population 1", "John", 45);
        Mouse mouse = new Mouse(LocalDate.now(), 332, Chromosome.X, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");
        Mouse mouse1 = new Mouse(LocalDate.now(), 223, Chromosome.X, Chromosome.Y, Gender.MALE, 43.4f, "Mouse");
        p1.addMiceToList(mouse);
        boolean result = p1.isMouseInPopulation(mouse1);
        assertEquals(result, false);
    }
    @Test
    void assignMiceToFamiliesRemoveOldMice(){
        Population p1 = new Population("Population 1", "John", 45);
        // Create a family with a mother and a father older than 2.5 years
        Mouse mother = new Mouse(LocalDate.of(2020, 01, 01), 300, Chromosome.Xmut, Chromosome.X, Gender.FEMALE, 37.0f, "Old Mother");
        Mouse father = new Mouse(LocalDate.of(2020, 01, 01), 290, Chromosome.X, Chromosome.Y, Gender.MALE, 39.0f, "Old Father");
        p1.assignMiceToFamilies();
        assertFalse(p1.isMouseInPopulation(mother));
        assertFalse(p1.isMouseInPopulation(father));
    }
    @Test
    void assignMiceToFamiliesRemoveWhenMothersIsEmpty(){
        Population p1 = new Population("Population 1", "John", 45);
        // Create a family with a mother and a father older than 2.5 years
        Mouse mother = new Mouse(LocalDate.of(2020, 01, 01), 300, Chromosome.Xmut, Chromosome.X, Gender.FEMALE, 37.0f, "Old Mother");
        Mouse father = new Mouse(LocalDate.of(2024, 01, 01), 290, Chromosome.X, Chromosome.Y, Gender.MALE, 39.0f, "Old Father");
        p1.assignMiceToFamilies();
        assertFalse(p1.isMouseInPopulation(mother));
    }
    @Test
    void assignMiceToFamiliesRemoveWhenFatherIsDead(){
        Population p1 = new Population("Population 1", "John", 45);
        // Create a family with a mother and a father older than 2.5 years
        Mouse mother = new Mouse(LocalDate.of(2024, 01, 01), 300, Chromosome.Xmut, Chromosome.X, Gender.FEMALE, 37.0f, "Old Mother");
        Mouse father = new Mouse(LocalDate.of(2020, 01, 01), 290, Chromosome.X, Chromosome.Y, Gender.MALE, 39.0f, "Old Father");
        p1.assignMiceToFamilies();
        assertFalse(p1.isMouseInPopulation(father));
    }
}