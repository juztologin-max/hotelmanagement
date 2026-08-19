package com.hms.demo.admin;

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

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final RoomService serv;

	public AdminController(RoomService serv) {
		this.serv = serv;
	}

	@GetMapping("/rooms")
	public String getRoomHandler(Model model) {
		if (model.getAttribute("roomObject") == null) {
			Room room = new Room();
			room.setState(Room.RoomState.FREE);
			model.addAttribute("roomObject", room);

		}
		model.addAttribute("roomTier", Room.RoomTier.values());
		model.addAttribute("roomState", Room.RoomState.values());
		model.addAttribute("roomCapacity", Room.RoomCapacity.values());
		model.addAttribute("rooms", serv.listRooms());
		return "admin/rooms/rooms";
	}

	@PostMapping("/rooms")
	public String postRoomHandler(@ModelAttribute Room room) {
		serv.saveRoom(room);
		return "redirect:/admin/rooms";
	}

	@GetMapping("/rooms/{id}")
	public String editRoomHandler(@PathVariable Long id, RedirectAttributes model) throws Exception {
		model.addFlashAttribute("roomObject",
				serv.getRoomWithId(id).orElseThrow(() -> new Exception("Room with id: " + id + "not found")));
		return "redirect:/admin/rooms";
	}

	@GetMapping("/rooms/delete/{id}")
	public String deleteRoomHandler(@PathVariable Long id, RedirectAttributes model) throws Exception {
		serv.deleteRoomWithId(id);
		return "redirect:/admin/rooms";
	}

}
