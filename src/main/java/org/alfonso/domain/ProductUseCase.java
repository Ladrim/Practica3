package org.alfonso.domain;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.List;
import java.util.Optional;

public class ProductUseCase
{
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository)
    {
        this.repository = repository;
    }

    public void addProduct(Product product)
    {
        Optional<Product> optionalProduct = repository.findProduct(product.getProductId());

        if(optionalProduct.isEmpty())
        {
            repository.addProduct(product);
        }
        else
        {
            throw new RuntimeException("Ya hay un producto con ese ID");
        }
    }

    public Optional<Product> findProduct(ProductId id)
    {
        return repository.findProduct(id);
    }

    public List<Product> findAllProducts()
    {
        return repository.findAllProducts();
    }

    public void deleteProduct(ProductId id)
    {
        repository.deleteProduct(id);
    }
}

