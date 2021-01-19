package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.condigitall.demo.service.BoosterService;

@Controller
public class BoosterController {
    private BoosterService boosterService;

    public BoosterController(BoosterService boosterService) {
        this.boosterService = boosterService;
    }

    @GetMapping("/booster")
    public String showBoosterPage(Model model) {
        model.addAttribute("data", boosterService.getViewData());
        return "booster";
    }

    @PostMapping("/booster")
    public String openBooster(RedirectAttributes red) {
        System.out.println("booster open");

       try {
           red.addFlashAttribute("booster", boosterService.openBooster());
       } catch ( Exception e){
           red.addFlashAttribute("error", e.getMessage());
       }
       return "redirect:/booster";
    }
}
