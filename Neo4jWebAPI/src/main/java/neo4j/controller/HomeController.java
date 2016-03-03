package neo4j.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neo4j.bean.User;
import neo4j.service.AccountService;
import neo4j.service.UserService;


@RestController
public class HomeController {

    @RequestMapping(value = "/neo4j/{userID}", method = RequestMethod.GET,headers="Accept=application/json")
    public User GetUserInfo(@PathVariable String userID) throws ClassNotFoundException, SQLException
    {
            UserService userService = new UserService();	
            return userService.getUserInfor(userID);
    }
    @RequestMapping(value = "/neo4j/register", method = RequestMethod.GET,headers="Accept=application/json")
    public void RegisterAccount() throws ClassNotFoundException, SQLException
    {
        AccountService service = new AccountService();
        service.registerNewAccount("testtest", "testtest", "testtest");
    }
}
