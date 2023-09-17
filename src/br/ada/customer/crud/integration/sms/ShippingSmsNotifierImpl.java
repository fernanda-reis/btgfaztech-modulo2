package br.ada.customer.crud.integration.sms;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierUserCase;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;

public class ShippingSmsNotifierImpl implements IShippingNotifierUseCase {

    private SendSms sendSms;

    public ShippingSmsNotifierImpl(SendSms sendSms) {
        this.sendSms = sendSms;
    }


    @Override
    public void notify(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Seu pedido já foi enviado.");
    }
}
