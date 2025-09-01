package br.com.erudito.service;

import br.com.erudito.exception.DivisionByZeroException;
import br.com.erudito.exception.UnsupportedMathOperationException;
import br.com.erudito.model.enums.MathOperationsEnum;
import org.springframework.stereotype.Service;

@Service
public class MathService {
    private final String NUMERIC_EXCEPTION_MSG = "Please set a numeric value!";
    private final String DIVISION_BY_ZERO_EXCEPTION_MSG = "Please set a value greater than zero for the numerator!";
    private final String NEGATIVE_NUMBER_EXCEPTION_MSG = "Please set a non-negative value!";

    public Double somar(String num1, String num2) throws IllegalArgumentException {
        validarNumeros(num1,num2, MathOperationsEnum.SUM.value);
        return convertToDouble(num1) + convertToDouble(num2);
    }

    public Double subtrair(String num1, String num2) throws IllegalArgumentException {
        validarNumeros(num1,num2, MathOperationsEnum.SUBTRACTION.value);
        return convertToDouble(num1) - convertToDouble(num2);
    }

    public Double multiplicar(String num1, String num2) throws IllegalArgumentException {
        validarNumeros(num1,num2, MathOperationsEnum.MULTIPLICATION.value);
        return convertToDouble(num1) * convertToDouble(num2);
    }

    public Double dividir(String num1, String num2) throws IllegalArgumentException {
        validarNumeros(num1,num2, MathOperationsEnum.DIVISION.value);
        return convertToDouble(num1) / convertToDouble(num2);
    }

    public Double calcularMedia(String num1, String num2) throws IllegalArgumentException {
        validarNumeros(num1,num2, MathOperationsEnum.AVARAGE.value);
        return (convertToDouble(num1) + convertToDouble(num2)) / 2;
    }

    public Double calcularRaizQuadrada(String value) throws IllegalArgumentException {
        validarNumeros(value,null, MathOperationsEnum.SQUARE_ROOT.value);
        return Math.sqrt(convertToDouble(value));
    }

    private void validarNumeros(String num1, String num2, Integer operation) {
        if(!(operation == MathOperationsEnum.SQUARE_ROOT.value)) {
            if(!isNumero(num1) || !isNumero(num2)) throw new UnsupportedMathOperationException(NUMERIC_EXCEPTION_MSG);
        }

        //divisão
        if(operation == MathOperationsEnum.DIVISION.value) {
            if(isDivisaoPorZero(num2)) throw new DivisionByZeroException(DIVISION_BY_ZERO_EXCEPTION_MSG);
        }

        //raizQuadrada
        if(operation == MathOperationsEnum.SQUARE_ROOT.value) {
            if(isNegativeNumber(num1)) throw new UnsupportedMathOperationException(NEGATIVE_NUMBER_EXCEPTION_MSG);
        }
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
