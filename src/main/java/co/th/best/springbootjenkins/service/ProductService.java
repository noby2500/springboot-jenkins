package co.th.best.springbootjenkins.service;

import co.th.best.springbootjenkins.entity.Product;
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
        List<Product> productList = productRepo.findAll();
        return productList;
    }
    public Product getProduct(ProductReq productReq){
        Optional<Product> productOpt = productRepo.findById(productReq.getId());
        return productOpt.get();
    }

}
