package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemUseCaseImpl implements IOrderItemUseCase {
    private OrderRepository repository;

    public OrderItemUseCaseImpl(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não está aberto.");
        }

        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setProduct(product);
        newOrderItem.setSaleValue(price);
        newOrderItem.setAmount(amount);

        List<OrderItem> orderItems = order.getItems();
        orderItems.add(newOrderItem);
        order.setItems(orderItems);

        repository.update(order);
        return newOrderItem;
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não está aberto.");
        }

        List<OrderItem> items = order.getItems();
        OrderItem updateItem = new OrderItem();

        for(OrderItem item : items) {
            if (item.getProduct() == product) {
                item.setAmount(amount);
                updateItem = item;
            }
        }
        repository.update(order);

        return updateItem;
    }

    @Override
    public void removeItem(Order order, Product product) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não está aberto.");
        }
        List<OrderItem> items = order.getItems();

        for(OrderItem item : items) {
            if (item.getProduct() == product) {
                item = null;
            }
        }
        repository.update(order);
    }
}
