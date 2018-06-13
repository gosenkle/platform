package club.isource.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import club.isource.platform.dao.mapper.UserMapper;
import club.isource.platform.dao.po.User;
import club.isource.platform.dao.po.UserExample;
import club.isource.platform.vo.TokenUser;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = userMapper.selectByExample(example );
		if (users == null || users.size() < 1 || users.size() > 1) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}else {
			User user = users.get(0);
			return new TokenUser(user.getUsername(), user.getPassword(), null);
		}
	}

}
