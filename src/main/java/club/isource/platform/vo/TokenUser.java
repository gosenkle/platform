package club.isource.platform.vo;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限用户对象用于token识别
 * @author gosenkle
 *
 */
@ApiModel(value="安全模块的用户模型")
public class TokenUser implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8528955224777418567L;
	@ApiModelProperty(value="权限认证的用户名")
	private String username;
	
	@ApiModelProperty(value="权限认证的密码")
    private String password;
	
    private Collection<? extends GrantedAuthority> authorities;
    
	public TokenUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
