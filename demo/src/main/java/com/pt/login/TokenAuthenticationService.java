package com.pt.login;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.google.gson.Gson;
import com.pt.utils.PropertiesUtil;
import com.pt.vo.ResultInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 432_000_000; // 5天,Java7开始在数字中使用下划线
	static final String SECRET = "123456"; // JWT密码
	static final String TOKEN_PREFIX = "Bearer"; // Token前缀
	static final String HEADER_STRING = "x-auth-token";// 存放Token的Header Key

	// JWT生成方法
	static void addAuthentication(HttpServletRequest request,HttpServletResponse response, Authentication auth) throws IOException {
		
		// 从properties文件读取JWT设置
		Properties properties = PropertiesUtil.read(request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes\\security.properties"));
		long expirationtime = Long.valueOf(properties.getProperty("EXPIRATIONTIME", "432000000"));
		String secret = properties.getProperty("SECRET", "123456");
		
		// 将权限拼成字符串
		Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>)auth.getAuthorities();
		String authsStr = "";
		if(auths!=null&&!auths.isEmpty()){
			int i = 0;
			for(GrantedAuthority grantedAuthority:auths) {
				authsStr = authsStr + grantedAuthority.getAuthority();
				i++;
				if(i<auths.size()) {
					authsStr = authsStr + ",";
				}
			}
		}
		// 生成JWT
		String JWT = Jwts.builder()
				// 保存权限（角色）
				.claim("authorities", authsStr)
				// 用户名写入标题
				.setSubject(auth.getName())
				// 有效期设置
				.setExpiration(new Date(System.currentTimeMillis() + expirationtime))
				// 签名设置
				.signWith(SignatureAlgorithm.HS512, secret).compact();

		// 将 JWT 写入 body
		try {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			ResultInfo<String> result = new ResultInfo<String>();
			result.setCode(0);
			result.setData(JWT);
			response.getOutputStream().println(new Gson().toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// JWT验证方法
	static Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		// 从properties文件读取JWT设置
		Properties properties = PropertiesUtil.read(request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes\\security.properties"));
		String secret = properties.getProperty("SECRET", "123456");
		String header = properties.getProperty("HEADER_STRING", "x-auth-token");
		String token_prefix = properties.getProperty("TOKEN_PREFIX", "Bearer");
				
		// 从Header中拿到token
		String token = request.getHeader(header);

		if (token != null) {
			// 解析 Token
			Claims claims = Jwts.parser()
					// 验签
					.setSigningKey(secret)
					// 去掉 Bearer
					.parseClaimsJws(token.replace(token_prefix, "")).getBody();

			// 拿用户名
			String user = claims.getSubject();
			if(new Date().after(claims.getExpiration())) {
				try {
					response.setContentType("application/json");
					response.setStatus(HttpServletResponse.SC_OK);
					ResultInfo<String> result = new ResultInfo<String>();
					result.setCode(ResultInfo.ERROR);
					result.setMessage("token失效");
					result.setUrl(request.getRequestURL().toString());
					response.getOutputStream().println(new Gson().toJson(result));
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// 得到 权限（角色）
			List<GrantedAuthority> authorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

			// 返回验证令牌
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
		}
		return null;
	}
}
