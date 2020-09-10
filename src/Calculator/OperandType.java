package Calculator;

public enum OperandType {
    E("e",2.71),
    PI("Pi",3.1415926),
    TRUE("True",1),
    FALSE("False",0);

    private final String literalSymbol;
    private final double numericalValue;
    OperandType(String literalSymbol, double numericalValue){
        this.literalSymbol = literalSymbol;
        this.numericalValue = numericalValue;
    }
    public String getLiteralSymbol(){ return literalSymbol; }
    public double getNumericalValue(){ return numericalValue; }
}
