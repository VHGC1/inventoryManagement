package br.com.ceub.inventoryManagement.service;

import br.com.ceub.inventoryManagement.domain.dto.ProductCreateRequest;
import br.com.ceub.inventoryManagement.domain.entities.Product;
import br.com.ceub.inventoryManagement.repository.ProductRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Product> createProduct(ProductCreateRequest body) {
        Product product = new Product();
        product.setName(body.getProductName());
        product.setUnitPrice(body.getUnitPrice());

        Product response = productRepository.save(product);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Product> productByName(String name) {
        Product response = productRepository.getByName(name);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
        }

        return ResponseEntity.ok(response);
    }
}
