package pl.codementors.finalstore.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "nickname"})) //dodalem unique constraint na email i nickname ie. nie moze byc uzytkownikow o dwoch takich samych nickach i emailach
public class User {

    public enum Role {
        ADMIN,
        USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private boolean isAccepted;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany (mappedBy = "user")
    private List<Order> usersOrders;

    public User() {
    }

    public User(String name, String surname, String email, String nickname, String password, boolean isAccepted, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.isAccepted = isAccepted;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getUsersOrders() {
        return usersOrders;
    }

    public void setUsersOrders(List<Order> usersOrders) {
        this.usersOrders = usersOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isAccepted == user.isAccepted &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, nickname, password, isAccepted, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", isAccepted=" + isAccepted +
                ", role=" + role +
                '}';
    }
}
