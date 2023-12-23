package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FilterService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Business logic for working with filtering a set of flightBuilder
 * -----//-----
 * Бизнес-логика по работе с фильтрацией набора перелётов
 */

public class FilterServiceImpl implements FilterService {
    @Override
    public List<Flight> excludeFlightBeforeCurrentTime(List<Flight> flightBuilder) {
        if (flightBuilder != null) {
            return flightBuilder.stream()
                    .filter(flight -> flight.getSegments().stream()
                            .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Flight> excludeSegmentsWhereArrivalDateEarlierThanDepartureDate(List<Flight> flightBuilder) {
        List<Flight> result = new ArrayList<>();
        if (flightBuilder != null) {
            return flightBuilder.stream()
                    .filter(flight -> flight.getSegments().stream()
                            .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public List<Flight> excludeFlightWhereTimeOnEarthExceedsTwoHours(List<Flight> flightBuilder) {
        List<Flight> result = new ArrayList<>();
        if (flightBuilder != null) {
            result = flightBuilder.stream()
                    .filter(flight -> flight.getSegments().size() > 1)
                    .filter(flight -> {
                        long countHours = IntStream.range(1, flight.getSegments().size())
                                .map(i -> Math.toIntExact(checkTimeDifference(flight.getSegments().get(i - 1).getArrivalDate(),
                                        flight.getSegments().get(i).getDepartureDate())))
                                .sum();
                        return countHours <= 2;
                    })
                    .collect(Collectors.toList());
            result.addAll(flightBuilder.stream()
                    .filter(flight -> flight.getSegments().size() <= 1)
                    .toList());
            return result;
        }
        return result;
    }

    /**
     * A method for calculating the difference between two time intervals (arrival and departure)
     * -----//-----
     * Метод для вычисления разницы между двумя промежутками времени(прибытия и отбытия)
     */
    private long checkTimeDifference(LocalDateTime arrival, LocalDateTime departure) {
        return ChronoUnit.HOURS.between(arrival, departure);
    }
}
