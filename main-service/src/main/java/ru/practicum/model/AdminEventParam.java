package ru.practicum.model;

import lombok.Data;
import ru.practicum.util.EventState;

import java.sql.Timestamp;
import java.util.List;

@Data

public class AdminEventParam {
    List<Integer> usersIds;
    List<EventState> states;
    List<Category> categories;
    Timestamp rangeStart;
    Timestamp rangeEnd;
}
