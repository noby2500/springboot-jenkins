package co.th.best.springbootjenkins.controller;

import co.th.best.springbootjenkins.entity.Product;
import co.th.best.springbootjenkins.model.ProductDto;
import co.th.best.springbootjenkins.model.req.ProductReq;
import co.th.best.springbootjenkins.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getproducts")
    public @ResponseBody ResponseEntity<?> getProducts() throws Exception {
        List<Product> productList = productService.getProductAll();

        if(!productList.isEmpty()) {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }else {
            throw new Exception();
        }

    }

    @PostMapping(value = "/product")
    public @ResponseBody ResponseEntity<?> product(@RequestBody ProductReq req) throws Exception{
        ProductDto product = productService.getProduct(req);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
