package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionService {

    AuctionRepository auctionRepository;

    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public void saveTitles() {
        Random random = new Random();
        for (Auction auction : auctionRepository.findAll()) {
            String randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            auction.setTitle(randomAdjective + " " + auction.getCarMake() + " " + auction.getCarModel());
            auctionRepository.save(auction);
        }
    }

    public List<Auction> getAuctionsByFilters(String sort, AuctionFilters auctionFilters) {
        List<Auction> auctions;
        if (sort != null) {
            switch (sort) {
                case "title":
                    auctions = auctionRepository.findAllByOrderByTitle();
                    break;
                case "price":
                    auctions = auctionRepository.findAllByOrderByPrice();
                    break;
                case "color":
                    auctions = auctionRepository.findAllByOrderByColor();
                    break;
                case "endDate":
                    auctions = auctionRepository.findAllByOrderByEndDate();
                    break;
                default:
                    auctions = auctionRepository.findAllByOrderByTitle();
            }
        } else {
            String getTitle = auctionFilters.getTitle() == null ? "" : auctionFilters.getTitle().toLowerCase();
            String getCarMaker = auctionFilters.getCarMaker() == null ? "" : auctionFilters.getCarMaker().toLowerCase();
            String getCarModel = auctionFilters.getCarModel() == null ? "" : auctionFilters.getCarModel().toLowerCase();
            String getColor = auctionFilters.getColor() == null ? "" : auctionFilters.getColor().toLowerCase();
            auctions = auctionRepository.findByFilters(
                    getTitle,
                    getCarMaker,
                    getCarModel,
                    getColor);
        }
        return auctions;
    }
}
