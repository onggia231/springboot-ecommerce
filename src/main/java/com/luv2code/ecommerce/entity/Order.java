package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();
//    mỗi đối tượng Order có thể liên kết với nhiều đối tượng OrderItem, trong đó OrderItem là lớp đối tượng biểu thị cho các mặt hàng trong đơn hàng
//    cascade = CascadeType.ALL: Tham số này cho phép các thao tác cập nhật và xóa đối tượng Order cũng được áp dụng cho các đối tượng OrderItem liên quan.
//    mappedBy = "order": Tham số này chỉ định trường nào trong đối tượng OrderItem sẽ là trường tham chiếu đến đối tượng Order.
//    Nó có nghĩa rằng trường order trong OrderItem sẽ được sử dụng để xác định đơn hàng mà các mặt hàng thuộc về.

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

// ManyToOne va OneToOne:   một đơn đặt hàng với một khách hàng và hai địa chỉ (địa chỉ giao hàng và địa chỉ thanh toán).

    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }

//    Phương thức này được sử dụng để thêm một đối tượng OrderItem vào danh sách các mặt hàng của đơn đặt hàng.
//    Nếu đối tượng OrderItem là không null (item != null), nó sẽ kiểm tra xem tập hợp orderItems đã được khởi tạo hay chưa. Nếu chưa, nó sẽ khởi tạo một tập hợp mới.
//    Sau đó, nó thêm đối tượng OrderItem vào tập hợp và cập nhật trường order của OrderItem để liên kết với đơn đặt hàng hiện tại (item.setOrder(this)).
}









