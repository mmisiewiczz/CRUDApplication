package com.example.CRUDApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.model.Product;

import org.springframework.http.HttpStatus; // Dodaj ten import

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/example")
    public Product getProduct() {
        System.out.println("Wywołano metodę getProduct()");
        // Tutaj można symulować pobranie danych z bazy danych lub innego źródła
        // W przykładzie zwracamy statycznie utworzony obiekt
        Product product = new Product(1L, "Example Product", 19.99);
        System.out.println("Zwracany produkt: " + product);
        return product;
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404 - Page not found";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500 - Internal server error";
            }
        }
        return "Unknown error";
    }
}
