package info.idb.crud.service;

import java.util.List;

import info.idb.crud.dto.Response;

public interface BaseService<ENTITY, ID> {
    Response<?> persist(ENTITY entity);
    Response<?> merge(ENTITY entity);
    Response<List<ENTITY>> findAll();
    Response<ENTITY> findById(ID id);
    Response<?> deleteById(ID id);
}
