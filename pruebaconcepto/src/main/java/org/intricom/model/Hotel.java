package org.intricom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelBooking> bookings;

    @Override
    public String toString() {
        return "Hotel{id=" + id + ", name='" + name + "', address='" + address + "', createdDate=" + createdDate + "}";
    }
}

