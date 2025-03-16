package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.client.Address;
import com.saltyFish.user.client.Review;
import com.saltyFish.user.external.Appointment;

import java.util.ArrayList;
import java.util.List;

public class ProviderProfile extends UserProfile {

    private List<Review> reviews = new ArrayList<>();
//    private PaymentAccount paymentAccount;
//    private List<Payment> incomeHistory = new ArrayList<>();

    public ProviderProfile() {
    }

    public ProviderProfile(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ProviderProfile(String gender, String birthDate, String last4SSN, List<Appointment> appointments, List<Address> addresses, Double balance, List<Review> reviews) {
        super(gender, birthDate, last4SSN, appointments, addresses, balance);
        this.reviews = reviews;
    }
}
