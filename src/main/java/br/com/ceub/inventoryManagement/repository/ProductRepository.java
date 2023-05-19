package br.com.ceub.inventoryManagement.repository;

import br.com.ceub.inventoryManagement.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product getByName(String name);
}
