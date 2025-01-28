import service.CommandLine;
import service.ProductManager;


public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        productManager.insertExampleData();

        new CommandLine(productManager).run();

    }
}