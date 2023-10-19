package co.th.best.springbootjenkins.repository;

import co.th.best.springbootjenkins.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, String> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(String s);
}
