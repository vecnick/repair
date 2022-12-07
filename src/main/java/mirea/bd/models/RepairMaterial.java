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
@ToString(exclude = {"provider","services"},includeFieldNames = false)
@Entity
@Table(name="repair_material")
public class RepairMaterial {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Material name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "retail_price")
    @NonNull
    private Integer retailPrice;

    @Column(name = "amount")
    @NonNull
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="provider_id", referencedColumnName = "id")
    private Provider provider;

    @OneToMany(mappedBy = "repairMaterial")
    private List<Service> services;
}
