package mirea.bd.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"services"},includeFieldNames = false)
@Entity
@Table(name="order_execution_process")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Textile name should not be empty")
    @Column(name = "technique_name")
    @NonNull
    private String name;

    @Column(name = "total_price")
    @NonNull
    private Integer totalPrice;

    @ManyToMany
    @JoinTable(
            name = "service_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="service_id")
    )
    private List<Service>services;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private List<Condition> conditions;
}
