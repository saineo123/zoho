package projectManagementSystem.projectManagement.controller;

// import all the necessary packages

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projectManagementSystem.projectManagement.model.Admin;
import projectManagementSystem.projectManagement.service.AdminService;

// start the REST api controller, which helps access all the api calls
@RestController
@RequestMapping("/auth") // request the api to start with /auth to be the prefix
public class AdminController { // create a AdminController class
    @Autowired // using Autowired to initialize the AdminService class, which is like initializing a constructor
    AdminService admin_service;
    // create a post request to save the admin details
    @PostMapping
    @Secured("ROLE_ADMIN") // this annotation tell that only ADMIN can access this api call
    public Admin saveCustomer(@RequestBody Admin admin){ // create a save admin method
        return admin_service.save(admin); // save the admin
    }

    // create a get request
    @GetMapping
    @Secured("ROLE_ADMIN") // only admin can access this
    public Iterable<Admin> getAllCustomers(){ // get all the admin details
        return admin_service.findAll(); // save the details
    }

    @GetMapping("/{id}") // creating a get request
    public Optional<Admin> getCustomerById(@PathVariable("AdminId") Long id){ // use the prefix /id
        try {
            if(admin_service.existsById(id)){
                return admin_service.findName(id); // if the admin exists then return the details or else throw an exception
            }
            else{
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        
    }

   // create a put request 
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Admin updateCustomer(@PathVariable("AdminId") Long id, @RequestBody Admin admin){
        admin.setAdminId(id);
        return admin_service.save(admin);
    }
    
    // creating a delete request
    @DeleteMapping("/{id}")
    public void deleteCustomer (@PathVariable("id") Long id){
        admin_service.deleteById(id);
    }
    
}