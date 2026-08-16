package com.hms.demo.api.rooms;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")

public class RoomsController {

	private final RoomService serv;

	public RoomsController(RoomService serv) {
		this.serv = serv;
	}

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<List<Room>> getRoomList() {
		return ResponseEntity.ok(serv.listRooms());
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> postRoomHandler(@RequestBody Room room) {
		serv.saveRoom(room);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> postRoomHandlerUrlEncoded(@RequestBody Room room) {
		serv.saveRoom(room);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
