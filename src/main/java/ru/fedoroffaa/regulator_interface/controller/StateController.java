package ru.fedoroffaa.regulator_interface.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.fedoroffaa.regulator_interface.PLC.PLCStateControl;
import ru.fedoroffaa.regulator_interface.PLC.PLCStateMessage;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class StateController {

    private Logger log = LoggerFactory.getLogger(StateController.class);
    private PLCStateControl plcStateControl = new PLCStateControl();
    private ArrayList<Integer> PLCOutputs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    private ArrayList<Integer> PLCInputs = new ArrayList<Integer>(Arrays.asList(1, 2, 6));
    private PLCStateMessage lastState = plcStateControl.getState(PLCOutputs, PLCInputs);
    public Boolean firstMessage = true;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sysstate")
    public void updateState(PLCStateMessage stateMessage){
        plcStateControl.setState(stateMessage.getInputs());
    }


    @Scheduled(fixedDelay = 500)
    private void sendState() {
        PLCStateMessage newState = plcStateControl.getState(PLCOutputs, PLCInputs);
        lastState = newState;
        firstMessage = false;
        simpMessagingTemplate.convertAndSend("/topic/sysstate", lastState);
    }
}