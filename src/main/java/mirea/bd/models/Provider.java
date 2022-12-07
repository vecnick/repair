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
@ToString(exclude = {"repairMaterials"},includeFieldNames = false)
@Entity
@Table(name="provider")
public class Provider {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "provider name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "provider surname should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String second_name;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "telephone_number")
    @NonNull
    private String telephone_number;

    @OneToMany(mappedBy = "provider")
    private List<RepairMaterial> repairMaterials;
}
