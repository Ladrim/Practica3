package org.alfonso.domain.price;

import org.alfonso.application.DateFormatter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateInRangeTest
{

    @Nested
    class OutOfRange
    {
        @Test
        public void whenDateIsLowerThanStartDateItShouldReturnFalse()
        {
            DateFormatter df = new DateFormatter();

            Date actualDate = df.stringParser("2000-01-01 10:00:00");
            Date startDate = df.stringParser("2001-01-01 10:00:00");
            Date endDate = df.stringParser("2005-01-01 10:10:00");

            DateInRange underTest = new DateInRange(startDate,endDate);

            Assertions.assertThat(underTest.isInRange(actualDate)).isFalse();
        }

        @Test
        public void whenDateIsHigherTHanEndDateItShouldReturnFalse()
        {
            DateFormatter df = new DateFormatter();

            Date actualDate = df.stringParser("2006-01-01 10:00:00");
            Date startDate = df.stringParser("2001-01-01 10:00:00");
            Date endDate = df.stringParser("2005-01-01 10:10:00");

            DateInRange underTest = new DateInRange(startDate,endDate);

            Assertions.assertThat(underTest.isInRange(actualDate)).isFalse();
        }
    }

    @Nested
    class InRange
    {
        @Test
        public void whenDateIsBetweenStartDateAndEndDateItShouldReturnTrue()
        {
            DateFormatter df = new DateFormatter();

            Date actualDate = df.stringParser("2004-01-01 10:00:00");
            Date startDate = df.stringParser("2001-01-01 10:00:00");
            Date endDate = df.stringParser("2005-01-01 10:10:00");

            DateInRange underTest = new DateInRange(startDate,endDate);

            Assertions.assertThat(underTest.isInRange(actualDate)).isTrue();
        }
    }


}