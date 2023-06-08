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

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Product> createProduct(ProductCreateRequest body) {
        Product sku = productRepository.getBySku(body.getSku());

        if(sku != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Sku deve ser unico!");
        }

        if(body.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade não pode ser menor do que 0");
        }

        Product product = new Product();
        product.setName(body.getName());
        product.setUnitPrice(body.getUnitPrice());
        product.setSku(body.getSku());
        product.setQuantity(body.getQuantity());

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

    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public String massDelete(String toDelete) {
        String[] toDeleteSplit = toDelete.split("-");

        try {
            for (String id : toDeleteSplit) {
                Optional<Product> exists = productRepository.findById(Integer.valueOf(id));

                if(exists.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com o " + id + "não encontrado!" );
                }

                productRepository.deleteById(Integer.valueOf(id));
            }

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        return "Produtos deletados com sucesso!";
    }
}
