package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.entity.ProductEntity;
import com.group.kamiloses.orderstreamapp.other.Product;
import com.group.kamiloses.orderstreamapp.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @PostConstruct
//    public void availableProducts(){
//        ProductEntity laptop = new ProductEntity(null, Product.LAPTOP);
//        ProductEntity smartphone = new ProductEntity(null,Product.SMARTPHONE);
//        ProductEntity tablet = new ProductEntity(null, Product.TABLET);
//        ProductEntity smartwatch = new ProductEntity(null, Product.SMARTWATCH);
//        ProductEntity eBookReader = new ProductEntity(null, Product.E_BOOK_READER);
//         log.info("Products have been added to our database");
//        productRepository.saveAll(Flux.just(laptop,smartphone,tablet,smartwatch,eBookReader)).subscribe();
//    }

    public Flux<ProductEntity> getAllProducts(){


    return productRepository.findAll();}


}
