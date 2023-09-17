package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.IPaymentNotifierUseCase;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase {
    private OrderRepository repository;
    private IPaymentNotifierUseCase notifier;

    public OrderPayUseCaseImpl(OrderRepository repository, IPaymentNotifierUseCase notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }
    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Pedido não está recebendo pagamento.");
        }
        order.setStatus(OrderStatus.PAID);
        notifier.notifyConfirmedPayment(order);
    }
}
