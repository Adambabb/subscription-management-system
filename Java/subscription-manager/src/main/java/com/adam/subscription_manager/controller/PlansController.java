package com.adam.subscription_manager.controller;
//import of the plans entity and service to use it
import com.adam.subscription_manager.model.Plans;
import com.adam.subscription_manager.service.PlansService;

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
@RequestMapping("/api/plans")
public class PlansController {

	//connection to the PlansService
	@Autowired
	private PlansService plansService;
	
	
	@GetMapping("/all")
	public List<Plans> getAllPlans() {
		return plansService.findAllPlans();
	}
	
	@GetMapping("/{id}")
	public Plans getPlansServicebyId(@PathVariable("id") Long id) {
		return plansService.findPlansbyId(id);
	}
	
	@GetMapping("/{state}")
	public ResponseEntity<?> getPlansbyState(@PathVariable("state") String state) {

		List<Plans> plans=plansService.findPlansbyState(state);
		if (plans.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There isnt any plans with the status: "+ state);
		}return ResponseEntity.ok(plans);
}
	@PostMapping("/save")
	public Plans createPlan(@RequestBody Plans plans) {
		return plansService.savePlans(plans);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePlan(@PathVariable("id") Long id) {
		 plansService.deletePlansbyId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> SoftdeletePlan(@PathVariable("id") Long id) {
		 try {
			 plansService.SoftdeletePlansbyId(id);
			 return ResponseEntity.ok("The plan with the id: " + id + " has been soft deleted");
		 }
		 catch (Exception e){
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		 }
	}
}
