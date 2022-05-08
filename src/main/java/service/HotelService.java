package service;

import domain.Hotel;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelService {

    public static final ArrayList<Hotel> LIST_ALL = new ArrayList<>(
            Arrays.asList(
                    Hotel.builder()
                            .name("Lakewood")
                            .rating(3)
                            .regularWeekPrice(110.0)
                            .rewardsWeekPrice(80.0)
                            .regularWeekendPrice(90.0)
                            .rewardsWeekendPrice(80.0)
                            .build(),
                    Hotel.builder()
                            .name("Bridgewood")
                            .rating(4)
                            .regularWeekPrice(160.0)
                            .rewardsWeekPrice(110.0)
                            .regularWeekendPrice(60.0)
                            .rewardsWeekendPrice(50.0)
                            .build(),
                    Hotel.builder()
                            .name("Ridgewood")
                            .rating(5)
                            .regularWeekPrice(220.0)
                            .rewardsWeekPrice(100.0)
                            .regularWeekendPrice(150.0)
                            .rewardsWeekendPrice(40.0)
                            .build())
    );
}
