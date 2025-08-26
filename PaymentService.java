package com.homeservice1.service;

import java.util.List;
import com.homeservice1.dao.*;
import com.homeservice1.entity.Payment;

public class PaymentService {
    private PaymentDAO dao = new PaymentDAO();

    public void addPayment(Payment payment) { dao.save(payment); }
    public Payment getPayment(int id) { return dao.getById(id); }
    public List<Payment> getAllPayments() { return dao.getAll(); }
    public void updatePayment(Payment payment) { dao.update(payment); }
    public void deletePayment(int id) { dao.delete(id); }
}
