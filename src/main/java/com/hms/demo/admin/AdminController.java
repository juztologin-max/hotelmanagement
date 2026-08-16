package com.hms.demo.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hms.demo.api.rooms.Room;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/rooms")
	public String getRoomHandler(Model model) {
		model.addAttribute("roomObject", new Room());
		model.addAttribute("roomTier", Room.RoomTier.values());
		model.addAttribute("roomState", Room.RoomState.values());
		model.addAttribute("roomCapacity", Room.RoomCapacity.values());

		return "admin/rooms/rooms";
	}

}
