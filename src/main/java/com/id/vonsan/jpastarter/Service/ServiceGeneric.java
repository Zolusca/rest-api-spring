package com.id.vonsan.jpastarter.Service;

import java.util.List;

/*
 * T used for entities
 * Id used for primary key data type
 */
public interface ServiceGeneric<T,ID> {
    public T create(T t);
    public void delete(ID id);
    public List<T> getAll();
    public T update(T t);
    public T getById(ID id);
}
