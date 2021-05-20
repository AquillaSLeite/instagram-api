package br.com.leite.aquilla.instagramapi.entity;

import br.com.leite.aquilla.instagramapi.enums.AccountTypeEnum;
import br.com.leite.aquilla.instagramapi.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 13)
    private String phone;
    @Convert(converter = GenderEnum.GenderEnumConverter.class)
    @Column(nullable = false, length = 1)
    private GenderEnum gender;
    @Convert(converter = AccountTypeEnum.AccountTypeEnumConverter.class)
    @Column(nullable = false, length = 1)
    private AccountTypeEnum accountType;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following"))
    private final Set<User> following = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "following")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private final Set<User> followers = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private final Set<Post> posts = new HashSet<>();
}
