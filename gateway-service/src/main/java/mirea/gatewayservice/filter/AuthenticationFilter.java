package mirea.gatewayservice.filter;

import mirea.gatewayservice.utils.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    //private final RestTemplate template;
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    //REST call to AUTH service
                    //template.getForObject("http://IDENTITY/validate?token=" + authHeader, String.class);

                    jwtUtil.validateToken(authHeader);

                    request = exchange
                            .getRequest()
                            .mutate()
                            .header("token", authHeader)
                            .build();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("unauthorized access");
                }
            }
            assert request != null;
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}
