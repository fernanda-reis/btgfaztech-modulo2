package br.ada.customer.crud.integration.sms;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IPaymentNotifierUseCase;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;

public class PaymentEmailNotifierImpl implements IPaymentNotifierUseCase {

    private SendSms sendSms;

    public PaymentEmailNotifierImpl(SendSms sendSms) {
        this.sendSms = sendSms;
    }


    @Override
    public void notifyPendingPayment(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Pedido confirmado. Aguardando pagamento.");

    }

    @Override
    public void notifyConfirmedPayment(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Seu pedido já foi enviado.");
    }
}
