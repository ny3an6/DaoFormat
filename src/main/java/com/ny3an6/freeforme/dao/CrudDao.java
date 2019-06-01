package com.ny3an6.freeforme.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<Y> {
    Optional<Y> find(Integer id);
    void save(Y user);
    List<Y> findAll();
}
