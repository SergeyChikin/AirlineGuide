package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.impl.FilterServiceImpl;

import java.util.List;

public class Main {

    private static final FilterServiceImpl filterService = new FilterServiceImpl();
    private static final List<Flight> flightList = FlightBuilder.createFlights();

    public static void main(String[] args) {
        // initial list of flights
        // изначальный список перелетов
        allFlight();

        // displaying flights with current departure dates
        // вывод перелетов с актуальными датами вылетов
        excludeFlightBeforeCurrentTime();

        // withdrawal of flights without segments with an arrival date earlier than the departure date
        // вывод перелетов без сегментов с датой прилёта раньше даты вылета
        excludeSegmentsWhereArrivalDateEarlierThanDepartureDate();

        // withdrawal of flights without transfers and / or with transfers no more than 2 hours
        // вывод перелетов без пересадок и/или с пересадками не более 2-х часов
        excludeFlightWhereTimeOnEarthExceedsTwoHours();
    }

    private static void allFlight() {
        separator();
        System.out.println(flightList);
    }

    private static void excludeFlightBeforeCurrentTime() {
        separator();
        System.out.println(filterService.excludeFlightBeforeCurrentTime(flightList));
    }

    private static void excludeSegmentsWhereArrivalDateEarlierThanDepartureDate() {
        separator();
        System.out.println(filterService.excludeSegmentsWhereArrivalDateEarlierThanDepartureDate(flightList));
    }

    private static void excludeFlightWhereTimeOnEarthExceedsTwoHours() {
        separator();
        System.out.println(filterService.excludeFlightWhereTimeOnEarthExceedsTwoHours(flightList));
    }

    private static void separator() {
        System.out.println("-----//-----");
    }

}
