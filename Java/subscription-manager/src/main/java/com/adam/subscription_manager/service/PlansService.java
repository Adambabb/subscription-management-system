package com.adam.subscription_manager.service;

//Import of the Plans class to be able to modify it
import com.adam.subscription_manager.model.Plans;
//Import to comunicate with the data base
import com.adam.subscription_manager.repository.PlansRepository;

//Import for the Dependencies
import org.springframework.beans.factory.annotation.Autowired;
//Marks this class as a Spring Service
import org.springframework.stereotype.Service;
//import to use the list and the optional
import java.util.List;
import java.util.Optional;
@Service
public class PlansService {

	@Autowired
	private PlansRepository plansRepository;

	public List<Plans> findAllPlans(){
		return plansRepository.findAll();
	}

	public Plans findPlansbyId(Long id){
		Optional<Plans> plans=plansRepository.findById(id);
		return plans.orElse(null);
	}

	public List<Plans> findPlansbyState(String state){
		return plansRepository.findByState(state);
	}

	public Plans savePlans(Plans plan) {
		return plansRepository.save(plan);
	}

	public void deletePlansbyId(Long id) {
		plansRepository.deleteById(id);
	}

	public void SoftdeletePlansbyId(Long id) throws Exception {
		Optional<Plans> plans=plansRepository.findById(id);
		if(plans.isEmpty()) {
			throw new Exception("There isn't a plan with the id: " + id);
		}
		Plans plans2=plans.get();
		plans2.setState("INACTIVE");
		plansRepository.save(plans2);
	}

}
