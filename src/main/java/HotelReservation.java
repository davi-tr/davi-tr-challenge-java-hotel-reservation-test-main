import domain.Offer;
import enums.CustomerTypeEnum;
import domain.Hotel;
import service.HotelService;
import util.DateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.MAX_VALUE;
import static util.ConstantsUtil.*;

public class HotelReservation {

    public String getCheapestHotel(final String input) {
        String customerTypeString = input.split(":")[FIRST_INDEX];
        boolean isRewards = CustomerTypeEnum.REWARDS.name().equalsIgnoreCase(customerTypeString);
        String datesString = input.split(":")[SECOND_INDEX];
        List<LocalDate> dateList = getDatesFromInput(datesString);

        Hotel firstHotel = HotelService.LIST_ALL
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The hotel list can't be empty!"));
        Offer currentlyOffer = Offer.builder()
                .hotel(firstHotel)
                .amount(MAX_VALUE)
                .build();

        for (Hotel hotel : HotelService.LIST_ALL) {
            Double amount = Double.MIN_VALUE;
            for (LocalDate localDate : dateList) {
                amount += sumValues(localDate, hotel, isRewards);
            }
            Offer suggestedOffer = Offer.builder()
                    .hotel(hotel)
                    .amount(amount)
                    .build();
            currentlyOffer = this.getBestOffer(currentlyOffer, suggestedOffer);
        }
        return currentlyOffer.getHotel().getName();
    }

    private Offer getBestOffer(final Offer currentlyOffer, final Offer suggestedOffer) {
        Double currentlyOfferAmount = currentlyOffer.getAmount();
        Double suggestedOfferAmount = suggestedOffer.getAmount();
        if (suggestedOfferAmount < currentlyOfferAmount) {
            return suggestedOffer;
        } else if (suggestedOfferAmount.equals(currentlyOfferAmount)) {
            Integer currentlyOfferHotelRating = currentlyOffer.getHotel().getRating();
            Integer suggestedOfferHotelRating = suggestedOffer.getHotel().getRating();
            return currentlyOfferHotelRating > suggestedOfferHotelRating ?
                    currentlyOffer : suggestedOffer;
        }
        return currentlyOffer;
    }

    private Double sumValues(final LocalDate date, final Hotel hotel, final boolean isRewards) {
        if (isRewards) {
            return DateUtil.isWeekend(date) ?
                    hotel.getRewardsWeekendPrice() : hotel.getRewardsWeekPrice();
        } else {
            return DateUtil.isWeekend(date) ?
                    hotel.getRegularWeekendPrice() : hotel.getRegularWeekPrice();
        }
    }

    private static List<LocalDate> getDatesFromInput(final String datesString) {
        String flatDatesString = datesString.replaceAll("\\s+", "");
        String stringDates = flatDatesString.replaceAll("\\([a-zA-Z]+\\)","");
        String[] listStringDates = stringDates.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH);
        return Arrays.stream(listStringDates)
                .map(date -> LocalDate.parse(date, formatter))
                .collect(Collectors.toList());
    }

}
