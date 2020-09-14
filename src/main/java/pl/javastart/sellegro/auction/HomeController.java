package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.sellegro.auction.AuctionService;

@Controller
public class HomeController {

    private AuctionService auctionService;

    @Autowired
    public HomeController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String home(Model model) {
        auctionService.saveTitles();
        model.addAttribute("cars", auctionService.auctionRepository.findTop4ByOrderByPriceDesc());
        return "home";
    }
}
