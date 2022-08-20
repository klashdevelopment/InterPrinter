package com.klashdevelopment.interprinter;

import com.klashdevelopment.klide.KliDEFrame;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InterPrinter {

    public static void runCode(Interpreter interpreter, File file) throws FileNotFoundException {
        try {
            interpreter.interpret(readContentOffFile(file));
        } catch (Exception e) {
            throw e;
        }
    }

    public static void openEditor(Interpreter interpreter) {
        KliDEFrame frame = new KliDEFrame();
        frame.RunPanel(interpreter);
    }

    public static String readContentOffFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (s.hasNextLine()) {
            sb.append(s.nextLine());
        }
        return sb.toString();
    }

}
