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
@ToString(exclude = {"orders"},includeFieldNames = false)
@Entity
@Table(name="service")
public class Service {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Service name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Service name should not be empty")
    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "service_price")
    @NonNull
    private Integer servicePrice;

    @ManyToMany(mappedBy = "services")
    private List<Order>orders;

    @ManyToOne
    @JoinColumn(name="material_id", referencedColumnName = "id")
    private RepairMaterial repairMaterial;
}
