package mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController  
@CrossOrigin("http://localhost:4200")
public class ProductController
{
	@Autowired
	ProductManager manager;
	@GetMapping(value = "crud/products",headers = "Accept=application/json")  
	 public String showProducts()
	 {
		  return new Gson().toJson(manager.getProducts());
	 }
	@GetMapping(value = "crud/search/{pid}", headers = "Accept=application/json")  
	 public Product getPro(@PathVariable int pid)
	 {
		Product p=manager.getProduct(pid);
		return p;
	 }
	@DeleteMapping(value = "crud/delete/{pid}", headers = "Accept=application/json")  
	 public void removepro(@PathVariable int pid)
	 {
		manager.delete(pid);
	 }
	@PutMapping(value = "crud/update/{pid}",headers = "Accept=application/json")  
	 public void updatepro(@RequestBody Product product,@PathVariable int pid)
	 {
		manager.update(product,pid);
	 }
	@PostMapping(value = "crud/add", headers = "Accept=application/json")  
	 public void addpro(@RequestBody Product product)
	 {
		System.out.println("addpro called");
		manager.addProduct(product);
	 }
}
