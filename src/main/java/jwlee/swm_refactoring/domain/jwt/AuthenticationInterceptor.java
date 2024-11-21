package jwlee.swm_refactoring.domain.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.exceptions.ExpiredTokenException;
import jwlee.swm_refactoring.domain.exceptions.InvalidTokenException;
import jwlee.swm_refactoring.domain.exceptions.UnauthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final JwtInterceptorHelper jwtInterceptorHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accessToken = jwtInterceptorHelper.extractAccessTokenFromRequest(request);
            Admin loginUser = jwtUtil.getLoginUserFromAccessToken(accessToken);
            request.setAttribute("loginUser", loginUser);
        } catch (ExpiredTokenException ete) {
            // 만료된 토큰일 경우 리프레시 토큰 로직을 추가할 수 있음
            request.setAttribute("loginUser", null);  // 로그인되지 않음
        } catch (InvalidTokenException ite) {
            request.setAttribute("loginUser", null);  // 로그인되지 않음
        }
        return true;
    }
}
