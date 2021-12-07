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

    @ManyToOne
    private User user;
    
    @ManyToOne //(cascade=CascadeType.ALL)
    private EventCategory eventCategory;

    public Event(String description, EventCategory eventCategory, User user) {
        super();
        this.description = description;
        this.eventCategory = eventCategory;
        this.user = user;
    }

    public Event() {}

    public User getUser() { return user;}

    public void setUser(User user) {this.user = user;}

    public String getDescription() { return description;}

    public void setDescription(String description) {this.description = description;}

    public EventCategory getEventCategory() {return eventCategory;}

    public void setEventCategory(EventCategory eventCategory) {this.eventCategory = eventCategory;}


}

