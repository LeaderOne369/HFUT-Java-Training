package com.example.pms1.security;

/**
 * @author loself
 * @date 2024-07-02 10:28
 */
import com.example.pms1.service.impl.CaptchaUserDetailsService;
import com.example.pms1.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CaptchaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CaptchaUserDetailsService userDetailsService;

    @Autowired
    private CaptchaUtil captchaService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        CaptchaAuthenticationToken authToken = (CaptchaAuthenticationToken) authentication;
        String principal = (String) authToken.getPrincipal();
        String captcha = (String) authToken.getCredentials();

        if (!captchaService.checkCaptcha(principal, captcha)) {
            throw new BadCredentialsException("Invalid captcha");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);

        CaptchaAuthenticationToken authenticatedToken = new CaptchaAuthenticationToken(
                userDetails, userDetails.getAuthorities());
        authenticatedToken.setDetails(authentication.getDetails());

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (CaptchaAuthenticationToken.class.isAssignableFrom(authentication));
    }
}