package org.alfonso.domain;

import org.alfonso.application.PriceByProductIdBrandIdDateCommand;
import org.alfonso.application.ProductRepository;
import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PriceUseCase
{
    private final ProductRepository repository;

    public PriceUseCase(ProductRepository repository)
    {
        this.repository = repository;
    }

    public Optional<Price> findPrice(PriceByProductIdBrandIdDateCommand command)
    {
        Optional<Product> optionalProduct = repository.findProduct(command.getProductId());
        return optionalProduct.flatMap(it->it.findPrice(command.getBrandId(), command.getDate()));
    }
}
