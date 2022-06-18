package pl.akepa.sales;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReservationStorage {
    Map<String, Reservation> reservations;

    public ReservationStorage() {
        this.reservations = new HashMap<>();
    }

    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public Optional<Reservation> find(String reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }
}