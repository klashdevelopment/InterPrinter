package test.klashdevelopment.interprinter;

import com.klashdevelopment.interprinter.Interpreter;
import com.klashdevelopment.interprinter.config.InterConfigurator;
import com.klashdevelopment.interprinter.presets.SimpleInterpreter;
import com.klashdevelopment.interprinter.presets.SimpleInterpreterCustomActions;

import java.util.*;

public class TestlangInterpreter extends SimpleInterpreter {

    @Override
    public boolean parseToken(String nextChar) {
        List<String> tokens = new ArrayList<String>(Arrays.asList(

                "PRINT",
                "<EOF>",
                "PRINT_LINE",
                "PRINT_NEWLINE"

        ));

        if(token.equals("PRINT") && !nextChar.equals("_")) {
            skipSpaces=1;
            return true;
        }

        if(token.equals("PRINT_NEWLINE")) {
            System.out.println();
            return true;
        }
        if(token.equals("PRINT_LINE")) {
            System.out.println();
            System.out.println(" >> -------------------------------------------------- << ");
            System.out.println();
            token = "";
            return true;
        }

        if(stringEnds("PRINT")) {
            String print = token.substring(6, token.length()-1);
            if(print.equals("")) {
                System.out.println(("No print text found."));
                return true;
            }
            System.out.println("(Debug): " +print);
            return true;
        }

        if(CommentedLines.contains(lineNumber)) {
            token = "";
            return true;
        }

        if(token.equals("")
                || token.equals("\n")) {
            return true;
        }

//        iff(!tokens.contains(token) && (nextChar == "\n" || nextChar == "<EOF>")) {
//            doError("Unexpected token: \"" + token + "\" at line: " + lineNumber);
//            return false;
//        }

        return false;
    }

    /**
     * @return the custom actions
     */
    @Override
    public SimpleInterpreterCustomActions runCustomActions() {
        return super.runCustomActions();
    }


    boolean stringEnds(String starter){
        return (token.startsWith(starter+"\'") && token.endsWith("\'") && token.length() > 7)
                || (token.startsWith(starter+"\"") && token.endsWith("\"") && token.length() > 7);
    }

    String stringGetEnds(String starter){
        return token.substring(starter.length()+1, token.length()-1);

    }

    /**
     * @return config
     */
    @Override
    public InterConfigurator configs() {
        InterConfigurator config = new InterConfigurator();
        config.getRequiredFileExtentions().toggleTo(true);
        config.getRequiredFileExtentions().setValue(new String[]{"test"});
        return config;
    }

    @Override
    public char commentTok() {
        return '#';
    }
}
