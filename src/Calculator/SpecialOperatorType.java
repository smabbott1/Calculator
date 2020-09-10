package Calculator;
public enum SpecialOperatorType implements IEnumType{
    COMMA(",",5,"None"),
    LEFTPARENTHESIS("(",5,"None"),
    RIGHTPARENTHESIS(")",5,"None");
    private final String symbol;
    private final Integer precedence;
    private final String associativity;
    SpecialOperatorType(String symbol, Integer precedence, String associativity){
        this.symbol = symbol;
        this.precedence = precedence;
        this.associativity = associativity;
    }
    public String getSymbol(){
        return symbol;
    }
    public int getPrecedence() { return precedence; }
    public String getAssociativity(){ return this.associativity; }
}
