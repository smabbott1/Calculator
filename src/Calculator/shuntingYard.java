package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class shuntingYard {
    private final String[] infix;
    private final HashMap<String,FuncType> funcMap;
    private final HashMap<String,BinaryOperatorType> bOpMap;
    private final HashMap<String,UnaryOperatorType> uOpMap;
    private final HashMap<String,SpecialOperatorType> sMap;
    public shuntingYard(String[] infix,
                        HashMap<String, FuncType> funcMap,
                        HashMap<String, BinaryOperatorType> bOpMap,
                        HashMap<String, UnaryOperatorType> uOpMap, HashMap<String, SpecialOperatorType> sMap)
    {
        this.infix = infix;
        this.funcMap = funcMap;
        this.bOpMap = bOpMap;
        this.uOpMap = uOpMap;
        this.sMap = sMap;
    }
    //method converts infix expression to postfix
    public ArrayList<String> convert(){
        Stack<IEnumType> operatorStack =  new Stack<>();
        ArrayList<String> outputQueue = new ArrayList<>();
        /**
         * Test Suite for Infix to Postfix Shunting Yard impl:
         * 3 + 2
         * should be 3 2 +
         * basic function
         *
         * 3 + 4 * 5 / 6
         * should be 3 4 5 * 6 / +
         * precedence operations
         *
         * ( 300 + 23 ) * ( 43 - 21 ) / ( 84 + 7 )
         * should be 300 23 + 43 21 - * 84 7 + /
         * non nested parenthesis
         *
         * ( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )
         * should be: 4 8 + 6 5 - * 3 2 – 2 2 + * /
         * nested parenthesis
         *
         * 3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3
         * should be: 3 4 2 * 1 5 - 2 3 ^ ^ / +
         * exponential with right associativity
         **/
        //for each character in the expression
        for (String term:infix) {
            //if token is operand, append to postfix output
            if (!funcMap.containsKey(term) && !bOpMap.containsKey(term) && !uOpMap.containsKey(term) && !sMap.containsKey(term)){
                outputQueue.add(term);
            }
            //if token is unary postfix operator append to postfix output
            else if (uOpMap.containsKey(term) && !uOpMap.get(term).isPrefixOp()){
                outputQueue.add(term);
            }
            //if token is unary prefix operator push it onto stack
            else if(uOpMap.containsKey(term) && uOpMap.get(term).isPrefixOp()){
                operatorStack.push(uOpMap.get(term));
            }
            //if token is a function token push it onto stack
            else if(funcMap.containsKey(term)){
                operatorStack.push(funcMap.get(term));
            }
            //if token is a function argument separator: pop top of stack append to output until top element is "("
            else if(term.equals(",")){
                while(!operatorStack.empty()
                        && !operatorStack.peek().getSymbol().equals("(")){
                    outputQueue.add(operatorStack.pop().getSymbol());
                }
            }
            //if token is binary operator then:
            else if(bOpMap.containsKey(term)){
                //if token A is left-associative, while there is op B of greater or equal precedence than A at top
                //of stack, pop B off stack and append it to output.
                if(bOpMap.get(term).getAssociativity().equals("Left")){
                    while(!operatorStack.empty()
                            && ( bOpMap.containsKey(operatorStack.peek().getSymbol())
                                 || uOpMap.containsKey(operatorStack.peek().getSymbol()))
                            && operatorStack.peek().getPrecedence() >= bOpMap.get(term).getPrecedence()){
                        outputQueue.add(operatorStack.pop().getSymbol());
                    }
                }
                //If token A is right-associative, while there is op B of greater precedence than A at top of
                //stack, pop B off stack and append it to output.
                else if(bOpMap.get(term).getAssociativity().equals("Right")){
                    while(!operatorStack.empty()
                            && operatorStack.peek().getPrecedence() > bOpMap.get(term).getPrecedence()){
                        outputQueue.add(operatorStack.pop().getSymbol());
                    }
                }
                //Push A onto the stack.
                operatorStack.push(bOpMap.get(term));
            }
            //if the token is an "(" push it onto the stack
            else if(term.equals("(")){
                operatorStack.push(sMap.get(term));
            }
            //if the token is a ")" then:
            else if(term.equals(")")){
                //Pop ops off stack and append them to output, until op at top of stack is "("
                while(!operatorStack.empty()
                        && !operatorStack.peek().getSymbol().equals("(")){
                    outputQueue.add(operatorStack.pop().getSymbol());
                }
                //then pop off the "("
                operatorStack.pop();
                //If token at top of stack is function token, pop it and append it to output.
                if(!operatorStack.empty() && funcMap.containsKey(operatorStack.peek().getSymbol())){
                    outputQueue.add(operatorStack.pop().getSymbol());
                }
            }
        }
        //after all tokens are read pop and append any remaining tokens
        while (!operatorStack.empty()){
            outputQueue.add(operatorStack.pop().getSymbol());
        }
        return outputQueue;
    }
}
