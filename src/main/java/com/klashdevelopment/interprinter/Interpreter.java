package com.klashdevelopment.interprinter;

import com.klashdevelopment.interprinter.config.InterConfigurator;

public abstract class Interpreter {

    public abstract void interpret(String fileContent);
    public abstract InterConfigurator configs();

}
