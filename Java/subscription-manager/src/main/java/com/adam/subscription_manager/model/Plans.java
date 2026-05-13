package com.adam.subscription_manager.model;

//import the standart for the conexion to the database
import jakarta.persistence.*; 

//declare the table from the database
@Entity
@Table(name="PLANS")
public class Plans {
	//declare the primary key and the sequence of the ids
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PLAN_GEN")
	@SequenceGenerator(
			name="PLAN_GEN",
			sequenceName="PLAN_SEQ",
			allocationSize=1
			)
    //declare the variables for each column and the diferents elements like the length or the null
	@Column(name="PLANID")
	private long id;
	@Column(name="PLANNAME" ,nullable=false,unique=true,length=40)
	private String planname;
	@Column(name="PRICE",nullable=false,columnDefinition = "NUMBER")
	private Double price;
	@Column(name="PLANDURATION",nullable=false)
	private int duration;	
    @Column(name = "STATE")
    private String state="ACTIVE";
    // empty constructor for Hibernate to read

	public Plans() {
		
	}
    //the constructor where we put the information of a plan
	public Plans(String planname,Double price, int duration, String state) {
		this.planname=planname;
		this.price=price;
		this.duration=duration;
		this.state=state;
	}
    //getter and setter for each column of the table

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}