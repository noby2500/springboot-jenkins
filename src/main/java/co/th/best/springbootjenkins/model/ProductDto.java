package co.th.best.springbootjenkins.model;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @Id
    String barcode;
    String name;
    Double price;
}
