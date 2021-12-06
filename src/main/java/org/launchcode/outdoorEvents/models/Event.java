package org.launchcode.outdoorEvents.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @Size(max = 500, message = "Description too long!")
    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    private User user = new User();
    
    @ManyToOne(cascade=CascadeType.ALL)
    private EventCategory eventCategory = new EventCategory();

    // @OneToMany(mappedBy="user")
    // private Location locations;

    public Event(String description) {
        super();
        this.description = description;
    }

    public Event() {}

    public User getUser() { return user;}

    public void setUser(User user) {this.user = user;}

    public String getDescription() { return description;}

    public void setDescription(String description) {this.description = description;}

    public EventCategory getEventCategory() {return eventCategory;}

    public void setEventCategory(EventCategory eventCategory) {this.eventCategory = eventCategory;}


}

