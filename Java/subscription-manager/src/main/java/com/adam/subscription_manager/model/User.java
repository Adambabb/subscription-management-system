package com.adam.subscription_manager.model;

//import the standart for the conexion to the database
//import the time for the creation of the user
import jakarta.persistence.*; 
import java.time.LocalDateTime;

//declare the table from the database
@Entity
@Table(name = "USERS") 
public class User {
	//declare the primary key and the sequence of the ids
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GEN")
    @SequenceGenerator(
        name = "USER_GEN", 
        sequenceName = "USERS_SEQ", 
        allocationSize = 1         
    )
    
    //declare the variables for each column and the diferents elements like the length or the null
    @Column(name = "USERID") 
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 30)
    private String username;

    @Column(name = "LASTNAME", nullable = false, length = 50) 
    private String lastname;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "DNI", nullable = false, unique = true, length = 12)
    private String dni;

    @Column(name = "USRPASS", nullable = false, length = 255)
    private String usrpass;

    @Column(name = "CREATE_DATE", updatable = false, insertable = false)
    private LocalDateTime createDate;
    
    @Column(name = "STATE")
    private String state="ACTIVE";

	// empty constructor for Hibernate to read
    public User() {
    }

    //the constructor where we put the information of a user
    public User(String username, String lastname, String email, String dni, String usrpass,String state) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.dni = dni;
        this.usrpass = usrpass;
        this.state=state;
    }

    //getter and setter for each column of the table
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getUsrpass() { return usrpass; }
    public void setUsrpass(String usrpass) { this.usrpass = usrpass; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }
    
    public String getState() {return state;}
	public void setState(String state) {this.state = state;}
}