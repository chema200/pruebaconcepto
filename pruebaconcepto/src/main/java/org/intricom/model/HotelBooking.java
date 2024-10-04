package org.intricom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotel_booking")
@Data
@NoArgsConstructor
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false) // FK a Hotel
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // FK a Client
    private Client client;

    @Override
    public String toString() {
        return "HotelBooking{id=" + id + ", name='" + name + "', address='" + address + "', createdDate=" + createdDate + "}";
    }
}

