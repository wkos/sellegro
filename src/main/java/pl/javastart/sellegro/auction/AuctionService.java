package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

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

    public List<Auction> findAuctionsBySortOrByFilter(String sort, AuctionFilters auctionFilters) {
        if (sort != null) {
            return findAuctionsSortBy(sort);
        } else {
            return findAuctionsByFilters(auctionFilters);
        }
    }

    public List<Auction> findTop4ByOrderByPriceDesc() {
        return auctionRepository.findTop4ByOrderByPriceDesc();
    }

    public List<Auction> findAuctionsSortBy(String sort) {
        List<Auction> auctions;
        auctions = auctionRepository.findAll();
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
        return auctions;
    }

    public List<Auction> findAuctionsByFilters(AuctionFilters auctionFilters) {
        List<Auction> auctions;
        auctions = auctionRepository.findByFilters(
                auctionFilters.getTitleLowerCase(),
                auctionFilters.getCarMakerLowerCase(),
                auctionFilters.getCarModelLowerCase(),
                auctionFilters.getColorLowerCase());
        return auctions;
    }
}
