package fileWriterAndReader;

import fileWriterAndReader.AppConstants;

import java.io.IOException;

public class FileWriterAndReaderRunner {
        public static void main(String[] args) throws IOException {
            if (AppConstants.FILE.exists()) {
                Utils.getProductsFromFile();
            }

            int ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product\n4.Sell Product\n5.Update Product \n6.View Sales \n7.Exit");
            while(ops != 7){
                if(ops == 1){
                    Utils.getProductsFromUser();
                    Utils.saveToFile();
                }else if(ops == 2){
                    Utils.viewProducts();
                } else if (ops == 3){
                    Utils.delete();
                }else if (ops == 4){
                    Utils.sell();
                }else if (ops == 5){
                    Utils.update();
                } else {
                     Utils.viewSales();
                }
                ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product \n4.Sell Product \n5.Update Product \n6.View Sales \n7.Exit");
            }
        }
    }

