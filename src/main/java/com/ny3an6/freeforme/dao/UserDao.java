package com.ny3an6.freeforme.dao;

import com.ny3an6.freeforme.models.User;

import java.util.List;

public interface UserDao extends CrudDao<User> {
    List<User> findByFirstName(String firstName);
    boolean isExist(String firstName, String lastName, String password);


}
