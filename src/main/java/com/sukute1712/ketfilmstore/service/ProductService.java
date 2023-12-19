package com.sukute1712.ketfilmstore.service;

import com.sukute1712.ketfilmstore.entity.Address;
import com.sukute1712.ketfilmstore.entity.Product;
import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.exception.ResourceNotFoundException;
import com.sukute1712.ketfilmstore.jwt.JwtAuthenticationFilter;
import com.sukute1712.ketfilmstore.jwt.JwtTokenProvider;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.product.ProductDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserListDto;
import com.sukute1712.ketfilmstore.repository.ProductRepository;
import com.sukute1712.ketfilmstore.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    public List<ProductDto> getAll() {
        List<Product> productDtoList = productRepository.findAll();
        return productDtoList.stream()
                .map(user -> modelMapper.map(user, ProductDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByName() {
        List<Product> productDtoList = productRepository.findAll();
        return productDtoList.stream()
                .map(user -> modelMapper.map(user, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
        return modelMapper.map(product, ProductDto.class);
    }

    public Message create(ProductDto productDto, HttpServletRequest request) {
        User user = userService.getAuthenticatedUser(request);
        Product product = modelMapper.map(productDto, Product.class);
        product.setCreatedBy(user);
        product.setCreatedDate(LocalDateTime.now());
        productRepository.save(product);
        return new Message("Product added successfully!");
    }

    public ProductDto update(UUID id, ProductDto productDto, HttpServletRequest request) {
        //TODO: Fix product properties, adding more properties or using model mapper
        User user = userService.getAuthenticatedUser(request);
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setLastModifiedBy(user);
        product.setLastModifiedDate(LocalDateTime.now());
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    public Message delete(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product"));
        productRepository.deleteById(id);
        return new Message("Product deleted successfully!");
    }

}
