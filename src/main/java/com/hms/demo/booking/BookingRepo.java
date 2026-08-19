package com.hms.demo.booking;

import java.time.LocalDateTime;
import java.util.List;
import com.hms.demo.common.loginlogout.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    public List<Booking> findByUserAndOccupancyEndGreaterThanEqual(LoginUser user, LocalDateTime dateTime);

    public List<Booking> findByUserAndOccupancyEndLessThanEqual(LoginUser user, LocalDateTime dateTime);

}
