package uz.elmurodov.meal.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.elmurodov.meal.domain.Order;
import uz.elmurodov.meal.dto.order.OrderCreateDto;
import uz.elmurodov.meal.dto.order.OrderDto;
import uz.elmurodov.meal.dto.order.OrderDto.OrderDtoBuilder;
import uz.elmurodov.meal.dto.order.OrderUpdateDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T22:16:05+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_322 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.childBuilder();

        orderDto.id( order.getId() );
        orderDto.userId( order.getUserId() );
        orderDto.mealId( order.getMealId() );

        return orderDto.build();
    }

    @Override
    public List<OrderDto> toDto(List<Order> e) {
        if ( e == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( e.size() );
        for ( Order order : e ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public Order fromCreateDto(OrderCreateDto orderCreateDto) {
        if ( orderCreateDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setMealId( orderCreateDto.getMealId() );

        return order;
    }

    @Override
    public Order fromUpdateDto(OrderUpdateDto d) {
        if ( d == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( d.getId() );
        order.setMealId( d.getMealId() );
        order.setUserId( d.getUserId() );

        return order;
    }

    @Override
    public Order fromUpdateDto(OrderUpdateDto dto, Order order) {
        if ( dto == null ) {
            return null;
        }

        if ( dto.getId() != null ) {
            order.setId( dto.getId() );
        }
        if ( dto.getMealId() != null ) {
            order.setMealId( dto.getMealId() );
        }
        if ( dto.getUserId() != null ) {
            order.setUserId( dto.getUserId() );
        }

        return order;
    }
}
