package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.util.EventState;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEventParam {
    List<Integer> usersIds;
    List<EventState> states;
    List<Category> categories;
    Timestamp rangeStart;
    Timestamp rangeEnd;
}
