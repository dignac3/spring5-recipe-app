package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRITPION = "description";
    public static final Long LONG_VALUE = new Long(1L);
    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setDescription(DESCRITPION);
        command.setId(LONG_VALUE);

        UnitOfMeasure  uom = converter.convert(command);

        assertNotNull(uom.getId());
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRITPION,uom.getDescription());
    }
}