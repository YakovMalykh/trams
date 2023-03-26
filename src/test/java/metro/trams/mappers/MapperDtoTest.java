package metro.trams.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static metro.trams.constants.ConstantsForTests.*;

class MapperDtoTest {

    MapperDto mapper = Mappers.getMapper(MapperDto.class);

    @BeforeEach
    void setUp() {
        ROUTE_DTO_1.setCode("R111");
        ROUTE_DTO_1.setName("first-second");

        ROUTE_DTO_2.setCode("UUUU");

        ROUTE_1.setId(1L);
        ROUTE_1.setCode("0000");
        ROUTE_1.setName("start-end");
    }

    @Test
    void updateAllFieldsRouteFromDto() {

        mapper.updateRouteFromDto(ROUTE_DTO_1, ROUTE_1);

        Assertions.assertEquals("R111", ROUTE_1.getCode());
        Assertions.assertEquals("first-second", ROUTE_1.getName());
    }

    @Test
    void updatePartFieldsRouteFromDto() {

        mapper.updateRouteFromDto(ROUTE_DTO_2, ROUTE_1);

        Assertions.assertEquals("UUUU", ROUTE_1.getCode());
        Assertions.assertEquals("start-end", ROUTE_1.getName());
    }
}