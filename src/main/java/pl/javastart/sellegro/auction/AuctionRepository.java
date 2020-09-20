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

    @Query("SELECT a FROM Auction a WHERE (LOWER(a.title) LIKE CONCAT('%', :title, '%')) AND " +
            "(LOWER(a.carMake) LIKE CONCAT('%', :carMaker, '%')) AND " +
            "(LOWER(a.carModel) LIKE CONCAT('%', :carModel, '%')) AND " +
            "(LOWER(a.color) LIKE CONCAT('%', :color, '%'))")
    List<Auction> findByFilters(@Param("title") String title,
                                @Param("carMaker") String carMaker,
                                @Param("carModel") String carModel,
                                @Param("color") String color);
}
