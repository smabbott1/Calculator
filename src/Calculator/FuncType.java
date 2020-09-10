package Calculator;

public enum FuncType implements IEnumType{
    SIN("sin"),
    COS("cos"),
    TAN("tan"),
    CSC("csc"),
    SEC("sec"),
    COT("cot"),
    ARCSIN("arcsin"),
    ARCCOS("arccos"),
    ARCTAN("arctan"),
    ARCCSC("arccsc"),
    ARCSEC("arcsec"),
    ARCCOT("arccot"),
    INTEGRATE("Integrate"),
    LOG("Log"),
    DIFFERENTIATE("Differentiate");
    private final String Symbol;
    private final Integer precedence;
    private final String Associativity;
    FuncType(String literalSymbol){
        this.Symbol = literalSymbol;
        this.precedence = 4;
        this.Associativity = "Any";
    }
    public String getSymbol(){
        return Symbol;
    }
    public int getPrecedence() {
        return precedence;
    }
    public String getAssociativity(){ return Associativity; }
}

