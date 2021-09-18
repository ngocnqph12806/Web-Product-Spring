package com.example.webproductspringboot.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApi {
    @GetMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> save() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update() {
        return null;
    }

}
