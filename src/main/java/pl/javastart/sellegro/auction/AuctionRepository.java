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

    @Query("SELECT a FROM Auction a WHERE a.title = CASE WHEN :title IS NULL THEN '%' ELSE :title END AND" +
            " a.carMake = CASE WHEN :carMaker IS NULL THEN '%' ELSE :carMaker END AND" +
            " a.carModel = CASE WHEN :carModel IS NULL THEN '%' ELSE :carModel END AND" +
            " a.color = CASE WHEN :color IS NULL THEN '%' ELSE :color END")
    List<Auction> findAllByCarMakeAndCarModelAndColor(@Param("title") String title,
                                                              @Param("carMaker") String carMaker,
                                                              @Param("carModel") String carModel,
                                                              @Param("color") String color);
}
