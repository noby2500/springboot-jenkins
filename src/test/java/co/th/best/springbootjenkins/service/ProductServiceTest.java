package co.th.best.springbootjenkins.service;


import co.th.best.springbootjenkins.entity.Product;
import co.th.best.springbootjenkins.model.ProductDto;
import co.th.best.springbootjenkins.model.req.ProductReq;
import co.th.best.springbootjenkins.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {
    @InjectMocks
    private ProductService service = new ProductService();
    @Mock
    private ProductRepo repo;
    private Product product = new Product();
    private List<Product> productList = new ArrayList<>();

    private ProductDto productDto = new ProductDto();


    private Date returnDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(date);
    }
    @BeforeEach
    public void setup() throws ParseException {
    mockProductList();

    }
    @Test
    public void test_getProductAll_success_case() throws Exception {
        Mockito.when(repo.findAll()).thenReturn(productList);
        assertEquals(productList,service.getProductAll());

    }

    @Test
    public void test_getProductAll_null_case() throws Exception {
        assertNotEquals(productList,service.getProductAll());

    }
    @Test
    public void test_getProduct_success_case() throws Exception {
        mockProduct();
        mockProductDto();
        ProductReq req = new ProductReq();
        req.setId("1");
        Mockito.when(repo.findById("1")).thenReturn(Optional.ofNullable(product));
        assertEquals(productDto.getBarcode(),service.getProduct(req).getBarcode());
    }


    private List<Product> mockProductList() throws ParseException {
        product.setBarcode("11111");
        product.setDate(returnDate("2022-07-20"));
        product.setName("test");
        product.setPrice(10.50);
        product.setId("1");
        productList.add(product);
        return productList;
    }

    private Product mockProduct() throws ParseException {
        product.setBarcode("11111");
        product.setDate(returnDate("2022-07-20"));
        product.setName("test");
        product.setPrice(10.50);
        product.setId("1");

        return product;
    }

    private ProductDto mockProductDto (){

        productDto.setBarcode("11111");
        productDto.setName("test");
        productDto.setPrice(10.50);

        return productDto;
    }
}
