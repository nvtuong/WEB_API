/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j.service;

import java.sql.SQLException;
import neo4j.bean.User;
import neo4j.dao.AccountDAO;

/**
 *
 * @author Hoang Van Ngoc
 */
public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public boolean checkExistEmail(String email)throws SQLException, ClassNotFoundException {
        return accountDAO.checkExistEmail(email);
    }
    public User loginWithEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        return accountDAO.loginWithEmailAndPassword(email, password);
    }   
    public void setNewPasswordAccount(String id, String password)throws SQLException, ClassNotFoundException {
        accountDAO.setNewPasswordAccount(id, password);
    }
    public void registerNewAccount(String name, String email, String password)throws SQLException, ClassNotFoundException {
        accountDAO.registerNewAccount(name, email, password);
    }
        
}
