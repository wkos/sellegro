package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuctionController {

    private AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters) {
        List<Auction> auctions;
//        auctions = auctionService.auctionRepository.findAll();
        String carMaker = "";
        String carModel = "";
        String color = "%";
        System.out.println(auctionFilters);
        System.out.println(sort);
        if (sort != null) {
            switch (sort) {
                case "title":
                    auctions = auctionService.auctionRepository.findAllByOrderByTitle();
                    break;
                case "price":
                    auctions = auctionService.auctionRepository.findAllByOrderByPrice();
                    break;
                case "color":
                    auctions = auctionService.auctionRepository.findAllByOrderByColor();
                    break;
                case "endDate":
                    auctions = auctionService.auctionRepository.findAllByOrderByEndDate();
                    break;
                default:
                    auctions = auctionService.auctionRepository.findAllByOrderByTitle();
            }

        } else {
            auctions = auctionService.auctionRepository.findAllByCarMakeAndCarModelAndColor(auctionFilters.getTitle(),
                    auctionFilters.getCarMaker(), auctionFilters.getCarModel(), auctionFilters.getColor());
//            if (auctionFilters.getTitle() != null) {
////                auctions = auctionRepository.findAllByTitle(auctionFilters.getTitle());
//            }
//            if (auctionFilters.getCarMaker() == null) {
//                carMaker = "*";
////                auctions = auctionRepository.findAllByCarMake(auctionFilters.getCarMaker());
//            }
//            if (auctionFilters.getCarModel() == null) {
//                carModel = "*";
////                auctions = auctionRepository.findAllByCarModel(auctionFilters.getCarModel());
//            }
//            if (auctionFilters.getColor() == null) {
//                color = "*";
////                System.out.println("jestem w kolorze");
////                System.out.println(auctionFilters.getColor());
////                auctions = auctionRepository.findAllByColor(auctionFilters.getColor());
//            }
        }

//        for (Auction auction : auctions) {
//            System.out.println(auction);
//        }
        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);
        return "auctions";
    }
}
