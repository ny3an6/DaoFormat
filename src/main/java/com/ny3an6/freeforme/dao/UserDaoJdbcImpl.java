package com.ny3an6.freeforme.dao;

import com.ny3an6.freeforme.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao{
    private JdbcTemplate jdbcTemplate;
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM fix_user";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM fix_user where id = ?";
    private final String SQL_SELECT_BY_FIRST_NAME = "SELECT * FROM fix_user where first_name = ?";
    private final String SQL_INSERT_USER = "INSERT INTO fix_user(first_name, last_name, password) values(?, ?, ?)";

    public UserDaoJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (ResultSet resultSet, int i) ->{
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("password"));
    };

    @Override
    public List<User> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public boolean isExist(String firstName, String lastName, String password) {
        for (User user : findAll()){
            System.out.println(user.getFirstName());
            System.out.println(firstName);
            if(user.getFirstName().equals(firstName) &&
                user.getLastName().equals(lastName) &&
                user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getFirstName(), user.getLastName(), user.getPassword());
    }


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
