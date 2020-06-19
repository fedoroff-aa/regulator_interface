package ru.fedoroffaa.regulator_interface.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;


@RestController

public class IOLabelsController {
    private final Map<Integer, String> inputsLabels = Map.ofEntries(
            entry(1, "Пуск"),
            entry(2, "Стоп"),
            entry(6, "Сброс")
    );
    private final Map<Integer, String> outputsLabels = Map.ofEntries(
            entry(1, "Регулятор"),
            entry(2, "Мотор"),
            entry(3, "Работа"),
            entry(4, "Стоп"),
            entry(5, "Датчик перегрева"),
            entry(6, "Датчик давления"),
            entry(7, "Датчик тока")
    );

    @RequestMapping(value = "/IOLabels", method = RequestMethod.POST)
    public Map<String, Map<Integer, String>> IOLabels() {
        return Map.ofEntries(
                entry("inputs", inputsLabels),
                entry("outputs", outputsLabels)
        );
    }


}
