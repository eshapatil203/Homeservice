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
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 🔹 Feedback is always linked to a booking
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    // 🔹 User who gave feedback
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 🔹 Provider who is rated
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private int rating; // Example: 1 to 5 stars

    @Column(length = 500)
    private String comments;

    @Column(nullable = false)
    private LocalDateTime feedbackDate;

    // ✅ No-arg constructor
    public Feedback() {}

    // ✅ All-args constructor
    public Feedback(Booking booking, User user, Provider provider, int rating, String comments, LocalDateTime feedbackDate) {
        this.booking = booking;
        this.user = user;
        this.provider = provider;
        this.rating = rating;
        this.comments = comments;
        this.feedbackDate = feedbackDate;
    }

    // ✅ Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) { this.provider = provider; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDateTime getFeedbackDate() { return feedbackDate; }
    public void setFeedbackDate(LocalDateTime feedbackDate) { this.feedbackDate = feedbackDate; }

    // ✅ toString()
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", bookingId=" + booking.getId() +
                ", userId=" + user.getUserId() +
                ", providerId=" + provider.getId() +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
