package com.mo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mo.businessObject.Bill;
import com.mo.businessObject.Product;
import com.mo.businessObject.User;
import com.mo.service.ProductService;
import com.mo.service.UserService;

@Controller
public class CheckoutController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserDetailsService userSecurityService;
	
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public String saveUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("role") String role){
		User user= new User();
		user.setName(name);
		user.setRole(role);
		user.setEmail(email);
		User userTobeSaved=userService.checkUser(user.getEmail());
		if(userTobeSaved!=null){
			userTobeSaved.setName(user.getName());
		}else{
			userService.saveUser(user);
		}
		UserDetails userDetails = userSecurityService
				.loadUserByUsername(user.getEmail());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return HttpStatus.OK.toString();
	}
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public String saveProduct(@RequestParam("name") String name, @RequestParam("description") String description, 
			@RequestParam("category") String category, @RequestParam("unitPrice") double unitPrice){
		Product product= new Product();
		product.setCategory(category);
		product.setDescription(description);
		product.setName(name);
		product.setUnitPrice(unitPrice);
		productService.saveProduct(product);
		return HttpStatus.OK.toString();
	}
	
	@RequestMapping(value="/getAllProducts", produces="application/json")
	@ResponseBody
	public String getAllProducts(){
		List<Product> products= productService.getAllProducts(null);
		JSONArray arr= null;
		if(products!=null){
			arr= new JSONArray(products);
		}
		
		return arr.toString();
	}
	
	@RequestMapping("/getBill")
	@ResponseBody
	public String getBill(@RequestParam ("productId") List<String> productIds, @RequestParam ("quantity") List<Integer> quantityList){
		Bill bill=productService.generateBill(productIds, quantityList);
		JSONObject obj=new JSONObject(bill);
		
		return obj.toString();
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam("email") String email){
		User user= userService.checkUser(email);
		if(user!=null){
			UserDetails userDetails = userSecurityService
					.loadUserByUsername(user.getEmail());
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getEmail(),
					userDetails.getAuthorities());
			System.out.println(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return HttpStatus.OK.toString();
		}else{
			return HttpStatus.FORBIDDEN.toString();
		}
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response){
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return HttpStatus.OK.toString();
	}
}
