package com.homeservice1.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 🔹 One booking has one payment
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, length = 20)
    private String method; // e.g. Cash, UPI, Credit Card, Net Banking

    @Column(nullable = false, length = 20)
    private String status; // Pending, Paid, Failed, Refunded

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    // ✅ No-arg constructor (Hibernate needs this)
    public Payment() {}

    // ✅ All-args constructor
    public Payment(Booking booking, double amount, String method, String status, LocalDateTime paymentDate) {
        this.booking = booking;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // ✅ Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    // ✅ toString()
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bookingId=" + booking.getId() +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
