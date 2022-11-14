package ProductList;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Тут основные методы по продуктам, логика программы должна быть в другом классе.
Если писать еще и всю логику, то это ДЗ тянет на курсовую.
Судя по методам, и так не сложно догадаться как все должно работать.
 */

public class ProductList {
    private Map<Product, Boolean> productMap = new HashMap<>();

    public Product createProduct() {
        String name = inputName();
        System.out.println("Введите требуемое количество продукта в кг.");
        double weight = inputDouble();
        System.out.println("Введите стоимость килограмма продукта");
        double cost = inputDouble();
        return new Product(name, cost, weight);
    }

    public void add() {
        if (productMap.isEmpty()) {
            add(createProduct());
        } else {
            String name = inputName();
            Product p = new Product(name, 0, 0);
            if (!productMap.containsKey(p)) {
                setProperty(p, false);
            } else System.out.println("Товар уже есть в списке");
        }
    }

    private void setProperty(Product product, boolean b) {
        System.out.println("Введите требуемое количество продукта в кг.");
        double weight = inputDouble();
        System.out.println("Введите стоимость килограмма продукта");
        double cost = inputDouble();
        product.setCost(cost);
        product.setWeight(weight);
        productMap.put(product, b);
    }

    public void add(Product product) {
        if (!productMap.containsKey(product)) {
            productMap.put(product, false);
        } else System.out.println("Товар уже есть в списке");
    }

    public void remove(Product product) {
        if (productMap.containsKey(product)) {
            productMap.remove(product);
        } else System.out.println("Товара нет в списке!");
    }

    public Product find(Product product) {
        if (productMap.containsKey(product)) {
            for (Map.Entry<Product, Boolean> mapEntry : productMap.entrySet()) {
                if (mapEntry.getKey().equals(product)) return mapEntry.getKey();
            }
        }
        return null;
    }

    public Product find() {
        String name = inputName();
        Product p = new Product(name, 0, 0);
        return find(p);
    }

    public void setAlreadyBought(Product product) {
        Product p = find(product);
        if (p != null) {
            productMap.remove(p);
            productMap.put(p, true);
        } else System.out.println("Продукта нет в списке");
    }

    public void changeProduct(Product product) {
        Product p = find(product);
        if (p != null) {
            boolean isAlreadyBought = productMap.get(p);
            productMap.remove(p);
            setProperty(p, isAlreadyBought);
        } else System.out.println("Продукта нет в списке");
    }

    private String inputName() {
        System.out.println("Введите имя продукта");
        Scanner console = new Scanner(System.in);

        while (true) {
            try {
                //почему когда я использую try(Scanner console = new Scanner(System.in)) метод уходит в бесконечный цикл?
                //Так все работает, как я хочу, но не могу понять закрывается ли Scanner (console.close();)???
                String str = console.nextLine();
                //System.out.println("inputName");
                if (str.isBlank()) throw new RuntimeException("Вы ввели пустое имя.");
                if (!str.matches("[а-яА-Я]+?")) throw
                        new RuntimeException("Имя может содержать только кирилицу пробел и цифры");
                return str;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + " Попробуйте снова.");
            }
        }
    }

    private double inputDouble() {
        Scanner console = new Scanner(System.in);
        while (true) {
            try {
                String str = console.nextLine();
                double d = Double.parseDouble(str);
                //System.out.println("inputDouble");
                if (d <= 0) throw new RuntimeException("Значение может быть строго больше нуля!");
                return d;
            } catch (NumberFormatException e1) {
                System.out.println("Вы ввели не число! Попробуйте снова.");
            } catch (RuntimeException e2) {
                System.out.println(e2.getMessage() + " Попробуйте снова.");
            }
        }
    }

    public void printInfo() {
        String str;
        for (Product p : productMap.keySet()) {
            str = productMap.get(p) ? " уже куплен." : " еще не куплен.";
            System.out.println(p.toString() + str);
        }
    }
}
