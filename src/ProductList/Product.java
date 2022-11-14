package ProductList;

import java.util.Objects;

public class Product {
    //private static int count = 0;
    private String name;
    private double cost;
    private double weight;

    //private final int ID;


    public Product(String name, double cost, double weight) {
        //ID = count++;
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    //public int getID() {return ID;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        //       if (Double.compare(product.cost, cost) != 0) return false;
        //       if (Double.compare(product.weight, weight) != 0) return false;
        //       if (ID != product.ID) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Продукт: %s; количество: %.2f кг; стоимость: %.2f рублей.",
                name, weight, cost);
    }
}
