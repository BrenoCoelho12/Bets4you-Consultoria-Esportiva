package com.desenvolvimento.bets4you.controller;

import com.desenvolvimento.bets4you.repository.Apostas;
import com.desenvolvimento.bets4you.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Hashtable;
import java.util.Locale;

@Controller
public class DashboardController {

    @Autowired
    private Apostas apostas;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("/usuario/dashboard");
        mv.addObject("totalApostasNoMes", apostas.totalApostasNoMes());
        mv.addObject("oddMediaNoMes", apostas.oddMediaNoMes());
        mv.addObject("rentabilidadeNoMes", dashboardService.calculoRentabilidadeNoMes().setScale(2, RoundingMode.HALF_UP));
        mv.addObject("mesAtual", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")));

        return mv;
    }

    @GetMapping("/rentabilidadePorDia")
    public @ResponseBody
    Hashtable rentabilidadePorDia(){
        Hashtable hashRentabilidadePorDia = dashboardService.calculoRentabilidadePorDia();
        return hashRentabilidadePorDia;
    }

    @GetMapping("/rentabilidadeDiaADia")
    public @ResponseBody
    Hashtable rentabilidadeDiaADia(){
        Hashtable hashRentabilidadeDiaADia = dashboardService.calculoRentabilidadeDiaADia();
        return hashRentabilidadeDiaADia;
    }

}
