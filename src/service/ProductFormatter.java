package service;

import model.Product;

/**
 * zawiera metody zwracające informacje o produkcie w odpowiedniej formie
 */
public class ProductFormatter {

    public static String getProductToInvoice(CartItem cartItem){
        Product product = cartItem.getProduct();
        String result = product.getName() + " " + product.getBasePrice();
        result += getProductConfiguration(cartItem);
        return result;
    }

    public static String getProductToCart(CartItem cartItem){
        Product product = cartItem.getProduct();
        String result = "Id. " + product.getId() + ", Nazwa: " + product.getName() + ", Cena: " + product.getBasePrice();
        result += getProductConfiguration(cartItem);
        return result;
    }

    private static String getProductConfiguration(CartItem cartItem){
        String result = "";
        if(!cartItem.getChosenConfigurations().isEmpty()){
            result += "\n" + "Konfiguracja:" + "\n" + cartItem.getChosenConfigurations();
        }
        return result;
    }
}
