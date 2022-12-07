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
@Table(name="post")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Post name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;


    @NotEmpty(message = "Post duties should not be empty")
    @Column(name = "duties")
    @NonNull
    private String duties;

    @NotEmpty(message = "Post requirements should not be empty")
    @Column(name = "requirements")
    @NonNull
    private String requirements;

    @OneToMany(mappedBy = "post")
    private List<Employee> employees;
}
