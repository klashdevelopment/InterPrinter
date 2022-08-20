package com.klashdevelopment.interprinter.config;

import com.klashdevelopment.interprinter.Interpreter;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InterConfigurator {
    @Getter
    ToggleableValue requiredFileExtentions = new ToggleableValue(false, new String[]{});
}
