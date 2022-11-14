package ProductList;

import java.util.*;

public class Recipe {
    String name;
    double cost;
    Set<Product> products = new HashSet<>();

    public Recipe(String name, Collection<Product> products) {
        this.name = name;
        this.products.addAll(products);
        cost = mathCost();
    }

    public Recipe(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        if (!products.add(product)) System.out.println("Продукт уже добавлен в рецепт!");
        cost = mathCost();
    }

    public void changeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            Product p = product;
            System.out.println("Введите новый вес продукта.");
            p.setWeight(inputDouble());
            System.out.println("Введите новый стоимость килограмма продукта.");
            p.setCost(inputDouble());
            products.add(p);
            cost = mathCost();
        } else System.out.println("Продукт нет в рецепте!");
    }

    private double mathCost() {
        double d = 0;
        for (Product p : products) {
            d += p.getCost() * p.getWeight();
        }
        return d;
    }

    private double inputDouble() {
        double d;
        Scanner console = new Scanner(System.in);
        while (true) {
            try {
                d = console.nextDouble();
                if (d <= 0) throw new RuntimeException("Значение может быть строго больше нуля!");
                console.close();
                return d;
            } catch (InputMismatchException e1) {
                System.out.println("Вы ввели не число! Попробуйте снова.");
            } catch (RuntimeException e2) {
                System.out.println(e2.getMessage() + " Попробуйте снова.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;

        Recipe recipe = (Recipe) o;

        return name.equals(recipe.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        String str1 = "Рецепт " + getName() + ":";
        if (products.isEmpty()) str1 += "\nПуст!";
        StringBuilder sb = new StringBuilder(str1);
        for (Product p : products) {
            sb.append("\n").append(p.toString());
        }
        sb.append("\n").append(String.format("Суммарная стоимость: %.2f рублей.", cost));
        return sb.toString();
    }
}
