package com.hms.demo.booking;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.demo.common.loginlogout.LoginUser;

@Service
public class BookingService {

	private final BookingRepo repo;

	public BookingService(BookingRepo repo) {
		this.repo = repo;
	}

	public void saveBooking(Booking booking) {
		repo.save(booking);
	}

	public List<Booking> getCurrentBookings(LoginUser user) {
		return repo.findByUserAndOccupancyEndGreaterThanEqual(user, LocalDateTime.now());
	}

	public List<Booking> getPastBookings(LoginUser user) {
		return repo.findByUserAndOccupancyEndLessThanEqual(user, LocalDateTime.now());
	}

	public List<Booking> getAllBookings() {
		return repo.findAll();
	}

	public Booking getBookingWithId(Long id) throws Exception {
		return repo.findById(id).orElseThrow(() -> new Exception("Booking with id: " + id + " not found"));
	}
}
