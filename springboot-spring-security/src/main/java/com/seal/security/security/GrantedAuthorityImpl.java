package com.seal.security.security;
import org.springframework.security.core.GrantedAuthority;


/**
 * 权限封装
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:32
 **/
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}