package fileWriterAndReader;

public class Product {
        private int id;
        private String name;
        private int quantity;
        private double price;

        private boolean isDeleted = false;

        public int getId() {
            return id;
        }

        public Product(int id, String name, int quantity, double price) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public Product(int id, String name, int quantity, double price, boolean isDeleted) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.isDeleted = isDeleted;
        }


        public boolean isDeleted() {
            return isDeleted;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void delete() {
            this.isDeleted = true;
        }

        public int sell(int quantity) {
            this.quantity -= quantity;
            return quantity;
        }

        public String getMessageDetails() {
            return id + "," + name + "," + quantity + "," + price + "," + isDeleted;
        }

        @Override
        public String toString() {
            return "fileWriterAndReader.Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", isDeleted=" + isDeleted +
                    '}';
        }
    }



