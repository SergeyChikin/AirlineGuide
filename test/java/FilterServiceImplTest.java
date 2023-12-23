import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.impl.FilterServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterServiceImplTest {

    FilterServiceImpl filterServiceTest = new FilterServiceImpl();
    Segment testOne = new Segment(LocalDateTime.of(2023, 12, 23, 2, 10),
            LocalDateTime.of(2023, 12, 23, 10, 10));
    Segment testTwo = new Segment(LocalDateTime.of(2023, 12, 20, 5, 30),
            LocalDateTime.of(2023, 12, 20, 6, 0));
    Segment testThree = new Segment(LocalDateTime.of(2023, 9, 9, 5, 30),
            LocalDateTime.of(2023, 9, 9, 10, 0));
    Segment testFour = new Segment(LocalDateTime.of(2023, 4, 8, 5, 30),
            LocalDateTime.of(2023, 4, 7, 1, 0));

    List<Segment> segmentList = List.of(testOne);
    List<Segment> segmentListTwo = new ArrayList<>();
    List<Segment> segmentListThree = List.of(testTwo);
    List<Segment> segmentListFour = new ArrayList<>();
    List<Segment> segmentListFive = new ArrayList<>();


    Flight testFlight = new Flight(segmentList);
    Flight testFlightTwo = new Flight(segmentListTwo);
    Flight testFlightThree = new Flight(segmentListThree);
    Flight testFlightFour = new Flight(segmentListFour);
    Flight testFlightFive = new Flight(segmentListFive);

    List<Flight> expected = new ArrayList<>();
    List<Flight> actual = new ArrayList<>();

    @BeforeEach
    void addSegment() {
        segmentListTwo.add(testThree);
        segmentListFour.add(testFour);
        segmentListFive.add(testThree);
        segmentListFive.add(testTwo);
    }

    @AfterEach
    void clearFlight() {
        expected.clear();
        actual.clear();
    }

    @Test
    void excludeFlightBeforeCurrentTimeTest() {
        expected.add(testFlight);
        actual.add(testFlight);
        List<Flight> result = filterServiceTest.excludeFlightBeforeCurrentTime(actual);
        assertEquals(result, expected);
    }

    @Test
    void excludeSegmentsWhereArrivalDateEarlierThanDepartureDateTest() {
        expected.add(testFlightTwo);
        actual.add(testFlightFour);
        actual.add(testFlightTwo);
        List<Flight> result = filterServiceTest.
                excludeSegmentsWhereArrivalDateEarlierThanDepartureDate(actual);
        assertEquals(result, expected);
    }

    @Test
    void excludeFlightWhereTimeOnEarthExceedsTwoHoursTest() {
        expected.add(testFlightThree);
        actual.add(testFlightFive);
        actual.add(testFlightThree);
        List<Flight> result = filterServiceTest.
                excludeFlightWhereTimeOnEarthExceedsTwoHours(actual);
        assertEquals(result, expected);
    }

}
