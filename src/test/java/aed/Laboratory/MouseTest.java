package aed.Laboratory;

import aed.Exceptions.MouseExceptions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class MouseTest {

    @Test
    void constructorFutureDate(){
        LocalDate date = LocalDate.of(2025, 12, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructorNegativeWeight(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, -400, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructorWeight9(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 9, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructorWeight501(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 501, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructorWeight10(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        try {
            Mouse mouse1 = new Mouse(date, 10, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse");
            int weightMouse1 = 10;
            assertEquals(weightMouse1, mouse1.getWeightGr());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void constructorWeight500(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        try {
            Mouse mouse1 = new Mouse(date, 500, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse");
            int weightMouse1 = 500;
            assertEquals(weightMouse1, mouse1.getWeightGr());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void constructorNegativeTemperature(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, Chromosome.X, Chromosome.Y, Gender.MALE, -36.8f, "Nice mouse"));
    }
    @Test
    void constructorTemperature0(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, Chromosome.X, Chromosome.Y, Gender.MALE, 0f, "Nice mouse"));
    }
    @Test
    void constructorTemperature451(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, Chromosome.X, Chromosome.Y, Gender.MALE, 45.1f, "Nice mouse"));
    }
    @Test
    void constructorTemperature1(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        try {
            Mouse mouse1 = new Mouse(date, 10, Chromosome.X, Chromosome.Y, Gender.MALE, 1f, "Nice mouse");
            float temperatureMouse1 = 1f;
            assertEquals(temperatureMouse1, mouse1.getTemperatureInC());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void constructorTemperature45(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        try {
            Mouse mouse1 = new Mouse(date, 10, Chromosome.X, Chromosome.Y, Gender.MALE, 45f, "Nice mouse");
            float temperatureMouse1 = 45f;
            assertEquals(temperatureMouse1, mouse1.getTemperatureInC());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void constructorChromosome1Y(){
        LocalDate date = LocalDate.of(2020, 10, 3);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, Chromosome.Y, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructorInvalidChromosome(){
        LocalDate date = LocalDate.of(2020, 10, 3);
        String introducedString = "Ymut"; //either Y, Ymut or anything else
        Chromosome chromosome1 = Chromosome.valueOf(introducedString);
        assertThrows(MouseExceptions.class, () -> new Mouse(date, 400, chromosome1, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse"));
    }
    @Test
    void constructor2Chromosome1Y(){
        assertThrows(MouseExceptions.class, () -> new Mouse(Chromosome.Y, Chromosome.Y));
    }
    @Test
    void constructor2InvalidChromosome(){
        String introducedString = "Ymut"; //either Y, Ymut or anything else
        Chromosome chromosome1 = Chromosome.valueOf(introducedString);
        assertThrows(MouseExceptions.class, () -> new Mouse(chromosome1, Chromosome.Y));
    }
    @Test
    void constructor3Chromosome1Y(){
        LocalDate date = LocalDate.now();
        assertThrows(MouseExceptions.class, () -> new Mouse(Chromosome.Y, Chromosome.Y, date));
    }
    @Test
    void constructor3InvalidChromosome(){
        LocalDate date = LocalDate.now();

        String introducedString = "Ymut"; //either Y, Ymut or anything else
        Chromosome chromosome1 = Chromosome.valueOf(introducedString);
        assertThrows(MouseExceptions.class, () -> new Mouse(chromosome1, Chromosome.Y, date));
    }
    @Test
    void changeDataOfExistingMouse(){
        try {
            LocalDate date1 = LocalDate.of(2020, 1, 1);
            Mouse mouse1 = new Mouse(date1,350, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 39.1f, "Good mouse");

            mouse1.changeMouseData(456, 34.5f, "Crazy mouse");
            int actualWeight = mouse1.getWeightGr();
            int expectedWeight = 456;
            assertEquals(expectedWeight, actualWeight);
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void isNotSterile(){
        try {
            LocalDate date = LocalDate.of(2020, 10, 3);
            Mouse mouse1 = new Mouse(date, 350, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse");
            boolean expectedValue = false;
            assertEquals(expectedValue, mouse1.isSterile());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void isSterile(){
        try {
            LocalDate date = LocalDate.of(2020, 10, 3);
            Mouse mouse1 = new Mouse(date, 350, Chromosome.Xmut, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse");
            boolean expectedValue = true;
            assertEquals(expectedValue, mouse1.isSterile());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void isNotPolygamous(){
        try {
            LocalDate date = LocalDate.of(2020, 10, 3);
            Mouse mouse1 = new Mouse(date, 350, Chromosome.X, Chromosome.Y, Gender.MALE, 36.8f, "Nice mouse");
            boolean expectedValue = false;
            assertEquals(expectedValue, mouse1.isPolygamous());
        }catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void isPolygamous(){
        try {
            LocalDate date = LocalDate.of(2020, 10, 3);
            Mouse mouse1 = new Mouse(date, 350, Chromosome.X, Chromosome.Ymut, Gender.MALE, 36.8f, "Nice mouse");
            boolean expectedValue = true;
            assertEquals(expectedValue, mouse1.isPolygamous());
        } catch(MouseExceptions me) {
            System.out.println("ERROR: " + me);
        }
    }
    @Test
    void isSexuallyMatureTrue(){
        LocalDate date = LocalDate.of(2024, 10, 3);
        Mouse mouse = new Mouse(Chromosome.X, Chromosome.Y, date);
        boolean expectedValue = true;
        boolean result = mouse.isSexuallyMature(90);
        assertEquals(expectedValue, result);
    }
    @Test
    void isSexuallyMatureFalse(){
        LocalDate date = LocalDate.of(2025, 02, 10);
        Mouse mouse = new Mouse(Chromosome.X, Chromosome.Y, date);
        boolean expectedValue = false;
        boolean result = mouse.isSexuallyMature(45);
        assertEquals(expectedValue, result);
    }
}