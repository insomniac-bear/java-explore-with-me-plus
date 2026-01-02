package ru.practicum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.AdminEventParam;
import ru.practicum.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE "
            + "(:users IS NULL OR e.initiator.id IN :users) "
            + "AND (:states IS NULL OR e.state IN :states) "
            + "AND (:categories IS NULL OR e.category IN :categories) "
            + "AND (:rangeStart IS NULL OR e.eventDate >= :rangeStart) "
            + "AND (:rangeEnd IS NULL OR e.eventDate <= :rangeEnd)")
    List<Event> getAdminEvents(AdminEventParam param, Pageable pageable);

}
