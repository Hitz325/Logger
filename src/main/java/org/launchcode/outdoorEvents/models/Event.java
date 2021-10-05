package org.launchcode.outdoorEvents.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Category is required")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="event_category_id")
    private EventCategory eventCategory;

    public Event(String description, EventCategory eventCategory) {
        this.description = description;
        this.eventCategory = eventCategory;
    }

    public Event() {}

    public String getDescription() { return description;}

    public void setDescription(String description) {this.description = description;}

    public EventCategory getEventCategory() {return eventCategory;}

    public void setEventCategory(EventCategory eventCategory) {this.eventCategory = eventCategory;}


}

