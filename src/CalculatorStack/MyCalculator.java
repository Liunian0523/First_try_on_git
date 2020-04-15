package CalculatorStack;

import java.io.*;
import java.util.Scanner;
public class MyCalculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the name of test file: ");
        String name = sc.nextLine();
        File flName = new File(name + ".txt");
        Scanner in = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        PrintWriter out = null;
        try {
            fis = new FileInputStream(flName);
            in = new Scanner(fis);
            fos = new FileOutputStream("output.txt");
            out = new PrintWriter(fos);
            while(in.hasNextLine()) {
                String expression = in.nextLine();
                out.println(expression);
                String exp = expression.replace(" ", "");
                String result = calculator(exp);
                out.println("result: "+result);
            }
            out.flush();
            System.out.println("Write successfully!!! The program will terminate soon.");
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null) {
                try {
                    fis.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            in.close();
            out.close();
            sc.close();
        }
    }

    public static String calculator(String exp) throws Exception {

        //checks whether the expression is illegal or not
        if(exp == null || exp.isEmpty() || !checkFormat(exp))
            return "illegal expression";
        //adds $ symbol as the mark indicating "the end of expression"
        exp += "$";
        //initializes two stacks, one is to store values, the other is to store operators
        DynamicStack<Double> vals = new DynamicStack<Double>(10);
        DynamicStack<String> ops = new DynamicStack<String>(10);
        //manipulates the expression from the first element
        int len = exp.length();
        for(int i =0; i <len; i++) {
            String current = exp.substring(i, i+1);
            if(isNumber(current)) {
                double val = Double.parseDouble(current);
                //checks whether the next symbol is a number if it is not the last symbol except $
                if(i != len-2) {
                    //gets the next symbol
                    String next = exp.substring(++i, i+1);
                    //calculates the value until the next symbol is not a number
                    while(isNumber(next)) {
                        val = val*10 + Double.parseDouble(next);
                        next = exp.substring(++i, i+1);
                    }
                    i--;
                }
                //pushes the value on the stack
                vals.push(val);
            }else if(isOperator(current)){
                String op = current;
                //checks if <, >, !, = flowed by =
                if("<>!=".indexOf(op) >= 0 && followedEquality(exp, i)) {
                    op += "=";
                    i++;
                }
                //pushes on the stack if it's a left parenthesis
                if(op.equals("("))
                    ops.push(op);
                    //when the operator is a right parenthesis,
                else if(op.equals(")")) {
                    //does the operations till the top elements of operation stack is left parenthesis '('
                    while(!ops.top().equals("(")) {
                        double y = vals.pop();
                        double x = vals.pop();
                        //pops the top operator and does the operation
                        double result = numericOperation(x, ops.pop(), y);
                        //pushes the value on the stack
                        vals.push(result);
                    }
                    //pops the left parenthesis
                    ops.pop();
                }
                //if the operator is $, does operations till the opStack stores only one elements

                else {
                    while(vals.size() > 1 && prec(op) <= prec(ops.top())) {
                        double y = vals.pop();
                        double x = vals.pop();
                        //pops the top operator and does the operation
                        double result = numericOperation(x, ops.pop(), y);
                        //pushes the value on the stack
                        vals.push(result);
                    }
                    ops.push(op);
                }
            }else if(current.equals("$")) {
                while(ops.size()> 1) {
                    double y = vals.pop();
                    double x = vals.pop();
                    //pops the top operator and does the operation
                    double result = numericOperation(x, ops.pop(), y);
                    //pushes the value on the stack
                    vals.push(result);
                }
            }
        }

        //operates the last step of operation
        String result;
        if(vals.size() == 1) {
            result = Double.toString(vals.pop());
        }else {
            String finalOp = ops.pop();
            double y = vals.pop();
            double x = vals.pop();

            if("+-*/^".indexOf(finalOp) >= 0) {
                result = String.format("%.2f", numericOperation(x, finalOp, y));
            }else {
                result = Boolean.toString(booleanOperation(x, finalOp, y));
            }
        }
        if(result.substring(result.length()-3).equals(".00") || result.substring(result.length()-2).equals(".0"))
            result = result.substring(0,result.indexOf('.'));
        return result;
    }

    //returns a new expression that is removed all spaces added a $ symbol indicating "the end of expression"
    public static String newExpression(String expression) {
        return expression != null ? (expression.replaceAll(" ", "")) : "";
    }

    //returns true if there exists negative number
    public static boolean hasNegative(String exp) {
        for(int i = 0; i < exp.length(); i++) {
            if(exp.charAt(i)=='-') {
                //returns true if the '-' is located on the sides of the expression
                //or the front symbol is not a number
                if(i == 0 || i == exp.length()-1 || !(Character.isDigit(exp.charAt(i-1)) || ")".equals(exp.charAt(i-1)+"")))
                    return true;
            }
        }
        return false;
    }

    //return true if the symbol is a number
    public static boolean isNumber(String symbol) {
        //since it's a one-length string, we could use Character
        if(symbol.length() == 1 && Character.isDigit(symbol.charAt(0)))
            return true;
        return false;
    }

    public static boolean followedEquality(String exp, int i) {
        if(exp.substring(i+1, i+2).equals("="))
            return true;
        return false;
    }

    public static boolean isOperator(String op) {
        if("()<>=!+-*/^".indexOf(op) >=0)
            return true;
        return false;
    }

    //return the priority of the operator
    public static int prec(String op) {
        if(op==null)
            return 0;
        switch(op) {
            case "(":
                return 1;
            case "==":
            case "!=":
                return 2;
            case ">":
            case "<":
            case "<=":
            case ">=":
                return 3;
            case "+":
            case "-":
                return 4;
            case "*":
            case "/":
                return 5;
            case "^":
                return 6;
            default:
                throw new IllegalArgumentException("illegal operator: " + op);
        }
    }

    public static double numericOperation(double x, String op, double y) {
        switch(op) {
            case "+":
                return x+y;
            case "-":
                return x-y;
            case "*":
                return x*y;
            case "/":
                if(y==0)
                    throw new ArithmeticException("0 cannot be division");
                return x/y;
            case "^":
                return Math.pow(x, y);
            default:
                throw new IllegalArgumentException("illegal operator: " + op);
        }
    }

    public static boolean booleanOperation(double x, String op, double y) {
        switch(op) {
            case ">":
                return x>y;
            case "<":
                return x<y;
            case ">=":
                return x>=y;
            case "<=":
                return x<=y;
            case "==":
                return x==y;
            case "!=":
                return x!=y;
            default:
                throw new IllegalArgumentException("illegal operator: " + op);
        }
    }

    public static boolean checkFormat(String exp) {
        //cannot be null or empty
        if(exp.isEmpty() || exp==null || hasNegative(exp))
            return false;
        //creates a dynamic stack to store parentheses
        DynamicStack<Character> parenth = new DynamicStack<>(10);
        //int count1=0, countA=0;
        for(int i = 0; i<exp.length(); i++) {
            char symbol = exp.charAt(i);
            //check if there exists illegal symbols
            if(!Character.isDigit(symbol) && !isOperator(symbol+""))
                return false;
            //pushes left parenthesis on the stack
            if("(".equals(symbol+""))
                parenth.push(symbol);
            //if it's a right parenthesis
            if(")".equals(""+symbol)) {
                //if the stack is empty or its top element is not a right parenthesis, returns false
                if(parenth.isEmpty() || !"(".equals("" + parenth.pop()))
                    return false;
            }
//			if(Character.isDigit(symbol))
//				count1++;
//			if("+-*/^=><!".indexOf(symbol)>=0)
//				countA++;
//		}
//		if(count1 == 0 || countA == 0)
//			return false;

        }
        return true;
    }

}//end of class
