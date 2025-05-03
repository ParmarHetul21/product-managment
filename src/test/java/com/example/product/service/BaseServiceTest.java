package com.example.product.service;

import com.example.product.ProductApplicationTests;
import com.example.product.repository.ProductRepository;
import com.example.product.utility.Translator;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.example.product.constant.TestConstant.ADMIN_USER_USERNAME;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithUserDetails(value = ADMIN_USER_USERNAME)
public class BaseServiceTest extends ProductApplicationTests {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected Translator translator;

    @Autowired
    protected ProductRepository productRepository;
}
