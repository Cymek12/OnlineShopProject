package service;

import model.Product;

public class DisplayFormatter {

    public static String getProductToInvoice(Product product){
        String result = product.getName() + " " + product.getBasePrice();
        result += getProductConfigurationAndAccessories(product);
        return result;
    }

    public static String getProductToCart(Product product){
        String result = "Id. " + product.getId() + ", Nazwa: " + product.getName() + ", Cena: " + product.getBasePrice();
        result += getProductConfigurationAndAccessories(product);
        return result;
    }

    private static String getProductConfigurationAndAccessories(Product product){
        String result = "";
        if(!product.getChosenConfiguration().isEmpty()){
            result += "\n" + "Konfiguracja:" + "\n" + product.getChosenConfiguration();
        }
        if(!product.getAccessories().isEmpty()){
            result += "\n" + "Akcesoria:" + "\n" + product.getAccessories();
        }
        return result;
    }
}
