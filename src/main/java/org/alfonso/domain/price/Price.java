package org.alfonso.domain.price;

import java.util.UUID;

public class Price
{
    private final PriceId priceId;
    private final BrandId brandId;
    private final DateInRange dateInRange;
    private final Priority priority;
    private final Money money;

    public Price(PriceId priceId, BrandId brandId, DateInRange dateInRange, Priority priority, Money money)
    {
        this.priceId = priceId;
        this.brandId = brandId;
        this.dateInRange = dateInRange;
        this.priority = priority;
        this.money = money;
    }

    public PriceId getPriceId()
    {   return priceId;}

    public BrandId getBrandId()
    {   return brandId;}

    public DateInRange getDateInRange()
    {   return dateInRange;}

    public Priority getPriority()
    {   return priority;}

    public Money getMoney()
    {   return money;}
}
