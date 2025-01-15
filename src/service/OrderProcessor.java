package service;

import exception.EmptyCartException;
import model.Order;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderProcessor {
    private static int factureNumber = 0;

    public void generateFacture(Order order, Cart cart){
        factureNumber++;
        try (FileWriter writer = new FileWriter("faktura_" + factureNumber + ".txt")){
            writer.write("Faktura numer: " + factureNumber + "\n");
            writer.write(getSellerInformation());
            writer.write(getBuyerInformation(order));
            writer.write(getOrderedProductsInformation(cart));
            writer.write("\nDo zapłaty: %s".formatted(order.getRoundedOrderPrice()));
            writer.write(getDateTimeInformation());
            writer.write("\nZamówienie zostanie wysłane w ciągu 7 dni roboczych od zaksięgowania wpłaty");

            System.out.println("Faktura została wygenerowana");
        } catch (IOException e) {
            System.out.println("!Błąd ppodczas generowania faktury generowaniu faktury!");
        }
    }

    private String getSellerInformation(){
        return "\nSprzedawca:\n" +
                "Sklep XYZ\n" +
                "ul. Piotrkowska 1, 00-001 Łódź\n" +
                "NIP: 111-222-33-44\n";
    }

    private String getBuyerInformation(Order order){
        return "\nNabywca:\n" +
                order.getFirstName() + " " + order.getLastName() + "\n" +
                order.getDeliveryAddress() + "\n" +
                order.getPhoneNumber() + "\n" +
                order.getEmailAddress() + "\n";
    }

    private String getDateTimeInformation(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        return "\n\nData i godzina wystawienia faktury: " + now.format(dateTimeFormatter) + "\n" +
                "Proszę opłacić zamówienie do " + now.plusDays(20).format(dateFormatter);
    }

    private String getOrderedProductsInformation(Cart cart){
        String result = "\nLp. |" + " Nazwa towaru |" + " Cena Brutto\n";
        int ordinalNumber = 0;
        try {
            for (Product product : cart.getAddedProducts()) {
                result += ++ordinalNumber + " " + product.getName() + " " + product.getPrice() + "\n";
    //                result += ++ordinalNumber + " " + product.getName() + " " + ((Computer) product).getProcessor() + " " +
            }
        } catch (EmptyCartException e) {
            System.out.println("Koszyk jest pusty!");
        }
        return result;

    }

}
