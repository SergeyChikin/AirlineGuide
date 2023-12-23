package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Factory class to get sample list of flights.
 *  -----//-----
 *  Фабричный класс, чтобы получить примерный список рейсов.
 */
public class FlightBuilder {
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
            //A normal flight with two hour duration
            //Обычный рейс продолжительностью два часа
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
            //A normal multi segment flight
            //Обычный многосегментный перелёт
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
            //A flight departing in the past
            //Рейс, вылетавший в прошлом
            createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
            //A flight that departs before it arrives
            //Рейс, который вылетает раньше, чем прибывает
            createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
            //A flight with more than two hours ground time
            //Полёт с более чем двухчасовым наземным временем
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
            //Another flight with more than two hours ground time
            //Другой рейс с более чем двучасовым наземным временем
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}

