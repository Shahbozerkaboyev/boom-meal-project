package uz.elmurodov.meal.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.elmurodov.meal.domain.Menu;
import uz.elmurodov.meal.dto.menu.MenuCreateDto;
import uz.elmurodov.meal.dto.menu.MenuDto;
import uz.elmurodov.meal.dto.menu.MenuDto.MenuDtoBuilder;
import uz.elmurodov.meal.dto.menu.MenuUpdateDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T22:16:05+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_322 (Amazon.com Inc.)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public MenuDto toDto(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDtoBuilder menuDto = MenuDto.childBuilder();

        menuDto.id( menu.getId() );
        menuDto.mealId( menu.getMealId() );

        return menuDto.build();
    }

    @Override
    public List<MenuDto> toDto(List<Menu> e) {
        if ( e == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( e.size() );
        for ( Menu menu : e ) {
            list.add( toDto( menu ) );
        }

        return list;
    }

    @Override
    public Menu fromCreateDto(MenuCreateDto menuCreateDto) {
        if ( menuCreateDto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setMealId( menuCreateDto.getMealId() );

        return menu;
    }

    @Override
    public Menu fromUpdateDto(MenuUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( dto.getId() );
        menu.setMealId( dto.getMealId() );

        return menu;
    }

    @Override
    public Menu fromUpdateDto(MenuUpdateDto dto, Menu menu) {
        if ( dto == null ) {
            return null;
        }

        if ( dto.getId() != null ) {
            menu.setId( dto.getId() );
        }
        if ( dto.getMealId() != null ) {
            menu.setMealId( dto.getMealId() );
        }

        return menu;
    }
}
