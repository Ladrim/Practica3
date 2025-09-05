package org.alfonso.domain.product;

import org.alfonso.application.DateFormatter;
import org.alfonso.domain.price.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    DateFormatter df = new DateFormatter();

    //Price1------------------------------------------------------------------------------------------------------------
    PriceId priceId1 = new PriceId(UUID.fromString("5f26d134-d348-4857-9a07-653270c31765"));
    BrandId brandId1 = new BrandId(1);
    Date startDate1 = df.stringParser("2020-06-14 00:00:00");
    Date endDate1 = df.stringParser("2020-12-31 23:59:59");
    DateInRange dateInRange1 = new DateInRange(startDate1, endDate1);
    Priority priority1 = new Priority(0);
    CurrencyType currencyType1 = CurrencyType.valueOf("EUR");
    Float amount1 = 35.50f;
    Money money1 = new Money(currencyType1, amount1);

    Price price1 = new Price(priceId1, brandId1, dateInRange1, priority1, money1);

    //Price2------------------------------------------------------------------------------------------------------------
    PriceId priceId2 = new PriceId(UUID.fromString("a33fd1fe-7ccc-4b2b-a1d6-c6f9cb21668e"));
    BrandId brandId2 = new BrandId(1);
    Date startDate2 = df.stringParser("2020-06-14 15:00:00");
    Date endDate2 = df.stringParser("2020-06-14 18:30:00");
    DateInRange dateInRange2 = new DateInRange(startDate2, endDate2);
    Priority priority2 = new Priority(1);
    CurrencyType currencyType2 = CurrencyType.valueOf("EUR");
    Float amount2 = 25.45f;
    Money money2 = new Money(currencyType2, amount2);

    Price price2 = new Price(priceId2, brandId2, dateInRange2, priority2, money2);

    //Price3------------------------------------------------------------------------------------------------------------
    PriceId priceId3 = new PriceId(UUID.fromString("c34922fc-2f23-422d-96c0-b59c14894535"));
    BrandId brandId3 = new BrandId(1);
    Date startDate3 = df.stringParser("2020-06-15 00:00:00");
    Date endDate3 = df.stringParser("2020-06-15 11:00:00");
    DateInRange dateInRange3 = new DateInRange(startDate3, endDate3);
    Priority priority3 = new Priority(1);
    CurrencyType currencyType3 = CurrencyType.valueOf("EUR");
    Float amount3 = 30.50f;
    Money money3 = new Money(currencyType3, amount3);

    Price price3 = new Price(priceId3, brandId3, dateInRange3, priority3, money3);

    //Price4------------------------------------------------------------------------------------------------------------
    PriceId priceId4 = new PriceId(UUID.fromString("dcfd086b-97d3-48a9-b3d3-29fd36bb2642"));
    BrandId brandId4 = new BrandId(1);
    Date startDate4 = df.stringParser("2020-06-15 16:00:00");
    Date endDate4 = df.stringParser("2020-12-31 23:59:59");
    DateInRange dateInRange4 = new DateInRange(startDate4, endDate4);
    Priority priority4 = new Priority(1);
    CurrencyType currencyType4 = CurrencyType.valueOf("EUR");
    Float amount4 = 38.95f;
    Money money4 = new Money(currencyType4, amount4);

    Price price4 = new Price(priceId4, brandId4, dateInRange4, priority4, money4);

    //Product-----------------------------------------------------------------------------------------------------------

    ProductId productId = new ProductId(UUID.fromString("3d5542ca-9575-47e3-a177-e1f822833aef"));
    ProductName productName = new ProductName("pantalon");
    List<Price> prices = Arrays.asList(price1, price2, price3, price4);


    @Nested
    class ReturnsAnEmptyOptional
    {
        @Test
        public void whenBraidIdAndDateAreNotInAnyPriceItShouldReturnAnEmptyOptional() {
            BrandId brandId = new BrandId(2);
            Date date = df.stringParser("2025-06-14 15:00:00");

            Product underTest = new Product(productId, productName, prices);
            Optional<Price> result = underTest.findPrice(brandId, date);
            Assertions.assertThat(result).isEmpty();
        }

        @Test
        public void whenOnlyDateIsInAnyPriceItShouldReturnAnEmptyOptional() {
            BrandId brandId = new BrandId(2);
            Date date = df.stringParser("2020-06-14 15:30:00");

            Product underTest = new Product(productId, productName, prices);
            Optional<Price> result = underTest.findPrice(brandId, date);
            Assertions.assertThat(result).isEmpty();
        }

        @Test
        public void whenOnlyBraidIdIsInAnyPriceItShouldReturnAnEmptyOptional() {
            BrandId brandId = new BrandId(1);
            Date date = df.stringParser("2025-06-14 15:30:00");

            Product underTest = new Product(productId, productName, prices);
            Optional<Price> result = underTest.findPrice(brandId, date);
            Assertions.assertThat(result).isEmpty();
        }
    }

    @Nested
    class NotReturnsAnEmptyOptional
    {
        @Test
        public void whenBraidIdAndDateAreInAnyPriceItShouldNotReturnAnEmptyOptional() {
            BrandId brandId = new BrandId(1);
            Date date = df.stringParser("2020-06-14 15:30:00");

            Product underTest = new Product(productId, productName, prices);
            Optional<Price> result = underTest.findPrice(brandId, date);
            Assertions.assertThat(result).isNotEmpty();
        }
    }


    @Test
    public void whenTwoPricesMatchesItReturnsTheOneWithHigherPriority()
    {
        BrandId brandId = new BrandId(1);
        Date date = df.stringParser("2020-06-14 15:30:00");

        Product underTest = new Product(productId, productName, prices);

        Optional<Price> result = underTest.findPrice(brandId, date);

        Optional<Price> expectedResult = Optional.ofNullable(price2);

        Assertions.assertThat(result).isNotEmpty();
    }
}