package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {

    private String name;
    private Integer rating;
    private Double regularWeekPrice;
    private Double rewardsWeekPrice;
    private Double regularWeekendPrice;
    private Double rewardsWeekendPrice;

}
