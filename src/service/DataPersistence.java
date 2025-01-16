package service;

import model.Order;

import java.io.FileWriter;
import java.io.IOException;

/**
 * klasa obsługuje zapis szczegółów dotyczżcych zamówienia i użytkownika do osobnch plików testowych
 */
public class DataPersistence {
    private static int orderNumber = 0;

    /**
     * Metoda obsluguje zapis szczególów dotyczacych zamówienie do pliku tekstowego
     */
    public synchronized void writeOrderToFile(OrderProcessor orderProcessor, Cart cart) {
        orderNumber++;
        try (FileWriter writer = new FileWriter("zamowienie_" + orderNumber + ".txt")) {
            writer.write("Zamówienie numer: " + orderNumber + "\n");
            writer.write(orderProcessor.getOrderedProductsInformation(cart));
        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z zamówienem");
        }
    }

    /**
     * Metoda obsluguje zapis szczególów dotyczacych uzytkownika i adresu wysylki do pliku tekstowego
     */
    public synchronized void writeUserToFile(OrderProcessor orderProcessor, Order order) {
        try (FileWriter writer = new FileWriter("uzytkownik_" + orderNumber + ".txt")) {
            writer.write("Użytkownik numer: " + orderNumber + "\n");
            writer.write(orderProcessor.getBuyerInformation(order));

        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z użytkownikiem");
        }
    }
}
