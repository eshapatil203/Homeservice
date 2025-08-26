package com.homeservice1.service;

import java.util.List;
import com.homeservice1.dao.*;
import com.homeservice1.entity.Feedback;

public class FeedbackService {
    private FeedbackDAO dao = new FeedbackDAO();

    public void addFeedback(Feedback feedback) { dao.save(feedback); }
    public Feedback getFeedback(int id) { return dao.getById(id); }
    public List<Feedback> getAllFeedbacks() { return dao.getAll(); }
    public void updateFeedback(Feedback feedback) { dao.update(feedback); }
    public void deleteFeedback(int id) { dao.delete(id); }
}
