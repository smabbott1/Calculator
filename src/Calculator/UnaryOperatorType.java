package Calculator;

public enum UnaryOperatorType implements IEnumType{
    UNARYSUBTRACT("_",4,"None", true),
    FACTORIAL("!", 4, "None", false);
    private final String symbol;
    private final Integer precedence;
    private final String associativity;
    private final Boolean prefixOp;
    UnaryOperatorType(String symbol, Integer precedence, String associativity, Boolean prefixOp){
        this.symbol = symbol;
        this.precedence = precedence;
        this.associativity = associativity;
        this.prefixOp = prefixOp;
    }
    public String getSymbol() { return symbol; }
    public int getPrecedence() { return precedence; }
    public String getAssociativity() { return associativity; }
    public Boolean isPrefixOp() { return prefixOp; }
}
