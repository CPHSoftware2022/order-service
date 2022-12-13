package com.exam2022.orderservice;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OrderService.class)
public class CreateCustomerOrderTests {

    private static final String REQ_PATH = "graphql/request";
    private static final String RES_PATH = "graphql/response";
    // Create customer order graphql test.
    private static final String CREATE_CUSTOMER_ORDER_PATH = format("%s/createCustomerOrder.graphql", REQ_PATH);
    private static final String CREATE_CUSTOMER_ORDER_RESPONSE_PATH = format("%s/createCustomerOrder.json", RES_PATH);

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void returnCreatingCourier() throws IOException, JSONException {

        var createCourier = "create-courier.json*";

        GraphQLResponse graphQLResponse = graphQLTestTemplate
                .postForResource(format(CREATE_CUSTOMER_ORDER_PATH, createCourier));

        var expectedResponseBody = read(format(CREATE_CUSTOMER_ORDER_RESPONSE_PATH, createCourier));
        System.out.println(expectedResponseBody);

        assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


        assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
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
