package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

/**
 *  Flight set filtering service
 *  -----//-----
 *  Сервис фильтрации набора перелётов
 */
public interface FilterService {

    /**
     * The method of excluding departures up to the current time from the general list
     * -----//-----
     * Метод исключения вылетов до текущего момента времени из общего списка
     */
    List<Flight> excludeFlightBeforeCurrentTime(List<Flight> flightBuilder);

    /**
     * The method of excluding flights, where segments with an arrival date
     * earlier than the departure date from the general list
     * -----//-----
     * Метод исключения перелетов, где сегменты с датой прилёта
     * раньше даты вылета из общего списка
     */
    List<Flight> excludeSegmentsWhereArrivalDateEarlierThanDepartureDate(List<Flight> flightBuilder);

    /**
     * A method of excluding flights where the total time spent on the ground exceeds two hours
     * -----//-----
     * Метод исключения перелетов, где общее время, проведённое на земле, превышает два часа
     */
    List<Flight> excludeFlightWhereTimeOnEarthExceedsTwoHours(List<Flight> flightBuilder);

}
