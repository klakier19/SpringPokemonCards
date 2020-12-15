package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.condigitall.demo.service.BoosterService;

@Controller
public class BoosterController {
    private BoosterService boosterService;

    public BoosterController(BoosterService boosterService) {
        this.boosterService = boosterService;
    }

    @GetMapping("/booster")
    public String showBoosterPage() {
        return "booster";
    }

    @PostMapping("/booster")
    public void openBooster(Model model) {
        System.out.println("booster open");
        model.addAttribute("booster",boosterService.cardGenerator());
    }


}
