package service;

import model.Order;

import java.io.FileWriter;
import java.io.IOException;


public class DataPersistence {
    private static int orderNumber = 0;

    public synchronized void writeOrderToFile(OrderProcessor orderProcessor, Cart cart) {
        orderNumber++;
        try (FileWriter writer = new FileWriter("zamowienie_" + orderNumber + ".txt")) {
            writer.write("Zamówienie numer: " + orderNumber + "\n");
            writer.write(orderProcessor.getOrderedProductsInformation(cart));
        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z zamówienem");
        }
    }

    public synchronized void writeUserToFile(OrderProcessor orderProcessor, Order order) {
        try (FileWriter writer = new FileWriter("uzytkownik_" + orderNumber + ".txt")) {
            writer.write("Użytkownik numer: " + orderNumber + "\n");
            writer.write(orderProcessor.getBuyerInformation(order));

        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z użytkownikiem");
        }
    }
}
