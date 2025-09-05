package org.alfonso.domain.product;

import org.alfonso.domain.price.BrandId;
import org.alfonso.domain.price.Price;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Product
{
    private final ProductId productId;
    private final ProductName productName;
    private final List<Price> prices;

    public Product(ProductId productId, ProductName productName, List<Price> prices)
    {
        this.productId = productId;
        this.productName = productName;
        this.prices = prices;
    }

    public ProductId getProductId()
    {   return productId;}

    public ProductName getProductName()
    {   return productName;}

    public List<Price> getPrices()
    {   return prices;}

    public Optional<Price> findPrice(BrandId id, Date date)
    {
        return prices.stream()
            .filter(it->it.getBrandId().equals(id))
            .filter(it->it.getDateInRange().isInRange(date))
            .max(Comparator.comparing(it->it.getPriority().getValue()));
    }
}
