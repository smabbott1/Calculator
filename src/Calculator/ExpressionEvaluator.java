package Calculator;
import java.util.*;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        HashMap<String, BinaryOperatorType> bOpMap = new HashMap<>();
        HashMap<String, FuncType> funcMap = new HashMap<>();
        HashMap<String, UnaryOperatorType> uOpMap = new HashMap<>();
        HashMap<String, SpecialOperatorType> sMap = new HashMap<>();
        Arrays.asList(BinaryOperatorType.values()).forEach(BinaryOperatorType -> bOpMap.put(BinaryOperatorType.getSymbol(), BinaryOperatorType));
        Arrays.asList(UnaryOperatorType.values()).forEach(UnaryOperatorType -> uOpMap.put(UnaryOperatorType.getSymbol(), UnaryOperatorType));
        Arrays.asList(FuncType.values()).forEach(FuncType -> funcMap.put(FuncType.getSymbol(), FuncType));
        Arrays.asList(SpecialOperatorType.values()).forEach(specialOperatorType -> sMap.put(specialOperatorType.getSymbol(),specialOperatorType));

        System.out.println("Acceptable Symbols are: ");
        for (String symbol : bOpMap.keySet()) {
            System.out.print(symbol + " ");
        }
        System.out.print("\r\n");
        System.out.println("Type the Expression you wish to Evaluate with spaces between each symbol: ");
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        //split the infix expression by spaces and call the shunting yard impl
        shuntingYard decoder = new shuntingYard(expression.split(" "), funcMap, bOpMap, uOpMap, sMap);
        ArrayList<String> outputQueue = decoder.convert();
        for (String s : outputQueue) {
            System.out.print(s + " ");
        }
        System.out.print("\r\n");
        //CalculationTree tree = new CalculationTree(outputQueue, funcMap, bOpMap);
        //tree.generateTree();
        //tree.printTree();
        //BTreeNode root = PostfixToTree(outputQueue);
    }
}
