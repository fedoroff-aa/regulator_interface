package ru.fedoroffaa.regulator_interface.PLC;

import java.util.HashMap;
import java.util.Map;

public class PLCStateMessage {

    private Map<Integer, Boolean> inputs = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> outputs = new HashMap<Integer, Boolean>();

    public PLCStateMessage(Map<Integer, Boolean> inputs, Map<Integer, Boolean> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public PLCStateMessage(Map<Integer, Boolean> inputs) {
        this.inputs = inputs;
    }

    public PLCStateMessage() {}

    public Map<Integer, Boolean> getInputs() {
        return inputs;
    }

    public Map<Integer, Boolean> getOutputs() {
        return outputs;
    }

    public void setInputs(Map<Integer, Boolean> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(Map<Integer, Boolean> outputs) {
        this.outputs = outputs;
    }
}