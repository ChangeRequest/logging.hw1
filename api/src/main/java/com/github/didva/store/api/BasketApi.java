package com.github.didva.store.api;

import com.github.didva.strore.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BasketApi {

    private LinkedHashMap<Item, Integer> items = new LinkedHashMap<>();

    public void add(Item item) {
        Integer currentAmount = items.get(item);
        if (currentAmount == null) {
            items.put(item, 1);
        } else {
            items.put(item, currentAmount + 1);
        }
    }

    public List<Item> getLast(int n) {
        int size = items.size();
        if (size < n || n < 1) {
            throw new IllegalArgumentException();
        }
        return Collections.unmodifiableList(new ArrayList<>(items.keySet()).subList(size - n, size));
    }

    public void remove(Item item) {
        Integer currentAmount = items.get(item);
        if (currentAmount == null) {
            throw new IllegalArgumentException();
        } else if (currentAmount == 1) {
            items.remove(item);
        } else {
            items.replace(item, currentAmount - 1);
        }
    }

    public Map<Item, Integer> checkout() {
        LinkedHashMap<Item, Integer> itemsToCheckout = this.items;
        this.items = new LinkedHashMap<>();
        return itemsToCheckout;
    }

}
