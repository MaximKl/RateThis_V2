package com.ratethis.apigateway;

import com.ratethis.apigateway.exceptions.MyErrorDecoder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableFeignClients
public class ApiGatewayApplication extends CorsConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route
                        .path("/RateThis/public/**")
                        .uri("lb://public"))
                .route(route -> route
                        .path("/RateThis/**")
                        .uri("lb://profile"))
                .route(route -> route
                        .path("/RateThis-product/**")
                        .uri("lb://product"))
                .route(route -> route
                        .path("/RateThis-review/**")
                        .uri("lb://review"))
                .route(route -> route
                        .path("/RateThis-chat/**")
                        .uri("lb://chat"))
//				.route(route-> route
//						.path("/RateThis/**")
//						.uri("lb://film"))
//				.route(route-> route
//						.path("/RateThis/**")
//						.uri("lb://game"))
//				.route(p->p.path("/currency-conversion/**")
//						.uri("lb://currency-conversion"))
//				.route(p->p.path("/currency-conversion-feign/**")
//						.uri("lb://currency-conversion"))
//				.route(p->p.path("/currency-conversion-test/**")
//						.filters(f->f.rewritePath("/currency-conversion-test/(?<segment>.*)"
//								,"/currency-conversion-feign/${segment}"))
//						.uri("lb://currency-conversion"))

//
//				.route(r -> r.path("/consumer/**")
//						.uri("http://localhost:8082/")
//						.id("consumerModule"))
                .build();
    }

    @Bean
    public MyErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        corsConfiguration.addAllowedHeader("origin");
        corsConfiguration.addAllowedHeader("content-type");
        corsConfiguration.addAllowedHeader("accept");
        corsConfiguration.addAllowedHeader("authorization");
        corsConfiguration.addAllowedHeader("cookie");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }




//	@Bean
//	public RouterFunction<?> necessaryRouter(){
//		return route(GET("/exit"),
//				request -> ServerResponse.ok().)
//	}

}
