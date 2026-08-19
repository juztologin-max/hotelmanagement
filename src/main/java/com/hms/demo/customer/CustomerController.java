package com.hms.demo.customer;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hms.demo.api.rooms.Room;
import com.hms.demo.api.rooms.RoomService;
import com.hms.demo.booking.Booking;
import com.hms.demo.booking.BookingService;
import com.hms.demo.common.loginlogout.LoginUser;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final BookingService bookingServ;
    private final RoomService roomServ;

    public CustomerController(BookingService booking, RoomService room) {
        this.bookingServ = booking;
        this.roomServ = room;
    }

    @GetMapping("/booking")
    public String getRoomHandler(Model model, @AuthenticationPrincipal LoginUser user) {
        Booking booking = new Booking();
        booking.setRoom(new Room());
        booking.setOccupancyStart(LocalDateTime.now());
        booking.setOccupancyEnd(LocalDateTime.now().plusDays(1));
        model.addAttribute("bookingObject", booking);
        model.addAttribute("roomTier", Room.RoomTier.values());
        model.addAttribute("roomState", Room.RoomState.values());
        model.addAttribute("roomCapacity", Room.RoomCapacity.values());
        model.addAttribute("currentBookings", bookingServ.getCurrentBookings(user));
        model.addAttribute("pastBookings", bookingServ.getPastBookings(user));
        return "customer/booking/booking";
    }

    @PostMapping("/booking")
    public String postRoomHandler(@ModelAttribute Booking booking, @AuthenticationPrincipal LoginUser user)
            throws Exception {

        Room room = roomServ.findFirst(booking.getRoom()).orElseThrow(() -> new Exception("Room not Found"));
        room.setState(Room.RoomState.OCCUPIED);
        booking.setUser(user);
        booking.setRoom(room);
        bookingServ.saveBooking(booking);
        return "redirect:/customer/booking";
    }
}
