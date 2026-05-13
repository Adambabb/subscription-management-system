package com.adam.subscription_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adam.subscription_manager.repository.*;
import com.adam.subscription_manager.model.*;

@Service
public class SubscriptionServices {



	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public List<Subscription> findAllSubscription(){
		return subscriptionRepository.findAll();
	}

	public Subscription findSubscriptionbyId(Long id){
		Optional<Subscription> subscription=subscriptionRepository.findById(id);
		return subscription.orElse(null); 
	}
	
	public List<Subscription> findByState(String state){
		return subscriptionRepository.findByState(state);

	}

	public Subscription saveSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public void deleteSubscriptionbyId(Long id) {
		subscriptionRepository.deleteById(id);
	}
	
	public void SoftdeleteSubscriptionbyId(Long id) throws Exception {
		Optional<Subscription> subscription=subscriptionRepository.findById(id);
		if (subscription.isEmpty()) {
			throw new Exception("There inst any subscription with the id: " + id);
		}
		Subscription subscription2=subscription.get();
		subscription2.setState("inactive");
		subscriptionRepository.save(subscription2);
	}

	public List<Subscription>  findByUserId(Long userId){
		return subscriptionRepository.findByUserId(userId);
	}
	
	public List<Subscription>  findByPlanId(Long planId){
		return subscriptionRepository.findByPlanId(planId);
	}

}
