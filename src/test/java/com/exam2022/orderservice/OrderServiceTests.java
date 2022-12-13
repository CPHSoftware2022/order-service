package com.exam2022.orderservice;

import com.exam2022.orderservice.OrderService;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OrderService.class)
class OrderServiceTests {
    private static final String REQ_PATH = "graphql/request";
    private static final String RES_PATH = "graphql/response";
    // Find a single customer graphql test.
    private static final String FIND_CUSTOMER_ORDER_PATH = format("%s/customerOrder.graphql", REQ_PATH);
    private static final String FIND_CUSTOMER_ORDER_RESPONSE_PATH = format("%s/customerOrder.json", RES_PATH);

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void findSingleCustomerOrder() {
        try {
            String findCustomerOrder = "customerOrder*";

            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(String.format(FIND_CUSTOMER_ORDER_PATH, findCustomerOrder));
            String expectedResponseBody = read(String.format(FIND_CUSTOMER_ORDER_RESPONSE_PATH, findCustomerOrder));

            assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String read(String location) {
        try {
            return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
