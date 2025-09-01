package br.com.erudito.controllers;

import br.com.erudito.exception.DivisionByZeroException;
import br.com.erudito.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    private final String NUMERIC_EXCEPTION_MSG = "Please set a numeric value!";
    private final String DIVISION_BY_ZERO_EXCEPTION_MSG = "Please set a value greater than zero for the numerator!";
    private final String NEGATIVE_NUMBER_EXCEPTION_MSG = "Please set a non-negative value!";

    //http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{num1}/{num2}")
    public Double sum(@PathVariable("num1") String num1,
                      @PathVariable("num2") String num2) throws IllegalArgumentException {
        if(!isNumero(num1) || !isNumero(num2)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        return convertToDouble(num1) + convertToDouble(num2);
    }

    //http://localhost:8080/math/subtract/3/5
    @RequestMapping("/subtract/{num1}/{num2}")
    public Double subtract(@PathVariable("num1") String num1,
                      @PathVariable("num2") String num2) throws IllegalArgumentException {
        if(!isNumero(num1) || !isNumero(num2)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        return convertToDouble(num1) - convertToDouble(num2);
    }

    //http://localhost:8080/math/divide/3/5
    @RequestMapping("/divide/{num1}/{num2}")
    public Double divide(@PathVariable("num1") String num1,
                           @PathVariable("num2") String num2) throws IllegalArgumentException {
        if(!isNumero(num1) || !isNumero(num2)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        if(isDivisaoPorZero(num2)) throw new DivisionByZeroException(DIVISION_BY_ZERO_EXCEPTION_MSG);
        return convertToDouble(num1) / convertToDouble(num2);
    }

    //http://localhost:8080/math/avarage/3/5
    @RequestMapping("/avarage/{num1}/{num2}")
    public Double avarage(@PathVariable("num1") String num1,
                         @PathVariable("num2") String num2) throws IllegalArgumentException {
        if(!isNumero(num1) || !isNumero(num2)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        if(isDivisaoPorZero(num2)) throw new DivisionByZeroException(DIVISION_BY_ZERO_EXCEPTION_MSG);
        return (convertToDouble(num1) + convertToDouble(num2)) / 2;
    }

    //http://localhost:8080/math/square/3/5
    @RequestMapping("/square/{value}")
    public Double square(@PathVariable("value") String value) throws IllegalArgumentException {
        if(!isNumero(value)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        if(isNegativeNumber(value)) throw new UnsupportedMathOperationException(NEGATIVE_NUMBER_EXCEPTION_MSG);
        return Math.sqrt(convertToDouble(value));
    }

    private Double convertToDouble(String strValue) {
        if(strValue == null || strValue.isEmpty()) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        String number = strValue.replace(",", "."); //5,00 -> 5.00
        return Double.parseDouble(number);
    }

    private boolean isNumero(String strValue) {
        if(strValue == null || strValue.isEmpty()) return false;
        String number = strValue.replace(",", "."); //R$ 5,00 -> 5.00
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }

    private boolean isDivisaoPorZero(String strValue) {
        String number = strValue.replace(",", "."); //R$ 5,00 -> 5.00
        return convertToDouble(number) == 0;
    }

    private boolean isNegativeNumber(String strValue) {
        String number = strValue.replace(",", "."); //R$ 5,00 -> 5.00
        return convertToDouble(number) < 0;
    }
}
