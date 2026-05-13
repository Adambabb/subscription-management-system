package com.adam.subscription_manager.controller;

//import of the user entity and service to use it
import com.adam.subscription_manager.model.User;
import com.adam.subscription_manager.service.UserService;

// Imports for the controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
//Import to achieve the get, post...
import org.springframework.web.bind.annotation.*;
//import to use the list
import java.util.List;

// creation of the api controller
@RestController
// assign the route of the controller
@RequestMapping("/api/users")
public class UserController {
	//connection to the UserService
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.findUserById(id);
	}
	
	@GetMapping("/{state}")
	public ResponseEntity<?> getUserbyState(@PathVariable("state") String state) {

			List<User> user=userService.findUserByState(state);
			if (user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There isnt any user with the status: "+ state);
			}return ResponseEntity.ok(user);
	}


	@PostMapping("/save")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> SoftdeleteUser(@PathVariable("id") Long id) {
		try {
			userService.SoftdeleteUser(id);

			return ResponseEntity.ok("User deleted (soft delete) successfully");

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}