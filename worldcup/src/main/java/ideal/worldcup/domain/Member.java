package ideal.worldcup.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String nickname;



    public static Member createMember(String password, String email, String nickname) {
        Member member = new Member();
        member.setPassword(password);
        member.setEmail(email);
        member.setNickname(nickname);

        return member;
    }
}
