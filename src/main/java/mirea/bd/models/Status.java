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
@ToString(exclude = {"conditions"},includeFieldNames = false)
@Entity
@Table(name="status")
public class Status {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Status name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Condition> conditions;
}
