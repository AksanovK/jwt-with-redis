package ru.itis.rest_api.security.token;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.rest_api.exception.AccessTokenIncorrect;
import ru.itis.rest_api.repositories.TokensRepository;
import ru.itis.rest_api.repositories.UsersRepository;
import ru.itis.rest_api.services.JwtBlackListService;
import ru.itis.rest_api.services.RefreshService;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    public RefreshService refreshService;

    @Autowired
    public TokensRepository tokensRepository;

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public JwtBlackListService jwtBlackListService;

    @SneakyThrows
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = request.getHeader("ACCESS-TOKEN");
        if (token != null) {

//            if (jwtBlackListService.exists(token)) {
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }

            if (refreshService.accessCheck(token)) {
                TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                filterChain.doFilter(request, response);
            } else {
                throw new AccessTokenIncorrect();
            }
        }
        filterChain.doFilter(request, response);
    }


}
