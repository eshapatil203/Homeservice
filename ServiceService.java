package com.homeservice1.service;


import java.util.List;
import com.homeservice1.dao.*;
import com.homeservice1.entity.*;


public class ServiceService {
    private ServiceDao dao = new ServiceDao();

    public void addService(Service service) { dao.save(service); }
    public Service getService(Long id) { return dao.getById(id); }
    public List<Service> getAllServices() { return dao.getAll(); }
    public void updateService(Service service) { dao.update(service); }
    public void deleteService(Long id) { dao.delete(id); }
}
