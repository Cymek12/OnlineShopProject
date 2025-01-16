import service.CommandLine;
import service.ProductManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ProductManager productManager = new ProductManager();
        productManager.insertExampleData();

        executorService.submit(() -> new CommandLine(productManager).run());

    }
}