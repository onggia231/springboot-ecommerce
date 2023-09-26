package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

//    đối tượng Customer có thể liên kết với nhiều đối tượng Order, trong đó Order là lớp đối tượng biểu thị cho các đơn đặt hàng của khách hàng.
//    mappedBy = "customer": Tham số này chỉ định trường nào trong đối tượng Order sẽ là trường tham chiếu đến đối tượng Customer.
//    Nó có nghĩa rằng trường customer trong Order sẽ được sử dụng để xác định khách hàng của đơn đặt hàng.

    public void add(Order order) {

        if (order != null) {

            if (orders == null) {
                orders = new HashSet<>();
            }

            orders.add(order);
            order.setCustomer(this);
        }
    }

//    Phương thức này được sử dụng để thêm một đơn đặt hàng (Order) vào danh sách đơn đặt hàng của khách hàng.
//    Nếu đơn đặt hàng là không null (order != null), nó sẽ kiểm tra xem tập hợp orders đã được khởi tạo hay chưa. Nếu chưa, nó sẽ khởi tạo một tập hợp mới.
//    Sau đó, nó thêm đơn đặt hàng vào tập hợp và cập nhật trường customer của đơn đặt hàng để liên kết với khách hàng hiện tại (order.setCustomer(this)).

}









