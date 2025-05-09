package com.saltyFish.appointment.mapper;

import com.saltyFish.appointment.dto.BookingDetailsDto;
import com.saltyFish.appointment.entity.BookingDetails;

public class BookingDetailsMapper {

    public static BookingDetailsDto mapToBookingDetailsDto(BookingDetails bookingDetails, BookingDetailsDto bookingDetailsDto) {
        bookingDetailsDto.setServiceType(bookingDetails.getServiceType());
        bookingDetailsDto.setOwnerConfirmed(bookingDetails.isOwnerConfirmed());
        bookingDetailsDto.setRequesterConfirmed(bookingDetails.isRequesterConfirmed());
        bookingDetailsDto.setServiceType(bookingDetails.getServiceType());
        bookingDetailsDto.setStartTime(bookingDetails.getStartTime());
        bookingDetailsDto.setEndTime(bookingDetails.getEndTime());
        return bookingDetailsDto;
    }

    public static BookingDetails mapToBookingDetails(BookingDetailsDto bookingDetailsDto, BookingDetails bookingDetails) {
        bookingDetails.setServiceType(bookingDetailsDto.getServiceType());
        bookingDetails.setOwnerConfirmed(bookingDetailsDto.isOwnerConfirmed());
        bookingDetails.setRequesterConfirmed(bookingDetailsDto.isRequesterConfirmed());
        bookingDetails.setServiceType(bookingDetailsDto.getServiceType());
        bookingDetails.setStartTime(bookingDetailsDto.getStartTime());
        bookingDetails.setEndTime(bookingDetailsDto.getEndTime());
        return bookingDetails;
    }


}
