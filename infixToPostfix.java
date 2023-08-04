/**
 * Write a description of interface Stack here.
 *
 * @author (Mahmoud Mohamed)
 * @version (a version number or a date)
 */

import java.util.Scanner;

class infixToPostfix {
  
      public static String infixToPostfix(String s) {
          
        ArrayStack stack = new ArrayStack(); // creates new stack using arrayStack class
        String postfix = ""; // declaring an empty string
        char ch[] = s.toCharArray();
        
        for(char c: ch) {
          if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')') {
            postfix = postfix + c;
          } 
          else if (c == '(') {
            stack.push(c);
          } 
          else if (c == ')') {
            while(!stack.isEmpty()) {
                
              Object t = stack.pop();
              if(t != "(") {
                postfix = postfix + t;
              } else {
                break;
              }
            }
          }
          else if(c == '+' ||c == '-' ||c == '*' ||c == '/') {
            if(stack.isEmpty()) {
              stack.push(c);
            } 
            else {
              while(!stack.isEmpty()) {
                Object t = stack.pop();
                if(t == "(") {
                  stack.push(t);
                  break;
                } else if(t == "+" || t == "-" || t == "*" || t == "/") {
                  if(getPriority(t) <  getPriority(c)) {
                    stack.push(t);
                    break;
                  } else {
                    postfix = postfix + t;
                  }
                }
              }
              stack.push(c);
            }
          }
        }
        
        while(!stack.isEmpty()) {
          postfix = postfix + stack.pop();
        }
        return postfix;
      }
      
      public static int getPriority(Object c) {
        if(c == "+" || c == "-") { // if encounters + or - priority is applied
          return 1; 
        } else {
          return 2;
        } 
      }
      // this is the main method will call the infixtopostfix method and takes input through console and prints the result to the console
      public static void main(String[] args) {
          Scanner input = new Scanner(System.in);
          String choice = "n";
          while(true) {
              try {
                  System.out.print("Enter expression : ");
                  String exp = input.nextLine();
                  String result = infixToPostfix(exp);
                  System.out.println("Infix To Postix : " + result);
              } catch (Exception ex) {
                  System.err.println(ex.getMessage());
              }
              System.out.print("\nPress enter to enter another expression : ");
              choice = input.nextLine();
          }
      }
}