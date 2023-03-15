package fileWriterAndReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static fileWriterAndReader.AppConstants.*;

public class Utils {

    private Utils() {
    }

    public static final String SPLIT_PATTERN = ";\n";

    static int collectIntegerInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }

    static String collectStringInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    static void getProductsFromUser() {
        do {
            System.out.println("Please enter your product details in this format name,quantity,price or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String productString = scanner.nextLine().trim();
            if (productString.equals("-1")) {
                return;
            }
            String[] split = productString.split(",");
            Product product = new Product(PRODUCT_LIST.size() + 1,
                    split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            PRODUCT_LIST.add(product);
        } while (true);
    }

    static void getProductsFromFile() {

        String stringFromFile = readFromFile();

        if (!stringFromFile.isEmpty()) {

            String[] split = stringFromFile.split(SPLIT_PATTERN);

            for (String productString : split) {
                String[] productStringSplits = productString.split(",");

                Product product = new Product(Integer.parseInt(productStringSplits[0]), productStringSplits[1],
                        Integer.parseInt(productStringSplits[2]), Double.parseDouble(productStringSplits[3]),
                        Boolean.parseBoolean(productStringSplits[4]));
                AppConstants.PRODUCT_LIST.add(product);
            }
        }
    }

    static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(AppConstants.FILE)) {
            for (Product product : AppConstants.PRODUCT_LIST) {
                fileWriter.write(product.getMessageDetails() + SPLIT_PATTERN);
                fileWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readFile() {
        String fileName = "product.txt";
        try {
            FileReader fileReader = new FileReader("product.txt");
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = fileReader.read()) != -1) ;
            {
                sb.append((char) i);
                System.out.println((char) i);
            }
            String productList = sb.toString();
            System.out.println(productList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void viewProducts() {
        if (!PRODUCT_LIST.isEmpty()) {
            String format = String.format("%-4s %-30s %-12s %-8s %n",
                    "ID", "NAME", "QUANTITY", "PRICE");

            StringBuilder sb = new StringBuilder(format);

            for (Product product : PRODUCT_LIST) {
                if (!product.isDeleted()) {
                    String productLine = String.format("%-4d %-30s %-12d %-8s %n",
                            product.getId(), product.getName(), product.getQuantity(), product.getPrice());
                    sb.append(productLine);
                }
            }
            System.out.println(sb);
        } else {
            System.out.println("No product found!");
        }
    }

    private static String readFromFile() {
        if (!FILE.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(FILE)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void delete() {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        product.delete();
        saveToFile();
        System.out.println("fileWriterAndReader.Product deleted");
    }

    private static Product getProduct(int productId) {
        if (productId < 1 || productId > PRODUCT_LIST.size()) {
            try {
                throw new InvalidProductIdException(String.format("fileWriterAndReader.Product with id %d is invalid", productId));
            } catch (InvalidProductIdException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return PRODUCT_LIST.get(productId - 1);
    }

    public static void sell() throws IOException {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        if (!product.isDeleted()) {
            System.out.println("How many quantity");
            Scanner scanner = new Scanner(System.in);
            int quantity = scanner.nextInt();
            if (product.getQuantity() >= quantity) {
                captureSales(productId, product.getName(), quantity, product.getPrice());
                saveToSalesFile();
                product.sell(quantity);
                saveToFile();
            }
            System.out.println("Product sold successfully");
        } else {
            System.out.println("Product has been sold out");
        }
    }

    private static void captureSales(int productId, String name, int quantity, double price) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate Date = LocalDate.now();
        Product product = new Product(productId, name, quantity, price);
        PRODUCTSALES_LIST.add(product);

    }

    private static void saveToSalesFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(FILE2)) {
            for(Product product:PRODUCTSALES_LIST)
            fileWriter.write(product + SPLIT_PATTERN);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update() {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        String name = collectStringInput("Enter product Name");
        product.setName(name);
        int quantity = collectIntegerInput("Enter product quantity");
        product.setQuantity(quantity);
        double price = collectIntegerInput("Enter product price");
        product.setPrice(price);
        saveToFile();
        System.out.println("Product updated ");
    }

    public static void viewSales() {
        int ops = collectIntegerInput("Select operations \n1. View all sales \n2. View sales by Product ID \n3. View sales by date ");
        if (ops == 1) {
            if (!PRODUCTSALES_LIST.isEmpty()) {
                String format = String.format("%-4s %-30s %-12s %-8s %n",
                        "ID", "NAME", "QUANTITY", "PRICE");

                StringBuilder sb = new StringBuilder(format);

                for (Product product : PRODUCTSALES_LIST) {
                    if (!product.isDeleted()) {
                        String productLine = String.format("%-4d %-30s %-12d %-8s %n",
                                product.getId(), product.getName(), product.getQuantity(), product.getPrice());
                        sb.append(productLine);
                    }
                }
                System.out.println(sb);
            } else {
                System.out.println("No product found!");
            }

        } else if (ops == 2){
            int productId = collectIntegerInput("Please enter ProductId you want to view ");
            if (!PRODUCTSALES_LIST.isEmpty()) {
                String format = String.format("%-4s %-30s %-12s %-8s %n",
                        "ID", "NAME", "QUANTITY", "PRICE");

                StringBuilder sb = new StringBuilder(format);

                for (Product product : PRODUCTSALES_LIST) {
                    if (product.getId() == productId) {
                        product = getSalesProduct(productId);
                        String productSalesLine = String.format("%-4d %-30s %-12d %-8s %n",
                                product.getId(), product.getName(), product.getQuantity(), product.getPrice());
                        sb.append(productSalesLine);
                    }
                    else{
                        System.out.println("no product with id" + productId + " " + "sold" );
                    }
                }
                System.out.println(sb);
            } else {
                System.out.println("No product with id!");
            }
        }

    }
    private static Product getSalesProduct(int productId) {
        if (productId < 1 ) {
            try {
                throw new InvalidProductIdException(String.format("Product with id %d is invalid", productId));
            } catch (InvalidProductIdException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return PRODUCTSALES_LIST.get(productId-1);
    }

}




