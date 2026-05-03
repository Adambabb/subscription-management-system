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

	public Subscription findSubscriptionbyId(Long Id){
		Optional<Subscription> subscription=subscriptionRepository.findById(Id);
		return subscription.orElse(null); 
	}

	public Subscription saveSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public void deleteSubscriptionbyId(Long id) {
		subscriptionRepository.deleteById(id);
	}


}
