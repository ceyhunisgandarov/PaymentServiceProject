package com.payment.service.security.service;

import com.payment.service.exception.ExceptionConstant;
import com.payment.service.exception.MainException;
import com.payment.service.response.Response;
import com.payment.service.response.ResponseStatus;
import com.payment.service.security.UserRepository;
import com.payment.service.security.model.ReqAuth;
import com.payment.service.security.model.RespAuth;
import com.payment.service.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public Response<RespAuth> login(ReqAuth request) {
        Response<RespAuth> response = new Response<>();

        try {
            String userName = request.getUsername();
            String password = request.getPassword();
            System.out.println(userName + ", " + password);
            if (userName==null && password==null) {
                throw new MainException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid data");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            User user = repository.findUserByUsername(userName);
            System.out.println(user.getUsername());
            if (user==null) {
                throw new MainException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            var jwtToken = jwtService.generateToken(user);
            System.out.println(jwtToken);
            RespAuth respAuth = RespAuth.builder()
                    .token(jwtToken)
                    .build();
            response.setT(respAuth);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (MainException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.getMessage();
        }

        return response;
    }

}
