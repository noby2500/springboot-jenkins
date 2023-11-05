package co.th.best.springbootjenkins.controller;

import co.th.best.springbootjenkins.entity.Product;
import co.th.best.springbootjenkins.entity.Customer;
import co.th.best.springbootjenkins.model.ProductDto;
import co.th.best.springbootjenkins.model.req.ProductReq;
import co.th.best.springbootjenkins.service.CustomerService;
import co.th.best.springbootjenkins.service.ProductService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

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

    @PostMapping("/upload")
    public void uploadCsvData(@RequestParam("file") MultipartFile file) {
            customerService.saveCsvData(file);
    }
    @GetMapping("/getcustomer")
    public List<Customer> getCsvData() {
        List<Customer> csvDataList = customerService.getCsvData();
        // Decrypt sensitive data before returning the response
        csvDataList.forEach(csvData -> {
            csvData.setNId(customerService.decryptData(csvData.getNId()));
            csvData.setMsisdn(customerService.decryptData(csvData.getMsisdn()));
        });
        return csvDataList;
    }

}
