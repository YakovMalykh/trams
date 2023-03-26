package metro.trams.mappers;

import metro.trams.dto.RouteDto;
import metro.trams.entity.Route;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public abstract class MapperDto {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateRouteFromDto(RouteDto routeDto, @MappingTarget Route route);
}
