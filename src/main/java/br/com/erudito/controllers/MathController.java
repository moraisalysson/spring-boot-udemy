package br.com.erudito.controllers;

import br.com.erudito.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @Autowired
    private MathService service;

    //http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{num1}/{num2}")
    public Double sum(@PathVariable("num1") String num1,
                      @PathVariable("num2") String num2) throws IllegalArgumentException {

        return service.somar(num1, num2);
    }

    //http://localhost:8080/math/subtract/3/5
    @RequestMapping("/subtract/{num1}/{num2}")
    public Double subtract(@PathVariable("num1") String num1,
                      @PathVariable("num2") String num2) throws IllegalArgumentException {
        return service.subtrair(num1, num2);
    }

    //http://localhost:8080/math/multiply/3/5
    @RequestMapping("/multiply/{num1}/{num2}")
    public Double multiply(@PathVariable("num1") String num1,
                         @PathVariable("num2") String num2) throws IllegalArgumentException {
        return service.multiplicar(num1, num2);
    }

    //http://localhost:8080/math/divide/3/5
    @RequestMapping("/divide/{num1}/{num2}")
    public Double divide(@PathVariable("num1") String num1,
                           @PathVariable("num2") String num2) throws IllegalArgumentException {
        return service.dividir(num1, num2);
    }

    //http://localhost:8080/math/avarage/3/5
    @RequestMapping("/avarage/{num1}/{num2}")
    public Double avarage(@PathVariable("num1") String num1,
                         @PathVariable("num2") String num2) throws IllegalArgumentException {
        return service.calcularMedia(num1, num2);
    }

    //http://localhost:8080/math/square/3/5
    @RequestMapping("/square/{value}")
    public Double square(@PathVariable("value") String value) throws IllegalArgumentException {
        return service.calcularRaizQuadrada(value);
    }
}
