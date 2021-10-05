package org.launchcode.outdoorEvents.models;

import com.sun.istack.NotNull;
import org.launchcode.outdoorEvents.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    protected String name;
    @NotNull
    private String pwHash;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;


    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    // @OneToMany(mappedBy="user")
    // private List<Location> locations = new ArrayList<>();

    public User(){};

    public User(String name, String password, String firstName, String lastName, String email) {
        this.name = name;
        this.pwHash = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event;}

    // public List<Location> getLocations() {return locations;}

    // public void setLocations(List<Location> locations) {this.locations = locations;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public boolean isMatchingPassword(String password) {return encoder.matches(password, pwHash);}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

