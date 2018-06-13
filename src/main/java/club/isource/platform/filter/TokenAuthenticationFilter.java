package club.isource.platform.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import club.isource.platform.dao.po.User;
import club.isource.platform.service.inf.UserService;
import club.isource.platform.utils.JWTUtils;
import club.isource.platform.vo.TokenUser;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private JWTUtils jWTUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 获取请求头信息的权限值
		String authHeader = request.getHeader("Authorization");
        //String tokenHead = "isource ";
        if (authHeader != null 
        		//&& authHeader.startsWith(tokenHead)
        		) {
            //final String authToken = authHeader.substring(tokenHead.length());
            // 获取token中的用户名信息
            String username = jWTUtils.getUsernameFromToken(authHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            	// 加载用户信息
                 User userDetails = new User();
                 userDetails.setUsername("test");
                 userDetails.setPassword("123131");
                		 //this.userServiceImpl.selectUserByKey("1");
                 TokenUser user = new TokenUser(userDetails.getUsername(), userDetails.getPassword(), null);
                // 验证token
                if (jWTUtils.validateToken(authHeader, user)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
	}

}
