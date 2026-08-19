package com.hms.demo.staff;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hms.demo.api.rooms.Room;
import com.hms.demo.api.rooms.RoomService;
import com.hms.demo.booking.Booking;
import com.hms.demo.booking.BookingService;
import com.hms.demo.common.loginlogout.LoginUser;

@Controller
@RequestMapping("/staff")
public class StaffController {
	private final BookingService bookingServ;
	private final RoomService roomServ;

	public StaffController(BookingService booking, RoomService room) {
		this.bookingServ = booking;
		this.roomServ = room;
	}

	@GetMapping("/booking")
	public String getRoomHandler(Model model, @AuthenticationPrincipal LoginUser user) {
		Booking booking = (Booking) model.getAttribute("bookingObject");
		model.addAttribute("bookingObject", booking);
		model.addAttribute("roomTier", Room.RoomTier.values());
		model.addAttribute("roomState", Room.RoomState.values());
		model.addAttribute("roomCapacity", Room.RoomCapacity.values());
		model.addAttribute("currentBookings", bookingServ.getAllBookings());
		return "staff/booking/booking";
	}

	@GetMapping("/booking/{id}")
	public String getRoomHandler(@PathVariable Long id, RedirectAttributes model) throws Exception {
		Booking booking = bookingServ.getBookingWithId(id);
		model.addFlashAttribute("bookingObject", booking);
		return "redirect:/staff/booking";
	}

	@PostMapping("/booking")
	public String putRoomHandler(@ModelAttribute Booking booking) {
		return "redirect:/staff/booking";
	}

}
