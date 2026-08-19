package com.hms.demo.api.rooms;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	private final RoomRepo repo;

	public RoomService(RoomRepo repo) {
		this.repo = repo;
	}

	public void saveRoom(Room room) {
		repo.save(room);
	}

	public List<Room> listRooms() {
		return repo.findAll();
	}

	public Optional<Room> getRoomWithId(Long id) {
		return repo.findById(id);
	}

	public void deleteRoomWithId(Long id) {
		repo.deleteById(id);
	}

	public Optional<Room> findFirst(Room room) {
		return repo.findAll(Example.of(room), PageRequest.of(0, 1)).get().findFirst();
	}

}
