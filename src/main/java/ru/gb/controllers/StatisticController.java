package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;
import ru.gb.services.StatisticService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
@EnableGlobalAuthentication
@AllArgsConstructor
public class StatisticController {

    private StatisticService statisticService;

    @GetMapping
    public List<String> getStatistic() {
        return statisticService.getStatistic();
    }
}