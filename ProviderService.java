package com.homeservice1.service;

import java.util.List;
import com.homeservice1.dao.*;

import com.homeservice1.entity.Provider;

public class ProviderService {
    private ProviderDAO dao = new ProviderDAO();

    public void addProvider(Provider provider) { dao.save(provider); }
    public Provider getProvider(int id) { return dao.getById(id); }
    public List<Provider> getAllProviders() { return dao.getAll(); }
    public void updateProvider(Provider provider) { dao.update(provider); }
    public void deleteProvider(int id) { dao.delete(id); }
}
