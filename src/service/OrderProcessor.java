package service;

import exception.EmptyCartException;
import model.Order;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderProcessor {

    private static int documentNumber = 0;

    /**
     * generuje fakture zawierajaca dane sprzedawcy, dane klienta, szczególy zamówienia, kwote do zaplaty, date wygenerowania faktury oraz termin zaplaty. Faktura jest zapisywana do pliku
     */
    public synchronized void generateFacture(Order order, Cart cart){
        documentNumber++;
        try (FileWriter writer = new FileWriter("faktura_" + documentNumber + ".txt")){
            writer.write("Faktura numer: " + documentNumber + "\n");
            writer.write(getSellerInformation());
            writer.write(getBuyerInformation(order));
            writer.write(getOrderedProductsInformation(cart));
            writer.write("\nDo zapłaty: %s".formatted(order.getRoundedOrderPrice()));
            writer.write(getDateTimeInformation());
            writer.write("\nZamówienie zostanie wysłane w ciągu 7 dni roboczych od zaksięgowania wpłaty");

            System.out.println("Faktura została wygenerowana");

        } catch (IOException e) {
            System.out.println("!Błąd podczas generowania faktury!");
        }
    }

    /**
     * Metoda obsluguje zapis szczególów dotyczacych zamówienie do pliku tekstowego
     */
    public synchronized void writeOrderToFile(Cart cart) {
        try (FileWriter writer = new FileWriter("zamowienie_" + documentNumber + ".txt")) {
            writer.write("Zamówienie numer: " + documentNumber + "\n");
            writer.write(getOrderedProductsInformation(cart));
        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z zamówienem");
        }
    }

    /**
     * Metoda obsluguje zapis szczególów dotyczacych uzytkownika i adresu wysylki do pliku tekstowego
     */
    public synchronized void writeUserToFile(Order order) {
        try (FileWriter writer = new FileWriter("uzytkownik_" + documentNumber + ".txt")) {
            writer.write("Użytkownik numer: " + documentNumber + "\n");
            writer.write(getBuyerInformation(order));

        } catch (IOException e) {
            System.out.println("Błąd generowania pliku z użytkownikiem");
        }
    }

    private String getSellerInformation(){
        return "\nSprzedawca:\n" +
                "Sklep XYZ\n" +
                "ul. Piotrkowska 1, 00-001 Łódź\n" +
                "NIP: 111-222-33-44\n";
    }

    public String getBuyerInformation(Order order){
        return "\nNabywca:\n" +
                order.getFirstName() + " " + order.getLastName() + "\n" +
                order.getDeliveryAddress() + "\n" +
                order.getPhoneNumber() + "\n" +
                order.getEmailAddress() + "\n";
    }

    private String getDateTimeInformation(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ZoneId zoneId = ZoneId.of("Europe/Warsaw");
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return "\n\nData i godzina wystawienia faktury: " + now.format(dateTimeFormatter) + "\n" +
                "Proszę opłacić zamówienie do " + now.plusDays(7).format(dateFormatter);
    }

    public String getOrderedProductsInformation(Cart cart){
        String result = "\nLp. |" + " Nazwa towaru |" + " Cena Brutto\n";
        int ordinalNumber = 0;
        try {
            for (Product product : cart.getAddedProducts()) {
                result += ++ordinalNumber + " " + product.getName() + " " + product.getBasePrice() + "\n";
            }
        } catch (EmptyCartException e) {
            e.getMessage();
        }
        return result;

    }

}
