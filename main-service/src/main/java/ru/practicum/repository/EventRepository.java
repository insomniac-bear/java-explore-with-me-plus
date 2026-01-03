package ru.practicum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.model.AdminEventParam;
import ru.practicum.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE (:#{#param.users} IS NULL OR e.initiator.id IN :#{#param.users}) " +
            "  AND (:#{#param.states} IS NULL OR e.state IN :#{#param.states}) " +
            "  AND (:#{#param.categories} IS NULL OR e.category.id IN :#{#param.categories}) " +
            "  AND (:#{#param.rangeStart} IS NULL OR e.eventDate >= :#{#param.rangeStart}) " +
            "  AND (:#{#param.rangeEnd} IS NULL OR e.eventDate <= :#{#param.rangeEnd})")
    List<Event> getAdminEvents(@Param("param") AdminEventParam param, Pageable pageable);

}
