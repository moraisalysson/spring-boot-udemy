package br.com.erudito.model.enums;

public enum MathOperationsEnum {

    SUM(1),
    SUBTRACTION(2),
    MULTIPLICATION(3),
    DIVISION(4),
    AVARAGE(5),
    SQUARE_ROOT(6);

    public final Integer value;

    MathOperationsEnum(Integer value) {
        this.value = value;
    }
}
