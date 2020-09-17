package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Value;

public class AuctionFilters {
    @Value("")
    private String title;
    @Value("")
    private String carMaker;
    @Value("")
    private String carModel;
    @Value("")
    private String color;

    public AuctionFilters(String title, String carMaker, String carModel, String color) {
        this.title = title;
        this.carMaker = carMaker;
        this.carModel = carModel;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitleLowerCase() {
        return title != null ? title.toLowerCase() : "";
    }

    public String getCarMakerLowerCase() {
        return carMaker != null ? carMaker.toLowerCase() : "";
    }

    public String getCarModelLowerCase() {
        return carMaker != null ? carModel.toLowerCase() : "";
    }

    public String getColorLowerCase() {
        return color != null ? color.toLowerCase() : "";
    }

    @Override
    public String toString() {
        return "AuctionFilters{" +
                "title='" + title + '\'' +
                ", carMaker='" + carMaker + '\'' +
                ", carModel='" + carModel + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
