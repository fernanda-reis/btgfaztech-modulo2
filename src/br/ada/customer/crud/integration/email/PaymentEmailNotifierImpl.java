package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IPaymentNotifierUseCase;

public class PaymentEmailNotifierImpl implements IPaymentNotifierUseCase {
    private SendEmail sendEmail;

    public PaymentEmailNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notifyPendingPayment(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Pedido confirmado. Aguardando pagamento.");

    }

    @Override
    public void notifyConfirmedPayment(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "O pagamento foi realizado.");

    }
}
