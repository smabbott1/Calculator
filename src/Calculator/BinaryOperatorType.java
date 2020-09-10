package Calculator;
public enum BinaryOperatorType implements IEnumType{
    ADD("+",1,"Any"),
    SUBTRACT("-",1,"Left"),
    MULTIPLY("*",2,"Any"),
    DIVIDE("/",2,"Left"),
    EXPONENTIATE("^",3,"Right");
    private final String symbol;
    private final Integer precedence;
    private final String associativity;
    BinaryOperatorType(String symbol, Integer precedence, String associativity){
        this.symbol = symbol;
        this.precedence = precedence;
        this.associativity = associativity;
    }
    public String getSymbol(){
        return symbol;
    }
    public int getPrecedence() {
        return precedence;
    }
    public String getAssociativity(){ return this.associativity; }
}
