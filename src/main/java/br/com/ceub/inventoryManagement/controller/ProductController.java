package br.com.ceub.inventoryManagement.controller;

import br.com.ceub.inventoryManagement.domain.dto.ProductCreateRequest;
import br.com.ceub.inventoryManagement.domain.entities.Product;
import br.com.ceub.inventoryManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductCreateRequest body) {
        return productService.createProduct(body);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return productService.productByName(name);
    }

//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts() {
//    }
}
