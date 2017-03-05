package com.github.didva.store.api.repositories;

import com.github.didva.store.storage.Storage;
import com.github.didva.strore.model.Category;

import java.util.List;

public class CategoryRepository {

    private Storage<Category, Long> categoryStorage;

    public CategoryRepository(Storage<Category, Long> categoryStorage) {
        if (categoryStorage == null) {
            throw new IllegalArgumentException();
        }
        this.categoryStorage = categoryStorage;
    }

    public Category saveOrUpdate(Category category) {
        if (category.getId() == null) {
            return categoryStorage.save(category);
        }
        return categoryStorage.update(category);
    }

    public Category find(Long id) {
        return categoryStorage.findOne(id);
    }

    public void remove(Long id) {
        categoryStorage.delete(id);
    }

    public List<Category> getAll() {
        return categoryStorage.findAll();
    }

}
