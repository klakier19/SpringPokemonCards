package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.condigitall.demo.request.SellRequest;
import pl.condigitall.demo.service.AuctionService;
import pl.condigitall.demo.service.AuctionServiceException;

import javax.validation.Valid;

@Controller
public class SellController {
    private AuctionService auctionService;

    public SellController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/sell")
    public String getSellPage(Model model) {
        SellRequest sellRequest = new SellRequest();
        model.addAttribute("request", sellRequest);
        model.addAttribute("cards", auctionService.getSellViewData());
        return "sell";
    }

    @PostMapping("/sell/{id}")
    public String createAuction(@Valid @ModelAttribute("request") SellRequest sellRequest,BindingResult bindingResult, @PathVariable String id,  Model model) {
        sellRequest.setCardID(id);
        if(bindingResult.hasErrors()) {
            System.err.println("Wystąpiły błedy w bindowaniu:" + bindingResult.getErrorCount());
            model.addAttribute("cards", auctionService.getSellViewData());
            return "sell";
        }
       try {
        auctionService.sellCard(sellRequest);
            } catch (AuctionServiceException e) {
           bindingResult.addError(new FieldError("request", "cardAmount", e.getMessage()));
           model.addAttribute("cards", auctionService.getSellViewData());
           return "sell";
       }
        return "redirect:/sell";
    }
}
