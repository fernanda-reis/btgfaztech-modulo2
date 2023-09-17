package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IPaymentNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {
    private OrderRepository repository;
    private IPaymentNotifierUseCase notifier;

    public OrderPlaceUseCaseImpl(OrderRepository repository, IPaymentNotifierUseCase notifier){
        this.repository = repository;
        this.notifier = notifier;
    }
    @Override
    public void placeOrder(Order order) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não está aberto.");
        }

        List<OrderItem> items = order.getItems();
        if (items.size() > 0) {
            BigDecimal totalValue = BigDecimal.valueOf(0);

            for (OrderItem item : items) {
                totalValue = totalValue.add(item.getSaleValue());
            }

            if (totalValue != BigDecimal.ZERO) {
                notifier.notifyPendingPayment(order);
                order.setStatus(OrderStatus.PENDING_PAYMENT);

            }
        }
    }
}
