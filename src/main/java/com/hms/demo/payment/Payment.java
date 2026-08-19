package com.hms.demo.payment;

import java.time.LocalDateTime;

import com.hms.demo.booking.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = false)
	Booking booking;

	Double amount;

	LocalDateTime datetime;
}
