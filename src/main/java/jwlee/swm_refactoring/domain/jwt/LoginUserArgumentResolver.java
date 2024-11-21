package jwlee.swm_refactoring.domain.jwt;


import jakarta.servlet.http.HttpServletRequest;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == Admin.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        // request에서 "loginUser" 속성을 가져옴
        Object objectLoginUser = servletRequest.getAttribute("loginUser");

        // 로그인된 사용자 정보가 있으면 Admin 객체를 반환, 없으면 null 반환
        if (objectLoginUser != null && objectLoginUser instanceof Admin) {
            return (Admin) objectLoginUser;
        }

        return null;  // 로그인되지 않았으면 null 반환
    }
}