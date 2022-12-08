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
@Table(name="client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Client name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Client surname should not be empty")
    @Column(name = "second_name")
    @NonNull
    private String secondName;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "telephone_number")
    @NonNull
    private String telephoneNumber;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
