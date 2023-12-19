package com.sukute1712.ketfilmstore.controller;

import com.sukute1712.ketfilmstore.entity.Product;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.product.ProductDto;
import com.sukute1712.ketfilmstore.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
        name = "Product",
        description = "Product controller"
)
public class ProductController {

    ProductService productService;

    @GetMapping("/public")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/public/search")
    public ResponseEntity<List<ProductDto>> getByName(@RequestParam(value = "query") String name) {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> create(@RequestBody ProductDto productDto, HttpServletRequest request) {
        return new ResponseEntity<>(productService.create(productDto, request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> update(@PathVariable UUID id, @RequestBody ProductDto productDto, HttpServletRequest request) {
        return ResponseEntity.ok(productService.update(id, productDto, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.delete(id));
    }

}
