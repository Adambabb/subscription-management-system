package com.adam.subscription_manager.model;

//import the standart for the conexion to the database
//import the time for the creation of the user
import jakarta.persistence.*; 
import java.time.LocalDateTime;
@Entity
@Table(name="SUBSCRIPTION")
public class Subscription {
	//declare the primary key and the sequence of the ids

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SUBSCRIPTION_GEN")
	@SequenceGenerator(
			name="SUBSCRIPTION_GEN",
			sequenceName="SUBSCRIPTION_SEQ",
			allocationSize=1)
    //declare the variables for each column and the diferents elements like the length or the null

	@Column(name="SUBSCRIPTIONID")
	private long id;
	@Column(name="DATESTART")
	private LocalDateTime createDateStart;
	@Column(name="DATEEND" ,updatable=false,insertable=false)
	private LocalDateTime createDateEnd;
	
	@ManyToOne
	@JoinColumn(name="USERID", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="PLANID", nullable=false)
	private Plans plan;
    // empty constructor for Hibernate to read

	public Subscription() {
		
	}
    //the constructor where we put the information of a subscription

	public Subscription(User user,Plans plan) {
		this.user=user;
		this.plan=plan;
		this.createDateStart=LocalDateTime.now();
	}
    //declare the variables for each column and the diferents elements like the length or the null
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(LocalDateTime createDateStart) {
		this.createDateStart = createDateStart;
	}

	public LocalDateTime getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(LocalDateTime createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plans getPlan() {
		return plan;
	}

	public void setPlan(Plans plan) {
		this.plan = plan;
	}
	
	
}
