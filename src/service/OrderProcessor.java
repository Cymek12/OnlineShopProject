package service;

import exception.EmptyCartException;
import model.Order;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;

public class OrderProcessor {
    private static int factureNumber = 0;

    public void generateFacture(Order order, Cart cart){
        factureNumber++;
        try (FileWriter writer = new FileWriter("faktura_" + factureNumber + ".txt")){
            writer.write("Faktura numer: " + factureNumber + "\n");
            writer.write(getSellerInformation());
            writer.write(getBuyerInformation(order));
            writer.write(getOrderedProductsInformation(cart));
            writer.write("Do zapłaty: %s".formatted(order.getRoundedOrderPrice()));

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

    private String getOrderedProductsInformation(Cart cart){
        String result = "Lp. |" + " Nazwa towaru |" + " Cena Brutto\n";
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
