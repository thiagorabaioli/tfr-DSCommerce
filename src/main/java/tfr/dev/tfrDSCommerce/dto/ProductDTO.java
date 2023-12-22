package tfr.dev.tfrDSCommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import tfr.dev.tfrDSCommerce.entities.Product;

public class ProductDTO {

    private Long id;


   @NotBlank(message = "can't be null")
   @Size(min = 8, max = 80, message = "the name must belong to the range minimum 8 and maximum 80")
    private String name;

    @NotBlank(message = "can't be null")
    @Size(min = 8, message = "the name must have minimum 8 characters ")
    private String description;

    @Positive
    private Double price;
    private String imgUrl;

    public ProductDTO(){}

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
