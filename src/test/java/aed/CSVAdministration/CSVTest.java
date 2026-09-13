package aed.CSVAdministration;

import aed.Exceptions.CSVExceptions;
import aed.Exceptions.InputExceptions;
import aed.Laboratory.Chromosome;
import aed.Laboratory.Gender;
import aed.Laboratory.Mouse;
import aed.Laboratory.Population;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    @Test
    void testOpenPopulationFromFileCorrectly() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/openFileCorrectly.CSV";
        Population expectedPopulationToBeCreated = new Population("Population1", "Abril", 90);
        LocalDate date1 = LocalDate.of(2020, 2, 14);
        LocalDate date2 = LocalDate.of(2024, 8, 30);
        LocalDate date3 = LocalDate.of(2025, 1, 4);
        Mouse mouse1 = new Mouse(date1, 234, Chromosome.X, Chromosome.Y, Gender.MALE, 24, "Old mouse");
        Mouse mouse2 = new Mouse(date2, 321, Chromosome.Xmut, Chromosome.Xmut, Gender.FEMALE, 27, "sterile female mouse");
        Mouse mouse3 = new Mouse(date3, 245, Chromosome.Xmut, Chromosome.Ymut, Gender.MALE, 33, "sterile and polygamous male mouse");
        expectedPopulationToBeCreated.addMiceToList(mouse1);
        expectedPopulationToBeCreated.addMiceToList(mouse2);
        expectedPopulationToBeCreated.addMiceToList(mouse3);

        Population opnedPopulation = CSV.openPopulationFromFile(filePath);
        assertEquals(expectedPopulationToBeCreated, opnedPopulation);
    }
    @Test
    void testSaveNullPopulation() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        assertThrows(InputExceptions.class, () -> {CSV.savePopulation(null, filePath, true,true);});
    }
    @Test
    void testSavePopulationNoFileOpened() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        Population opnedPopulation = CSV.openPopulationFromFile(filePath);
        assertThrows(CSVExceptions.class, () -> {CSV.savePopulation(opnedPopulation, filePath, false,true);});
    }
    @Test
    void testSavePopulationNotSavedAs() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        Population opnedPopulation = CSV.openPopulationFromFile(filePath);
        assertThrows(CSVExceptions.class, () -> {CSV.savePopulation(opnedPopulation, filePath, true,false);});
    }
    @Test
    void testSaveNullPopulationAs() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        assertThrows(InputExceptions.class, () -> {CSV.savePopulationAs(null, filePath, true,false);});
    }
    @Test
    void testSavePopulationAsNoFileOpened() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        Population opnedPopulation = CSV.openPopulationFromFile(filePath);
        assertThrows(CSVExceptions.class, () -> {CSV.savePopulationAs(opnedPopulation, filePath, false,false);});
    }
    @Test
    void testSavePopulationAsAlreadySavedAs() {
        String filePath = "/Users/abrilbetti/Desktop/Algoritmos/Programas/saveFile.CSV";
        Population opnedPopulation = CSV.openPopulationFromFile(filePath);
        assertThrows(CSVExceptions.class, () -> {CSV.savePopulationAs(opnedPopulation, filePath, true,true);});
    }
}