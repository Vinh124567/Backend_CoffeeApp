package com.example.CoffeeApp.service.implement;
import com.example.CoffeeApp.DTO.AuthenticationDTO.AuthenticationRequestDTO;
import com.example.CoffeeApp.DTO.AuthenticationDTO.IntrospectRequest;
import com.example.CoffeeApp.DTO.AuthenticationDTO.IntrospectResponse;
import com.example.CoffeeApp.Entity.Admin;
import com.example.CoffeeApp.Entity.Role;
import com.example.CoffeeApp.Exception.AppException;
import com.example.CoffeeApp.Exception.ErrorCode;
import com.example.CoffeeApp.repository.AdminRepository;
import com.example.CoffeeApp.response.AuthenticationResponse;
import com.example.CoffeeApp.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AdminRepository adminRepository;

   @NonFinal
   protected static  final String SIGNER_KEY="9o75HYyiqLhhK91+pvVoDsJ3p+oRd6n3iapvj9Hx8uwvcqWIEVDcAgNnz7gG0rTX";
    public AuthenticationResponse Authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        var admin = adminRepository.findByUsername(authenticationRequestDTO.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequestDTO.getPassword(), admin.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(admin);
        Optional<Admin> a =adminRepository.findByUsername("admin");
        System.out.println("Authenticated Admin: " + a);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(Admin admin){
        String issuer = System.getProperty("spring.application.name");

        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(admin.getUsername())
                .issuer(issuer)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(admin))
                .build();
        Payload payload =new Payload(jwtClaimsSet.toJSONObject());


        JWSObject jwsObject=new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    private String buildScope(Admin admin) {
        StringJoiner joiner = new StringJoiner("");
        System.out.println("Admin roles: " + admin.getRoles()); // Thêm log

        for (Role role : admin.getRoles()) { // Giả sử admin có phương thức getRoles()
            joiner.add(role.getName()); // Thêm tên của vai trò vào StringJoiner
        }

        return joiner.toString();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token=request.getToken();
        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT =SignedJWT.parse(token);

        Date exprityTime=signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified=signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && exprityTime.after(new Date()))
                .build();
    }
}
