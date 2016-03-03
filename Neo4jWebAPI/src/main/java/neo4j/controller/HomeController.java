package neo4j.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neo4j.bean.User;
import neo4j.service.UserService;


@RestController
public class HomeController {

	@RequestMapping(value = "/neo4j/{userID}", method = RequestMethod.GET,headers="Accept=application/json")
	public User GetUserInfo(@PathVariable String userID) throws ClassNotFoundException, SQLException
	{
		UserService userService = new UserService();
		
		return userService.getUserInfor(userID);
	}
	/*
	@RequestMapping(value = "/neo4j/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public Country getCountryById(@PathVariable int id)
	{
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries=createCountryList();

		for (Country country: listOfCountries) {
			if(country.getId()==id)
				return country;
		}
		
		return null;
	}

// Utiliy method to create country list.
	public List<Country> createCountryList()
	{
		Country indiaCountry=new Country(1, "India");
		Country chinaCountry=new Country(4, "China");
		Country nepalCountry=new Country(3, "Nepal");
		Country bhutanCountry=new Country(2, "Bhutan");

		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}
	*/
}
