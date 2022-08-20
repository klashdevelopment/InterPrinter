package com.klashdevelopment.interprinter.presets;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "set", buildMethodName = "finish", builderMethodName = "newBuilder")
public class SimpleInterpreterCustomActions {

    @Getter @Setter
    public SimpleInterpreterAction runAtStart = ()->{};
    @Getter @Setter
    public SimpleInterpreterAction runBeforeTokenParsing = ()->{};
    @Getter
    @Setter
    public SimpleInterpreterAction runAfter = ()->{};

    @FunctionalInterface
    public static interface SimpleInterpreterAction {
        public void run();
    }

    protected void runBeforeTokenParsing() {
        if(runBeforeTokenParsing != null) {
            runBeforeTokenParsing.run();
        }
    }
    protected void runAfter() {
        if(runAfter != null) {
            runAfter.run();
        }
    }
    protected void runAtStart() {
        if(runAtStart != null) {
            runAtStart.run();
        }
    }

}
