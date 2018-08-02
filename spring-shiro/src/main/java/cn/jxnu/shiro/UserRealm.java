package cn.jxnu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jxnu.domain.User;
import cn.jxnu.mapper.UserMapper;
import cn.jxnu.service.UserService;

/**
 * 自定义Realm
 * @author hewen
 *
 */
public class UserRealm extends AuthorizingRealm{
	/**
	 * 注入查询数据库的业务
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 指定授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//从数据库中获得授权字符串
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		User dbUser = userService.fingById(user.getId());	
		info.addStringPermission(dbUser.getPerms());
		
		return info;
	}
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		
		//编写shiro判断逻辑，判断用户名和密码
		//判断用户名
		UsernamePasswordToken mytoken = (UsernamePasswordToken)token;
		
		User user = userService.findByUsername(mytoken.getUsername());
		
		if(user == null) {
			//用户名不存在
			return null;//shiro底层会抛出UnkonwAccountException	
		}
		//判断密码,如果自定义了密码比较器，就要把密码比较器注入到此realm中
		return new SimpleAuthenticationInfo(user, user.getPassword(),"");
	}

}
