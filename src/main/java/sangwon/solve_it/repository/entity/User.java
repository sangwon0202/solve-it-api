package sangwon.solve_it.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name="id")
    private String id;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
