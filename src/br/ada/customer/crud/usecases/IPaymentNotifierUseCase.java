package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IPaymentNotifierUseCase {

    void notifyPendingPayment(Order order);

    void notifyConfirmedPayment(Order order);

}
