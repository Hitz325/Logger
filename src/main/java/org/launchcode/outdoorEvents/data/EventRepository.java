package org.launchcode.outdoorEvents.data;

import org.launchcode.outdoorEvents.models.Event;
import org.launchcode.outdoorEvents.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    User findByName(String name);
    List<Event> findAllByUser_Id(Integer userId);
}
