package uz.elmurodov.meal.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.elmurodov.meal.domain.Order;
import uz.elmurodov.meal.dto.order.OrderCreateDto;
import uz.elmurodov.meal.dto.order.OrderDto;
import uz.elmurodov.meal.dto.order.OrderUpdateDto;
import uz.elmurodov.meal.dto.response.AppErrorDto;
import uz.elmurodov.meal.dto.response.DataDto;
import uz.elmurodov.meal.mappers.OrderMapper;
import uz.elmurodov.meal.repository.OrderRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService implements AbstractService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    public OrderService(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseEntity<DataDto<List<OrderDto>>> getAllByDate() {
        List<Order> menus = repository.findAll();
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(menus), (long) menus.size()), HttpStatus.OK);
    }


    public ResponseEntity<DataDto<OrderDto>> get(Long id) {
        Optional<Order> mealOptional = repository.findById(id);
//        if (mealOptional.isEmpty())
//            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().build()), HttpStatus.OK);

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(mealOptional.get())), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Order> mealOptional = repository.findById(id);
//        if (mealOptional.isEmpty())
//            return new ResponseEntity<>(new DataDto<>(AppErrorDto
//                    .builder()
//                    .status(HttpStatus.NOT_FOUND)
//                    .build()), HttpStatus.OK);
        repository.delete(mealOptional.get());
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }


    public Long createFromTelegramBot(@NotNull OrderCreateDto dto) {
        Order order = mapper.fromCreateDto(dto);
        repository.save(order);
        return order.getId();
    }

    public ResponseEntity<DataDto<Long>> create(OrderCreateDto dto) {
        return null;
    }

    public ResponseEntity<DataDto<Boolean>> update(OrderUpdateDto dto) {
        Optional<Order> mealOptional = repository.findById(dto.getId());
//        if (mealOptional.isEmpty())
//            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
//                    .status(HttpStatus.NOT_FOUND)
//                    .message("Order not found by id : '%s".formatted(dto.getId()))
//                    .build()), HttpStatus.OK);
        Order meal = mapper.fromUpdateDto(dto, mealOptional.get());
        repository.save(meal);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

}
