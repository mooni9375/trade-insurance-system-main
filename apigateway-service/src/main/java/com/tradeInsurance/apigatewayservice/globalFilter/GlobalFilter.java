package com.tradeInsurance.apigatewayservice.globalFilter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    /**
     * GatewayFilterFactory Interface 구현
     *  Gateway 요청(Request) 및 응답(Response)을 처리하기 위한 사전(pre) 및 사후(post) 로직을 정의.
     *
     * Config
     *  application.yml 파일의 설정을 읽어들여 파라미터로 전달
     */
    @Override
    public GatewayFilter apply(Config config) {

        // GatewayFilter 구현 클래스 없이 람다로 간결화
        /**
         * ServerWebExchange
         *  클라이언트와 서버 간 데이터 교환의 중심 역할
         *  요청과 응답을 GatewayFilterChain에 전달하여 각 필터가 처리할 수 있도록 함
         *  요청/응답과 관련된 임시 데이터를 저장하고 공유할 수 있는 컨텍스트 역할
         */
        //  :  .
        return ((exchange, chain) -> {
            ServerHttpRequest  request  = exchange.getRequest(); // 현재 요청과 응답
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Filter baseMessage: {}", config.getBaseMessage());

            // 전처리 작업
            if (config.isPreLogger()) {
                log.info("Global Filter Start: request id -> {}", request.getId());
            }

            // 최상위 람다의 반환값
            // 체인 필터 실행 : 현재 필터 이후 다음 필터(후처리 작업)를 비동기(Runnable)로 실행
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 후처리 작업
                if (config.isPostLogger()) {
                    log.info("Global Filter End: response code id -> {}", response.getStatusCode());
                }
            }));
        });
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
