package com.ghostappi.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.ghostappi.backend.service.ProductService;
import com.ghostappi.backend.model.Product;

@Controller
public class ProductController {

        @Autowired
        private ProductService productServvice;

        // @Autowired
        // private ModelMapper modelMapper;

        @QueryMapping
        public List<Product> getAll() {
                return productServvice.getAll();
        }

        @QueryMapping
        public Product getById(@Argument int idProduct) {
                return productServvice.getById(idProduct);
        }

}