package co.th.best.springbootjenkins.service;

import co.th.best.springbootjenkins.entity.Product;
import co.th.best.springbootjenkins.model.ProductDto;
import co.th.best.springbootjenkins.model.req.ProductReq;
import co.th.best.springbootjenkins.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService  {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProductAll() {
        return productRepo.findAll();
    }
    public ProductDto getProduct(ProductReq productReq){
        Optional<Product> productOpt = productRepo.findById(productReq.getId());
        ProductDto productDto = new ProductDto();
        if (productOpt.isPresent()) {
            productDto.setBarcode(productOpt.get().getBarcode());
            productDto.setName(productOpt.get().getName());
            productDto.setPrice(productOpt.get().getPrice());
        }
        return productDto;
    }

}
