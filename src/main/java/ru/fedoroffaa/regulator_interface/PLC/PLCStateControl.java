package ru.fedoroffaa.regulator_interface.PLC;

import de.re.easymodbus.modbusclient.ModbusClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PLCStateControl {

    private static ModbusClient mc_get = new ModbusClient("169.254.168.108", 502);
    private static ModbusClient mc_set = new ModbusClient("169.254.168.108", 503);


    public PLCStateMessage getState(ArrayList<Integer> outputs, ArrayList<Integer> inputs) {
        PLCStateMessage state = new PLCStateMessage();
        try {
            Map<Integer, Boolean> inputs_state = new HashMap<Integer, Boolean>();
            Map<Integer, Boolean> outputs_state = new HashMap<Integer, Boolean>();
            mc_get.Connect();
            for (Integer i : outputs) {
                outputs_state.put(i, mc_get.ReadCoils(8191 + i, 1)[0]);
            }
            for (Integer i : inputs) {
                inputs_state.put(i, mc_get.ReadCoils(i - 1, 1)[0]);
            }
            state = new PLCStateMessage(inputs_state, outputs_state);
            mc_get.Disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    public void setState(Map<Integer, Boolean> inputs) {
        try {
            mc_set.Connect();
            for (Integer i : inputs.keySet()) {
                mc_set.WriteSingleCoil(i - 1, inputs.get(i));
            }
            mc_set.Disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
