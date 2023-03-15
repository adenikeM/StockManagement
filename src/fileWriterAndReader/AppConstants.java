package fileWriterAndReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

    public class AppConstants {
        private AppConstants() {
        }
        public static final String FILE_NAME = "product.txt";
        public static final String FILE_NAME2 = "sales.txt";
        public static final File FILE = new File(FILE_NAME);
        public static final File FILE2 = new File(FILE_NAME2);
        public static List<Product> PRODUCT_LIST = new ArrayList<>();
        public static List<Product> PRODUCTSALES_LIST = new ArrayList<>();
}
