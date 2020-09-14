package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionService {

    AuctionRepository auctionRepository;

//    private List<Auction> auctions;

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

//    public List<Auction> find4MostExpensive() {
//        return auctions.stream()
//                .sorted(Comparator.comparing(Auction::getPrice).reversed())
//                .limit(4)
//                .collect(Collectors.toList());
//    }

//    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {
//        return auctions.stream()
//                .filter(auction -> auctionFilters.getTitle() == null || auction.getTitle().toUpperCase().contains(auctionFilters.getTitle().toUpperCase()))
//                .collect(Collectors.toList());
//    }
}
