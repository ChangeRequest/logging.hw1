package com.github.didva.store.api;

import com.github.didva.strore.model.Basket;
import com.github.didva.strore.model.Item;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BasketApi {

    private Basket basket = new Basket();

    public void add(Item item) {
        basket.addItem(item);
    }

    public List<Item> getLast(int n) {
        List<Item> items = basket.getItems();
        int size = items.size();
        if (n < 1 || n > size) {
            throw new IllegalArgumentException();
        }
        return Collections.unmodifiableList(items.subList(size - n, size));
    }

    public void remove(Item item) {
        basket.removeItem(item);
    }

    public Map<Item, Integer> checkout() {
        List<Item> items = basket.getItems();
        LinkedHashMap<Item, Integer> collectedItems = new LinkedHashMap<>();
        for (Item item : items) {
            Integer amount = collectedItems.get(item);
            if (amount != null) {
                collectedItems.replace(item, amount + 1);
            } else {
                collectedItems.put(item, 1);
            }
        }
        return collectedItems;
    }

}
