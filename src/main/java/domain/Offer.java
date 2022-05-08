package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {

    private Hotel hotel;
    private Double amount;

}
