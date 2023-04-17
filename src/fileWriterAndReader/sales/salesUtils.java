package fileWriterAndReader.sales;

import fileWriterAndReader.AppConstants;
import fileWriterAndReader.InvalidProductIdException;
import fileWriterAndReader.Product;
import fileWriterAndReader.Utils;
import fileWriterAndReader.sales.Sales;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static fileWriterAndReader.AppConstants.FILE2;
import static fileWriterAndReader.AppConstants.PRODUCTSALES_LIST;
import static fileWriterAndReader.Utils.collectIntegerInput;

public class salesUtils {
    public static final String SPLIT_PATTERN = ";\n";
    private static LocalDate currentDate;


    public static void captureSales() throws IOException {
      // Sales sales = new Sales();
     // PRODUCTSALES_LIST.add(sales);
        saveToSalesFile();
    }

    private static void saveToSalesFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(FILE2)) {
            for (Sales sales : AppConstants.PRODUCTSALES_LIST) {
                fileWriter.write(sales.getMessageDetails() + SPLIT_PATTERN);
               fileWriter.flush();
           }
       } catch (IOException e) {
            throw new RuntimeException(e);
       }
  }
//    private static void saveToSalesFile(int productId){
//        Sales sales = salesUtils.getSalesProduct( productId);
//        try(FileWriter fileWriter = new FileWriter(FILE2)) {
//            fileWriter.write(sales.getMessageDetails() + SPLIT_PATTERN);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void viewSales() {
        int ops = collectIntegerInput("Select operations \n1. View all sales \n2. View sales by Product ID \n3. View sales by date ");
        if (ops == 1) {
            if (!PRODUCTSALES_LIST.isEmpty()) {
                String format = String.format("%-4s %-30s %-12s %-8s %-10s %n",
                        "ID", "NAME", "QUANTITY", "PRICE","CURRENT DATE");

                StringBuilder sb = new StringBuilder(format);

                for (Sales sales : PRODUCTSALES_LIST) {
                    if (!sales.isDeleted()) {
                        String productLine = String.format("%-4d %-30s %-12d %-8s %10s %n",
                                sales.getId(), sales.getName(), sales.getQuantity(), sales.getPrice(), sales.getCurrentDate());
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

                for (Sales product : PRODUCTSALES_LIST) {
                    if (product.getId() == productId) {
                        product = getSalesProduct(productId);
                        String productSalesLine = String.format("%-4d %-30s %-12d %-8s  %n",
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
    private static Sales getSalesProduct(int productId) {
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


