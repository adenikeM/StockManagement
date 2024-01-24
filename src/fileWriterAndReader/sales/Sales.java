package fileWriterAndReader.sales;

import java.time.LocalDate;
import java.util.Date;

public class Sales {
    public String name;
    public int quantitySold;
    public double price;
    public int id;


//    public Sales(int id,String name, int quantitySold, double price, LocalDate currentDate) {
//        this.id = id;
//        this.name = name;
//        this.currentDate = currentDate;
//        this.quantitySold = quantitySold;
//        this.price = price;
//    }

    public Sales(int id, String name, int quantitySold, double price,LocalDate currentDate) {
        this.id = id;
        this.name = name;
        this.quantitySold = quantitySold;
        this.price = price;
        this.currentDate = currentDate;
    }


    public double getTotalPrice(){
        return quantitySold * price;
    }


    static LocalDate currentDate;


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

    public String getCurrentDate() {
        return String.valueOf(currentDate);};


    public boolean isDeleted() {return isDeleted();
    }
}
