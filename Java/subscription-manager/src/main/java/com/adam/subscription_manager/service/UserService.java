package com.adam.subscription_manager.service;

// Import of the user class to be able to modify it
import com.adam.subscription_manager.model.User;
// Import to comunicate with the data base
import com.adam.subscription_manager.repository.UserRepository;

// Import for the Dependencies
import org.springframework.beans.factory.annotation.Autowired;
// Marks this class as a Spring Service
import org.springframework.stereotype.Service;
//import to use the list and the optional
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}


	public User saveUser(User user) {

		return userRepository.save(user);
	}


	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}