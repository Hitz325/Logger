package org.launchcode.outdoorEvents.data;

import org.launchcode.outdoorEvents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.launchcode.outdoorEvents.models.*;


@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer>{
    User findByName(String name);
}
