package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

//        purchase: chứa thông tin về đơn hàng và các mặt hàng trong đơn hàng.

        // retrieve the order info from dto
//        lấy thông tin đơn hàng từ purchase.
        Order order = purchase.getOrder();

        // generate tracking number
//         tạo một mã số theo dõi đơn hàng
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
//        lấy danh sách các mặt hàng trong đơn hàng từ
        Set<OrderItem> orderItems = purchase.getOrderItems();
//        và thêm chúng vào đơn hàng
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
//         thêm thông tin về địa chỉ thanh toán
        order.setBillingAddress(purchase.getBillingAddress());
//        địa chỉ giao hàng
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
//         lấy thông tin khách hàng
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}









