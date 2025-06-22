package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilmsManagerTest {
    @Test
    void shouldAddFilmsAndFindAll() {
        FilmsManager manager = new FilmsManager();
        manager.addFilm("Фильм Бладшот (боевик)");
        manager.addFilm("Фильм Вперед (мультик)");

        String[] expected = {"Фильм Бладшот (боевик)", "Фильм Вперед (мультик)"};
        Assertions.assertArrayEquals(expected, manager.findAll());
    }

    @Test
    void shouldFindLastWhenLessThanDefaultLimit() {
        FilmsManager manager = new FilmsManager();
        manager.addFilm("Фильм Бладшот (боевик)");
        manager.addFilm("Фильм Вперед (мультик)");

        String[] expected = {"Фильм Вперед (мультик)", "Фильм Бладшот (боевик)"};
        Assertions.assertArrayEquals(expected, manager.findLast());
    }

    @Test
    void shouldFindLastWhenMoreThanDefaultLimit() {
        FilmsManager manager = new FilmsManager();
        String[] films = {"Фильм Бладшот (боевик)", "Фильм Вперед (мультик)", "Фильм Отель Белград (комедия)", "Фильм Джентльмены (боевик)", "Фильм Человек-неведимка (ужасы)", "Фильм Тролли.Мировой тур (мультик)"};
        for (String film : films) {
            manager.addFilm(film);
        }

        String[] expected = {"Фильм Тролли.Мировой тур (мультик)", "Фильм Человек-неведимка (ужасы)", "Фильм Джентльмены (боевик)", "Фильм Отель Белград (комедия)", "Фильм Вперед (мультик)"};
        Assertions.assertArrayEquals(expected, manager.findLast());
    }

    @Test
    void shouldFindLastWithCustomLimit() {
        FilmsManager manager = new FilmsManager(2);
        manager.addFilm("Фильм Бладшот (боевик)");
        manager.addFilm("Фильм Вперед (мультик)");
        manager.addFilm("Фильм Отель Белград (комедия)");
        manager.addFilm("Фильм Джентльмены (боевик)");

        String[] expected = {"Фильм Джентльмены (боевик)", "Фильм Отель Белград (комедия)"};
        Assertions.assertArrayEquals(expected, manager.findLast());
    }

    @Test
    void shouldFindLastWhenExactLimit() {
        FilmsManager manager = new FilmsManager(2);
        manager.addFilm("Фильм Бладшот (боевик)");
        manager.addFilm("Фильм Вперед (мультик)");

        String[] expected = {"Фильм Вперед (мультик)", "Фильм Бладшот (боевик)"};
        Assertions.assertArrayEquals(expected, manager.findLast());
    }

    @Test
    void shouldHandleEmptyManager() {
        FilmsManager manager = new FilmsManager();
        Assertions.assertArrayEquals(new String[0], manager.findAll());
        Assertions.assertArrayEquals(new String[0], manager.findLast());
    }

    @Test
    void shouldHandleSingleFilm() {
        FilmsManager manager = new FilmsManager();
        manager.addFilm("Фильм Бладшот (боевик)");

        String[] expectedAll = {"Фильм Бладшот (боевик)"};
        String[] expectedLast = {"Фильм Бладшот (боевик)"};

        Assertions.assertArrayEquals(expectedAll, manager.findAll());
        Assertions.assertArrayEquals(expectedLast, manager.findLast());
    }

    @Test
    void shouldUseCustomLimitWhenFilmsLessThanLimit() {
        FilmsManager manager = new FilmsManager(10);
        manager.addFilm("Фильм Бладшот (боевик)");
        manager.addFilm("Фильм Вперед (мультик)");
        manager.addFilm("Фильм Отель Белград (комедия)");

        String[] expected = {"Фильм Отель Белград (комедия)","Фильм Вперед (мультик)", "Фильм Бладшот (боевик)"};
        Assertions.assertArrayEquals(expected, manager.findLast());
    }
}