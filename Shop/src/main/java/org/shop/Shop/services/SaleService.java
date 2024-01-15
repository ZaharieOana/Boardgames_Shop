package org.shop.Shop.services;

import org.shop.Shop.models.Sale;
import org.shop.Shop.repos.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Iterable<Sale> findAll() {
        return saleRepository.findAll();
    }

    public void save(Sale newSale) {
        saleRepository.save(newSale);
    }
}
