package com.hms.demo.api.rooms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
	public enum RoomTier {
		BASIC, PREMIUM
	}

	public enum RoomCapacity {
		SINGLE, DOUBLE
	}

	public enum RoomState {
		FREE, OCCUPIED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomTier tier;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomCapacity capacity;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomState state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomTier getTier() {
		return tier;
	}

	public void setTier(RoomTier tier) {
		this.tier = tier;
	}

	public RoomCapacity getCapacity() {
		return capacity;
	}

	public void setCapacity(RoomCapacity capacity) {
		this.capacity = capacity;
	}

	public RoomState getState() {
		return state;
	}

	public void setState(RoomState state) {
		this.state = state;
	}

}
