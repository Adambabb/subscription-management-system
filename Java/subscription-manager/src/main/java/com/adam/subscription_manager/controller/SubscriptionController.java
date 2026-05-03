package com.adam.subscription_manager.controller;
//import of the subscription entity and service to use it
import com.adam.subscription_manager.model.Subscription;
import com.adam.subscription_manager.service.SubscriptionServices;

//Imports for the controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//Import to achieve the get, post...
import org.springframework.web.bind.annotation.*;
//import to use the list
import java.util.List;
//creation of the api controller
@RestController
//assign the route of the controller
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
	//connection to the subscriptionService
	@Autowired
	private SubscriptionServices subscriptionServices;
	
	@GetMapping("/all")
	public List<Subscription> getAllSubscriptions() {
		return subscriptionServices.findAllSubscription();
	}
	
	@GetMapping("/{id}")
	public Subscription getSubscriptionbyId(@PathVariable("id") Long id) {
		return subscriptionServices.findSubscriptionbyId(id);
	}
	
	@GetMapping("/users/{id}")
	public List<Subscription> getSubscriptionsbyUserid(@PathVariable("id") Long userid) {
		return subscriptionServices.findByUserId(userid);
	}
	
	@GetMapping("/plans/{id}")
	public List<Subscription> getSubscriptionsbyPlanid(@PathVariable("id") Long planid) {
		return subscriptionServices.findByPlanId(planid);
	}
	
	@GetMapping("/{state}")
	public ResponseEntity<?> getSubscriptionsbyState(@PathVariable("state") String state) {

			List<Subscription> subscription=subscriptionServices.findByState(state);
			if (subscription.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There isnt any subscription with the status: "+ state);
			}return ResponseEntity.ok(subscription);
	}
	
	@PostMapping("/save")
	public Subscription createSubscription(@RequestBody Subscription subscription) {
		return subscriptionServices.saveSubscription(subscription);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteSubscription(@PathVariable("id") Long id) {
		 subscriptionServices.deleteSubscriptionbyId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> SoftdeleteSubscription(@PathVariable("id") Long id) {
		 try {
			subscriptionServices.SoftdeleteSubscriptionbyId(id);
			return ResponseEntity.ok("The subscription with the id: " + id + " has been put Inactive");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
