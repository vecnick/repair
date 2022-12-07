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
@ToString(exclude = {"employees"},includeFieldNames = false)
@Entity
@Table(name="branch")
public class Branch {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "branch address should not be empty")
    @Column(name = "address")
    @NonNull
    private String address;


    @Column(name = "rating_indicator")
    @NonNull
    private int ratingIndicator;

    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;
}
