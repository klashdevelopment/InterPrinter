package com.klashdevelopment.interprinter.presets;

import com.klashdevelopment.interprinter.Interpreter;
import com.klashdevelopment.interprinter.config.ToggleableValue;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class SimpleInterpreter extends Interpreter {
    boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    public String token = "";
    public int countErrors=0;

    public int skipSpaces = 0;
    public int lineNumber = 1;
    public List<Integer> CommentedLines = new ArrayList<>();
    public int maxLines = 1;

    double parseAlgebra(String input) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
//        Map<String, Object> vars = new HashMap<String, Object>();
//        vars.put("x", 2);
//        vars.put("y", 1);
//        vars.put("z", 3);
        try {
            return (double)(engine.eval("x + y + z", new SimpleBindings(/*vars*/)));
        } catch (ScriptException e) {
           System.out.println("Invalid number expression: " + input);
        } catch (ClassCastException e) {
            System.out.println(("Invalid number expression: " + input));
        }
        return -Integer.MAX_VALUE;
    }

    String nextChar(int i){
        try {
            return token.charAt(i) + "";
        }
        catch (Exception e) {
            return "null";
        }
    }

    public void interpret(String fileContent) {
//        System.out.println(parseAlgebra("1+2"));
        List<String> args = new ArrayList<>();
        args.add(fileContent);
        try{
            for(String arg : args) {
                for(char c : arg.toCharArray()) {
                    if(c == '\n') {
                        maxLines++;
                    }
                }
            }
            for (String arg : args) {
                int i = 0;
                for(char c : arg.toCharArray()) {
                    if(c == '\r' || c == '\t') {
                        token = "";
                        continue;
                    }
                    if(c == '\n' || c == ';') {
                        lineNumber++;
                        token = "";
//                    continue;
                        if(parseToken("null") == false){
                            System.out.println("Unexpected token: \"" + token.replaceFirst("\n", "") + "\" at line: " + lineNumber);
                        }
                        continue;
                    }
                    if(c == ' ') {
                        if(skipSpaces > 0) {
                            skipSpaces--;
                            continue;
                        }
                    }
                    if(/* Check if its a comment */ c == commentTok() && token.equals("")) {
                        token = "";
                        CommentedLines.add(lineNumber);
//                    System.out.println(ConsoleColor.YELLOW + "Comment found on line " + lineNumber + ConsoleColor.RESET);
                        continue;
                    }
                    if(CommentedLines.contains(lineNumber)) {
                        token = "";
                        continue;
                    }

                    token += (""+c+"");
//                try{
//                    System.out.println("Current char: " + c + "\nToken: " + token + "\ni: " + i + "\nNext char: " + arg.charAt(i+1)+"\n");
//                }catch (Exception e) {
//                    System.out.println("Current char: " + c + "\nToken: " + token + "\ni: " + i + "\nNext char: " + "null\n");
//                }

//                if(isNumber(c) && token.equals("")) {
//                    nextChar(i);
//                }

                    boolean result;
                    parseToken(nextChar(i));
                    i++;
                }
            }
        }catch(Exception e) {
            
        }
    }
    public abstract boolean parseToken(String nextChar);
    public abstract char commentTok();

    public SimpleInterpreterCustomActions runCustomActions() {
        return SimpleInterpreterCustomActions.newBuilder().finish();
    }

}
