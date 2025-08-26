package com.homeservice1.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 🔹 Many bookings can belong to one User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 🔹 Many bookings can be for one Service
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    // 🔹 Many bookings can be assigned to one Provider
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private LocalDateTime bookingDate; // when booking was made

    @Column(nullable = false)
    private LocalDateTime scheduledDate; // when service is scheduled

    @Column(nullable = false, length = 20)
    private String status; // Pending, Confirmed, Completed, Cancelled

    @Column(length = 500)
    private String notes; // any special instructions

    // 🔹 No-arg constructor (required by Hibernate)
    public Booking() {}

    // 🔹 All-args constructor
    public Booking(User user, Service service, Provider provider,
                   LocalDateTime bookingDate, LocalDateTime scheduledDate,
                   String status, String notes) {
        this.user = user;
        this.service = service;
        this.provider = provider;
        this.bookingDate = bookingDate;
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.notes = notes;
    }

    // 🔹 Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }

    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) { this.provider = provider; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // 🔹 toString()
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user.getUserId() +
                ", service=" + service.getId() +
                ", provider=" + provider.getId() +
                ", bookingDate=" + bookingDate +
                ", scheduledDate=" + scheduledDate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
