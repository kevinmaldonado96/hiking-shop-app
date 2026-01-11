package org.example.hickingshop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.hickingshop.configuration.custom.CustomUser;
import org.example.hickingshop.controllers.utils.ControllerUtils;
import org.example.hickingshop.dto.OrderDto;
import org.example.hickingshop.dto.OrderProductDto;
import org.example.hickingshop.entities.Order;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.facade.OrderProductFacade;
import org.example.hickingshop.services.KafkaService;
import org.example.hickingshop.services.OrderService;
import org.example.hickingshop.services.iservices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderProductFacade orderProductFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ControllerUtils controllerUtils;

    @Operation(
            summary = "Crea una nueva orden",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid List<@Valid OrderProductDto> orderDto, BindingResult bindingResult, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(controllerUtils.getErrors(bindingResult));
        }
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(orderProductFacade.createOrder(orderDto, user));
    }

    @Operation(
            summary = "Obtener una nueva orden por id",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(orderService.getOrderDetailById(id, user.getId()));
    }

    @Operation(
            summary = "Obtener todas las ordenes",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getOrderByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(orderService.getOrdersByUser(user.getId()));
    }

    @Operation(
            summary = "Actualizar una orden por id",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody @Valid List<@Valid OrderProductDto> orderDto, BindingResult bindingResult, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(controllerUtils.getErrors(bindingResult));
        }
        return ResponseEntity.ok(orderProductFacade.updateOrder(orderDto, id, user.getId()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> sendOrder(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        try {
            kafkaService.sendMessage(id, user.getId());
            return ResponseEntity.ok("order succesfully sent");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
