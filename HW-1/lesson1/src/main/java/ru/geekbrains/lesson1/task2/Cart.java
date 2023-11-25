package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        boolean[] isPFC = {false, false, false};

        foodstuffs.forEach(food -> {
            if (food.getProteins()) isPFC[0] = true;
            if (food.getFats()) isPFC[1] = true;
            if (food.getCarbohydrates()) isPFC[2] = true;
        });

        if (isPFC[0] && isPFC[1] && isPFC[2]) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        if (!isPFC[0]) {
            var thing = market.getThings(Food.class).stream()
                    .filter(Food::getProteins)
                    .findFirst().orElse(null);
            if (thing != null) isPFC[0] = true;
            foodstuffs.add((T) thing);
        }
        if (!isPFC[1]) {
            var thing = market.getThings(Food.class).stream()
                    .filter(Food::getFats)
                    .findFirst().orElse(null);
            if (thing != null) isPFC[1] = true;
            foodstuffs.add((T) thing);
        }
        if (!isPFC[2]) {
            var thing = market.getThings(Food.class).stream()
                    .filter(Food::getCarbohydrates)
                    .findFirst().orElse(null);
            if (thing != null) isPFC[2] = true;
            foodstuffs.add((T) thing);
        }

        if (isPFC[0] && isPFC[1] && isPFC[2])
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
    }

}
