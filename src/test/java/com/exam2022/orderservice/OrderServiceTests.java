package com.exam2022.orderservice;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OrderService.class)
class OrderServiceTests {
    private static final String REQUEST = "graphql/request/customerOrder.graphql";
    private static final String RESPONSE = "graphql/response/customerOrder.json";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void findSingleCustomerOrder() {
        try {
            String findCustomerOrder = "customerOrder.json";

            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(String.format(REQUEST, findCustomerOrder));
            String expectedResponseBody = read(String.format(RESPONSE, findCustomerOrder));

            assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
