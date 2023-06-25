package br.com.muniz.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test Controller - name {}", name);
        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/horaAgora")
    public ResponseEntity<?> horaAgora() {
        Date dateNow = new Date();
        LOG.info("Test Controller - Hora Agora {}", dateNow);
        return ResponseEntity.ok("Hora agora: " + dateNow);
    }
}
