package service;

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
            writer.write(sellerInformation());
            writer.write(buyerInformation(order));
            writer.write(orderedProductsInformation(cart));
            writer.write("Do zapłaty: %s".formatted(Math.round(order.getOrderPrice() * 100.00) / 100.00));

            System.out.println("Faktura została wygenerowana");
        } catch (IOException e) {
            System.out.println("!Błąd ppodczas generowania faktury generowaniu faktury!");
        }
    }

    private String sellerInformation(){
        return "\nSprzedawca:\n" +
                "Sklep XYZ\n" +
                "ul. Piotrkowska 1, 00-001 Łódź\n" +
                "NIP: 111-222-33-44\n";
    }

    private String buyerInformation(Order order){
        return "\nNabywca:\n" +
                order.getFirstName() + " " + order.getLastName() + "\n" +
                order.getDeliveryAddress() + "\n" +
                order.getPhoneNumber() + "\n" +
                order.getEmailAddress() + "\n";
    }

    private String orderedProductsInformation(Cart cart){
        String result = "Lp. |" + " Nazwa towaru |" + " Cena Brutto\n";
        int ordinalNumber = 0;
        for (Product product : cart.getAddedProducts()) {
            result += ++ordinalNumber + " " + product.getName() + " " + product.getPrice() + "\n";
//                result += ++ordinalNumber + " " + product.getName() + " " + ((Computer) product).getProcessor() + " " +
        }
        return result;

    }

}
