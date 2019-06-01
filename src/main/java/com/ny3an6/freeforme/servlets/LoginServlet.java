package com.ny3an6.freeforme.servlets;

import com.ny3an6.freeforme.dao.UserDao;
import com.ny3an6.freeforme.dao.UserDaoJdbcImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        try{
            properties.load(new FileInputStream(getServletContext().getRealPath("WEB-INF/classes/application.properties")));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
            userDao = new UserDaoJdbcImpl(dataSource);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String password = request.getParameter("password");
        if(userDao.isExist(firstName, lastName, password)){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", firstName+" "+lastName);
            System.out.println(userDao.isExist(firstName, lastName, password));
            response.sendRedirect("/main");
        }else{
            System.out.println(userDao.isExist(firstName, lastName, password));
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }
}
