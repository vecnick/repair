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
@ToString(exclude = {"orders","post","branch"},includeFieldNames = false)
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Employee name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Employee surname should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String secondName;


    @Column(name = "patronymic")
    @NonNull
    private String patronymic;


    @Column(name = "gender")
    @NonNull
    private String gender;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="branch_id", referencedColumnName = "id")
    private Branch branch;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}
