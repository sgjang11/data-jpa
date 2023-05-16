package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    // 아무데서 호출되는 것을 막음
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

}
