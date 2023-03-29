package fileWriterAndReader.sales;

import java.time.LocalDate;
import java.util.Date;

public class Sales {
    private String name;
    private int quantitySold;
    private double price;
    private int id;

    public double getTotalPrice(){
        return quantitySold * price;
    }


    static LocalDate currentDate = LocalDate.now();

    public Sales(int id, String name, int quantitySold, double price,LocalDate currentDate) {
        this.id = id;
        this.name = name;
        this.currentDate = currentDate;
        this.quantitySold = quantitySold;
        this.price = price;
    }

    public Sales(int productId, String name, int quantitySold, double price) {

    }

    public String getMessageDetails() {
        return id + "," + name + "," + quantitySold + "," + price + "," + currentDate;
    }
        @Override
        public String toString () {
            return "fileWriterAndReader.Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantitySold+
                    ", price=" + price +
                    ", currentDate =" + currentDate+
                    '}';
        }

    public int getQuantity() {return quantitySold;}

    public String getName() {return name;}

    public int getId() {return id;}

    public double getPrice() {
        return price;
    }


    public boolean isDeleted() {return isDeleted();
    }
}
