import Passpor.Passport;
import Passpor.PassportsLibrary;
import ProductList.*;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String str = "Банан 5";
        Pattern p1 = Pattern.compile(".*a+.*?");//en
        Pattern p2 = Pattern.compile("[а-яА-Я 0-9]+?");//ru
        Matcher m1 = p1.matcher(str);
        Matcher m2 = p2.matcher(str);

        System.out.println(m1.matches() + " " + m2.matches());
        System.out.println("Задание 1");
        ProductList pd = new ProductList();
        pd.add();
        pd.add();
        pd.printInfo();
        pd.setAlreadyBought(pd.find());
        pd.changeProduct(pd.find());
        pd.remove(pd.find());
        pd.printInfo();

        System.out.println("Задание 2");
        List<Product> products = new LinkedList<Product>();
        products.add(new Product("Молоко", 100, 0.5));
        products.add(new Product("Яйца", 150, 0.9));
        products.add(new Product("Масло сливочное", 80, 0.5));
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe("Recipe", products);
        Recipe recipe2 = new Recipe("New recipe", products);
        Recipe recipe3 = new Recipe("Recipe", products);
        try {
            addRecipe(recipes, recipe1);
            addRecipe(recipes, recipe2);
            addRecipe(recipes, recipe3);
        } catch (RuntimeException e) {
            System.out.println("Обработана ошибка " + e.getMessage());
        }
        for (Recipe r : recipes) {
            System.out.println(r);
        }

        task3();
        task5(15);

        System.out.println("Задание 6");
        Passport ivan = new Passport(1, "Ivan","Ivanov","Ivanovitch",
                LocalDate.of(1980,1,1));
        Passport petr = new Passport(2, "Petr","Petrov","Petrovitch",
                LocalDate.of(1980,1,1));
        Passport sidor = new Passport(1, "Sidor","Sidorov","Sidorovitch",
                LocalDate.of(1980,1,1));
        PassportsLibrary pl = new PassportsLibrary();
        pl.add(ivan);
        pl.add(petr);
        pl.viewInfo();
        System.out.println("***");
        pl.add(sidor);
        pl.viewInfo();
        System.out.println("***");
        System.out.println(pl.find(1));
        System.out.println(pl.find(3));

    }

    public static void addRecipe(Set<Recipe> recipes, Recipe recipe) {
        if (!recipes.add(recipe)) throw new RuntimeException("Такой рецепт уже в коллекции");
    }

    static void task3() {
        System.out.println("Задание 3");
        Random rnd = new Random();
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            boolean b = numbers.add(rnd.nextInt(1000));
            if (!b) i--;
        }
        Set<Integer> clone = new HashSet<>(numbers);
        for (Integer i : clone) {
            if (i % 2 != 0) numbers.remove(i);
        }
        for (Integer i : numbers) {
            System.out.println(i);
        }
    }

    static <A> void task4(Collection<A> collection) {
        // Этот метод по идее выведет в консоль все уникальные элементы любого списка.
        // Ковырять машинки и прочее нет никакого желания
        Set<A> newSet = new HashSet<>(collection);
        for (A item : newSet) {
            System.out.println(item.toString());
        }
    }

    static void task5(int quantity) {
        System.out.println("Задание 5");
        //generate tasks
        Set<String> tasks = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                tasks.add(i + " x " + j + " =");
            }
        }
        //print tasks
        int count = 0;
        for (String s : tasks) {
            System.out.println(s);
            count++;
            if (count == quantity) return;
        }
    }

    static void task7() {
        System.out.println("Задание 7");
        System.out.println("Быстрее всех будет поиск по элементам будет вестись в HashSet");
        System.out.println("В HashSet поиск по значению будет гораздо быстрее чем в списках");
        System.out.println("В отличии от списков в HashSet для поиска не придется перебирать все элементы");
        System.out.println("сравниваться будут лишь элементы с равными по значению hashcode");
    }
}