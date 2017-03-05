package com.github.didva.store.storage;

import com.github.didva.store.persistenceapi.StoredEntity;

import java.util.List;

public interface Storage<T extends StoredEntity<ID>, ID> {

    T save(T entity);

    T update(T entity);

    T findOne(ID id);

    void delete(ID id);

    List<T> findAll();

}
