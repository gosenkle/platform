package club.isource.platform.filter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;

/** 
	* @author  高新国
	* @date 创建时间：2018年4月10日 上午11:31:36	
	* @version 1.0
**/
@Aspect
@Component
public class PaginationFilter {
	
	
	@Pointcut("execution(* club.isource.platform.dao.mapper.*.select*(..))")// the pointcut expression
	private void pagination() {}// the pointcut signature
	
	@Before("pagination()")
	public void startPagination(){
		// 1、获取分页请求参数
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
		String page = request.getParameter("page");
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isEmpty(page)) {
			page = "1";
		}
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = "10";
		}
		// 2、启动分页
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(pageSize));
	}
}
