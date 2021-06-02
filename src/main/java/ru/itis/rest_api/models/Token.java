package ru.itis.rest_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rest_api.security.token.TokensUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String token;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private Date time_of_creating;

    @Transient
    private TokensUtil tokensUtil;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token(long parseLong, String new_refresh_token, Date date) {
        id = parseLong;
        refreshToken = new_refresh_token;
        time_of_creating = date;
    }

    public Token(String new_refresh_token, Date date, User user) {
        this.user = user;
        refreshToken = new_refresh_token;
        time_of_creating = date;
    }

    public User getUser() {
        return user;
    }
//    public Token(String id, String new_refresh_token, Date date) {
//        id = Long.parseLong(id);
//    }
}
