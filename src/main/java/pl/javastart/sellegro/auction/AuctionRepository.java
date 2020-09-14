package pl.javastart.sellegro.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByOrderByTitle();

    List<Auction> findAllByOrderByPrice();

    List<Auction> findAllByOrderByColor();

    List<Auction> findAllByOrderByEndDate();

    List<Auction> findTop4ByOrderByPriceDesc();

    @Query("SELECT a FROM Auction a WHERE (:title IS NULL OR a.title = :title) AND" +
            " :carMaker IS NULL OR a.carMake = :carMaker AND" +
            " :carModel IS NULL OR a.carModel = :carModel AND" +
            " :color IS NULL OR a.color = :color")
    List<Auction> findByFilters(@Param("title") String title,
                                @Param("carMaker") String carMaker,
                                @Param("carModel") String carModel,
                                @Param("color") String color);
}
