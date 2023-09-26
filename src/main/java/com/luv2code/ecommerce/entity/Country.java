package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> states;

//    Mối quan hệ OneToMany:

//    @OneToMany(mappedBy = "country"): Đây là một annotation để xác định mối quan hệ một-nhiều giữa Country và State.
//    Điều này nghĩa là mỗi đối tượng Country có thể liên kết với nhiều đối tượng State, trong đó State là lớp đối tượng biểu thị cho các tỉnh/thành phố trong quốc gia.
//    mappedBy = "country": Tham số này chỉ định trường nào trong đối tượng State sẽ là trường tham chiếu đến đối tượng Country.
//    Nó có nghĩa rằng trường country trong State sẽ được sử dụng để xác định quốc gia mà các tỉnh/thành phố thuộc về.

//    @JsonIgnore:
//    Annotation @JsonIgnore được sử dụng để thông báo cho Jackson (thư viện được sử dụng để serialize và deserialize JSON trong Spring)
//    rằng phải bỏ qua trường states khi chuyển đổi đối tượng Country thành định dạng JSON. Điều này làm tránh việc xảy ra vòng lặp vô tận
//    trong quá trình serialization JSON, do mối quan hệ một-nhiều giữa Country và State

}










