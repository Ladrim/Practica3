package org.alfonso.domain.price;

import java.util.Objects;

public class BrandId
{
    private final Integer value;

    public BrandId(Integer value)
    {
        this.value = value;
    }


    public Integer getValue()
    {   return value;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BrandId brandId = (BrandId) o;
        return Objects.equals(value, brandId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
