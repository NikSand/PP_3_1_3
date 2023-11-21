package springsecurity.springbootsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    @NotBlank(message = "Имя не должно быть пустым")
    @Pattern(regexp = "^[^\\d]*$", message = "В поле не должно быть цифр")
    @Size(min = 1, max = 50, message = "Имя должно быть больше 0, но меньше символов 50")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "^[^\\d]*$", message = "В поле не должно быть цифр")
    @Size(min = 1, max = 50, message = "Фамилия не должна быть больше 0, но меньше символов 50")
    private String surname;

    @Column(name = "age")
    @NotNull(message = "Возраст не должен быть пустым")
    @Min(value = 0, message = "Возраст не может быть меньше 0")
    @Max(value = 100, message = "Возраст не может быть больше 100")
    private Byte age;

    @Column(name = "citizenship")
    @NotBlank(message = "Гражданство не может быть пустым")
    @Size(min = 1, max = 50, message = "Длина больше 0 символов, но меньше 50")
    private String citizenship;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Длина больше 0 символов, но меньше 50")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Поле не может быть пустым")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String name, String surname, Byte age, String citizenship, String username, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.citizenship = citizenship;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public User() {

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age) && Objects.equals(citizenship, user.citizenship) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, age, citizenship, username, password, roles);
    }
}