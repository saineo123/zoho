package projectManagementSystem.projectManagement.controller;
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

import projectManagementSystem.projectManagement.model.User;
import projectManagementSystem.projectManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
   
    @Autowired
    UserService userService;
    // save user
   @PostMapping
   @Secured("ROLE_ADMIN")
   public User saveUser(@RequestBody User user){
    return userService.save(user);
   } 

   // get all user
   @GetMapping
   @Secured("ROLE_ADMIN")
   public Iterable<User> getAllUsers(){
    return userService.findAll();
   }

   // get user by id
   @GetMapping("/{id}")
   public Optional<User> getUserById(@PathVariable("id") Long id){
    return userService.findById(id);
   }

   //update user
   @PutMapping("/{id}")
   @Secured("ROLE_ADMIN")
   public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
    user.setId(id);
    return userService.save(user);
   }

   //delete user
   @DeleteMapping("/{id}")
   @Secured("ROLE_ADMIN")
   public void deleteUser (@PathVariable("id") Long id){
    userService.deleteById(id);
   }

   
}