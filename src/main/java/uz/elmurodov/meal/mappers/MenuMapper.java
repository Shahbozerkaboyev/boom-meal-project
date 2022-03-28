package uz.elmurodov.meal.mappers;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.elmurodov.meal.domain.Meal;
import uz.elmurodov.meal.domain.Menu;
import uz.elmurodov.meal.dto.meal.MealUpdateDto;
import uz.elmurodov.meal.dto.menu.MenuCreateDto;
import uz.elmurodov.meal.dto.menu.MenuDto;
import uz.elmurodov.meal.dto.menu.MenuUpdateDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MenuMapper  extends BaseMapper<
        Menu,
        MenuDto,
        MenuCreateDto,
        MenuUpdateDto> {
    @Override
    MenuDto toDto(Menu menu);

    @Override
    List<MenuDto> toDto(List<Menu> e);

    @Override
    Menu fromCreateDto(MenuCreateDto menuCreateDto);

    @Override
    Menu fromUpdateDto(MenuUpdateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Menu fromUpdateDto(MenuUpdateDto dto, @MappingTarget Menu menu);

}
