package com.hms.demo.booking;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.hms.demo.api.rooms.Room;
import com.hms.demo.common.loginlogout.LoginUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id", nullable = false, referencedColumnName = "id")
	private Room room;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private LoginUser user;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime occupancyStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(nullable = false)
	private LocalDateTime occupancyEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}

	public LocalDateTime getOccupancyStart() {
		return occupancyStart;
	}

	public void setOccupancyStart(LocalDateTime occupancyStart) {
		this.occupancyStart = occupancyStart;
	}

	public LocalDateTime getOccupancyEnd() {
		return occupancyEnd;
	}

	public void setOccupancyEnd(LocalDateTime occupancyEnd) {
		this.occupancyEnd = occupancyEnd;
	}

}
