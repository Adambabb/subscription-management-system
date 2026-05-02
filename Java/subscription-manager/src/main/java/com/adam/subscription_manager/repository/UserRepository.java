package com.adam.subscription_manager.repository;

//imports of the class where we have the elements of the table
import com.adam.subscription_manager.model.User;
//import to be able to use the methods from the father class
import org.springframework.data.jpa.repository.JpaRepository;
//import to be able to create the repository
import org.springframework.stereotype.Repository;

//place where we  put new methods that dont come with the import

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
