package com.github.didva.store.inmemmorystorage;

import com.github.didva.store.persistenceapi.IdGenerator;
import com.github.didva.store.persistenceapi.StoredEntity;
import com.github.didva.store.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStorage<T extends StoredEntity<ID>, ID> implements Storage<T, ID> {

    private IdGenerator<ID> idGenerator;
    private Map<ID, T> storage;

    public InMemoryStorage(IdGenerator<ID> idGenerator) {
        if (idGenerator == null) {
            throw new IllegalArgumentException();
        }
        this.idGenerator = idGenerator;
        this.storage = new HashMap<>();
    }

    @Override
    public T save(T entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException();
        }
        entity.setId(idGenerator.generateId());
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        if (entity.getId() == null || !storage.containsKey(entity.getId())) {
            throw new IllegalArgumentException();
        }
        storage.replace(entity.getId(), entity);
        return entity;
    }

    @Override
    public T findOne(ID id) {
        return storage.get(id);
    }

    @Override
    public void delete(ID id) {
        storage.remove(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
