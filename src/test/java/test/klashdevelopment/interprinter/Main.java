package test.klashdevelopment.interprinter;

import com.klashdevelopment.interprinter.InterPrinter;
import com.klashdevelopment.interprinter.Interpreter;
import com.klashdevelopment.interprinter.presets.SimpleInterpreter;
import com.klashdevelopment.interprinter.presets.SimpleInterpreterCustomActions;

public class Main {
    public static void main(String[] args) {
        TestlangInterpreter interpreter = new TestlangInterpreter();
        interpreter.interpret("""
                PRINT "Hello World!"
                PRINT "HelloWorld" # a
                PRINT_LINE # a
                PRINT_NEWLINE
                PRINT "this wont run - it doesnt have an end
                PRINT "this will run! it has an end" # im a comment
                """);

        InterPrinter.openEditor(interpreter);
    }
}
