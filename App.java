package com.homeservice1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.homeservice1.entity.Booking;
import com.homeservice1.entity.Feedback;
import com.homeservice1.entity.Payment;
import com.homeservice1.entity.Provider;
import com.homeservice1.entity.Service;
import com.homeservice1.entity.User;

import com.homeservice1.service.BookingService;
import com.homeservice1.service.FeedbackService;
import com.homeservice1.service.PaymentService;
import com.homeservice1.service.ProviderService;
import com.homeservice1.service.ServiceService;
import com.homeservice1.service.UserService;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Services (match your project)
    private static final UserService userService = new UserService();
    private static final ProviderService providerService = new ProviderService();
    private static final ServiceService serviceService = new ServiceService();
    private static final BookingService bookingService = new BookingService();
    private static final PaymentService paymentService = new PaymentService();
    private static final FeedbackService feedbackService = new FeedbackService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== HOME SERVICE APP =====");
            System.out.println("1. Register User");
            System.out.println("2. Register Provider");
            System.out.println("3. Add Service (Provider)");
            System.out.println("4. Book Service");
            System.out.println("5. Make Payment");
            System.out.println("6. Give Feedback");
            System.out.println("7. View All Users");
            System.out.println("8. View All Providers");
            System.out.println("9. View All Services");
            System.out.println("10. View All Bookings");
            System.out.println("11. View All Payments");
            System.out.println("12. View All Feedbacks");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    registerUser();
                    break;

                case 2:
                    registerProvider();
                    break;

                case 3:
                    addService();
                    break;

                case 4:
                    bookService();
                    break;

                case 5:
                    makePayment();
                    break;

                case 6:
                    addFeedback();
                    break;

                case 7:
                    userService.listAllUsers().forEach(System.out::println);
                    break;

                case 8:
                    providerService.getAllProviders().forEach(System.out::println);
                    break;

                case 9:
                    serviceService.getAllServices().forEach(System.out::println);
                    break;

                case 10:
                    bookingService.getAllBookings().forEach(System.out::println);
                    break;

                case 11:
                    paymentService.getAllPayments().forEach(System.out::println);
                    break;

                case 12:
                    feedbackService.getAllFeedbacks().forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ============ Actions (match your entity fields & service method names) ============

    private static void registerUser() {
        User u = new User();
        System.out.print("Name: ");
        u.setName(sc.nextLine().trim());

        System.out.print("Email: ");
        u.setEmail(sc.nextLine().trim());

        System.out.print("Password: ");
        u.setPassword(sc.nextLine().trim());

        System.out.print("Phone: ");
        u.setPhone(sc.nextLine().trim());

        System.out.print("Address: ");
        u.setAddress(sc.nextLine().trim());

        // Your role is a free string; for customers we use "USER"
        u.setRole("USER");

        userService.registerUser(u); // matches your UserService
    }

    private static void registerProvider() {
        Provider p = new Provider();

        System.out.print("Name: ");
        p.setName(sc.nextLine().trim());

        System.out.print("Email: ");
        p.setEmail(sc.nextLine().trim());

        System.out.print("Phone: ");
        p.setPhone(sc.nextLine().trim());

        System.out.print("Address: ");
        p.setAddress(sc.nextLine().trim());

        System.out.print("Specialization (e.g., Plumbing, Electrical): ");
        p.setSpecialization(sc.nextLine().trim());

        System.out.print("Available? (true/false): ");
        try {
            p.setAvailability(Boolean.parseBoolean(sc.nextLine().trim()));
        } catch (Exception e) {
            p.setAvailability(true);
        }

        providerService.addProvider(p); // matches your ProviderService
    }

    private static void addService() {
        // NOTE: Your Service entity has NO provider field; it’s a catalog entry.
        Service s = new Service();

        System.out.print("Service Name: ");
        s.setServiceName(sc.nextLine().trim());

        System.out.print("Description: ");
        s.setDescription(sc.nextLine().trim());

        System.out.print("Price: ");
        try {
            s.setPrice(Double.parseDouble(sc.nextLine().trim()));
        } catch (Exception e) {
            System.out.println("Invalid price.");
            return;
        }

        serviceService.addService(s); // matches your ServiceService
    }

    private static void bookService() {
        try {
            System.out.print("User ID: ");
            int userId = Integer.parseInt(sc.nextLine().trim());
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("User not found.");
                return;
            }

            System.out.print("Service ID: ");
            long serviceId = Long.parseLong(sc.nextLine().trim());
            Service service = serviceService.getService(serviceId);
            if (service == null) {
                System.out.println("Service not found.");
                return;
            }

            System.out.print("Provider ID: ");
            int providerId = Integer.parseInt(sc.nextLine().trim());
            Provider provider = providerService.getProvider(providerId);
            if (provider == null) {
                System.out.println("Provider not found.");
                return;
            }

            System.out.print("Scheduled Date-Time (yyyy-MM-dd HH:mm): ");
            LocalDateTime scheduled = LocalDateTime.parse(sc.nextLine().trim(), DTF);

            System.out.print("Notes (optional): ");
            String notes = sc.nextLine();

            Booking b = new Booking(
                    user,
                    service,
                    provider,
                    LocalDateTime.now(),   // bookingDate
                    scheduled,             // scheduledDate
                    "Pending",             // status
                    notes
            );

            bookingService.addBooking(b); // matches your BookingService
            System.out.println("✅ Booking created: " + b);

        } catch (Exception e) {
            System.out.println("Failed to create booking: " + e.getMessage());
        }
    }

    private static void makePayment() {
        try {
            System.out.print("Booking ID: ");
            int bookingId = Integer.parseInt(sc.nextLine().trim());
            Booking b = bookingService.getBooking(bookingId);
            if (b == null) {
                System.out.println("Booking not found.");
                return;
            }

            System.out.print("Amount: ");
            double amount = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Method (Cash/UPI/Card/NetBanking): ");
            String method = sc.nextLine().trim();

            Payment p = new Payment(
                    b,
                    amount,
                    method,
                    "Paid",
                    LocalDateTime.now()
            );

            paymentService.addPayment(p); // matches your PaymentService
            System.out.println("✅ Payment recorded: " + p);

        } catch (Exception e) {
            System.out.println("Failed to record payment: " + e.getMessage());
        }
    }

    private static void addFeedback() {
        try {
            System.out.print("Booking ID: ");
            int bookingId = Integer.parseInt(sc.nextLine().trim());
            Booking booking = bookingService.getBooking(bookingId);
            if (booking == null) {
                System.out.println("Booking not found.");
                return;
            }

            System.out.print("User ID (who gives feedback): ");
            int userId = Integer.parseInt(sc.nextLine().trim());
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("User not found.");
                return;
            }

            System.out.print("Provider ID (who is rated): ");
            int providerId = Integer.parseInt(sc.nextLine().trim());
            Provider provider = providerService.getProvider(providerId);
            if (provider == null) {
                System.out.println("Provider not found.");
                return;
            }

            System.out.print("Rating (1-5): ");
            int rating = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Comments: ");
            String comments = sc.nextLine();

            Feedback f = new Feedback(
                    booking,
                    user,
                    provider,
                    rating,
                    comments,
                    LocalDateTime.now()
            );

            feedbackService.addFeedback(f); // matches your FeedbackService
            System.out.println("✅ Feedback saved: " + f);

        } catch (Exception e) {
            System.out.println("Failed to save feedback: " + e.getMessage());
        }
    }
}
