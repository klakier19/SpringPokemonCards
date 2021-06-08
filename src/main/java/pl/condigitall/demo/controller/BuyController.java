package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.condigitall.demo.request.BuyRequest;
import pl.condigitall.demo.service.AuctionService;

import javax.validation.Valid;

@Controller
public class BuyController {
    private AuctionService auctionService;

    public BuyController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/buy")
    public String ganateBuyPage(Model model) {
        BuyRequest buyRequest = new BuyRequest();
        model.addAttribute("request", buyRequest);
        model.addAttribute("auctions", auctionService.getBuyViewData());
        return "buy";
    }

    @PostMapping("/buy/{id}")
    public String buyAuction(@Valid @ModelAttribute("request") BuyRequest buyRequest, Model model, @PathVariable int id) {
        buyRequest.setAuctionID(id);
        auctionService.buyCard(buyRequest);
        return "redirect:/buy";
    }
}
// TODO Security