package br.com.muniz.aws_project01.repository;

import br.com.muniz.aws_project01.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByCode(String code);

}
