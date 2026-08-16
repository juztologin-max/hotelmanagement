package com.hms.demo.api.rooms;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RoomService {
	private final RoomRepo repo; 
	
	public RoomService(RoomRepo repo) {
		this.repo=repo;
	}
	
	
	public void saveRoom(Room room) {
		repo.save(room);
	}
	
	public List<Room> listRooms() {
		return repo.findAll();
	}
	
	
}
