package ru.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.practicum.model.EndpointHit;
import ru.practicum.repository.StatsRepository;
import ru.practicum.service.StatsServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class StatsServiceIntegrationTests {

    @Autowired
    private StatsServiceImpl statsService;

    @Autowired
    private StatsRepository statsRepository;

    @BeforeEach
    void setUp() {
        statsRepository.deleteAll();  // Очищаем БД перед каждым тестом
    }

    @Test
    void shouldAddHitAndReturnDto() {

        LocalDateTime timestamp = LocalDateTime.parse(
                "2025-12-19T10:00:00",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        );
        // Подготавливаем тестовые данные
        EndpointHitDto inputDto = EndpointHitDto.builder()
                .app("testApp")
                .uri("/test")
                .ip("127.0.0.1")
                .timestamp(timestamp)
                .build();

        // Вызываем метод сервиса
        EndpointHitDto resultDto = statsService.addHit(inputDto);

        // Проверяем, что запись сохранилась в БД
        EndpointHit savedEntity = statsRepository.findById(resultDto.getId())
                .orElseThrow(() -> new AssertionError("Entity not found in repository"));


        // Сверяем поля DTO и сущности
        assertThat(resultDto.getApp()).isEqualTo(inputDto.getApp());
        assertThat(resultDto.getUri()).isEqualTo(inputDto.getUri());
        assertThat(resultDto.getIp()).isEqualTo(inputDto.getIp());
        assertThat(resultDto.getTimestamp()).isEqualTo(inputDto.getTimestamp());


        assertThat(savedEntity.getApp()).isEqualTo(inputDto.getApp());
        assertThat(savedEntity.getUri()).isEqualTo(inputDto.getUri());
        assertThat(savedEntity.getIp()).isEqualTo(inputDto.getIp());
        assertThat(savedEntity.getTimestamp()).isEqualTo(inputDto.getTimestamp());


        // Проверяем, что ID сгенерирован (не null)
        assertThat(resultDto.getId()).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
    }

    @Test
    void shouldPersistHitInRepository() {
        LocalDateTime timestamp = LocalDateTime.parse(
                "2025-12-19T10:00:00",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        );
        EndpointHitDto dto = EndpointHitDto.builder()
                .app("anotherApp")
                .uri("/another")
                .ip("192.168.0.1")
                .timestamp(timestamp)
                .build();

        statsService.addHit(dto);


        // Проверяем количество записей в репозитории
        long count = statsRepository.count();
        assertThat(count).isEqualTo(1L);

        // Проверям конкретную запись
        EndpointHit entity = statsRepository.findAll().getFirst();
        assertThat(entity.getApp()).isEqualTo("anotherApp");
        assertThat(entity.getUri()).isEqualTo("/another");
        assertThat(entity.getIp()).isEqualTo("192.168.0.1");
    }
}
