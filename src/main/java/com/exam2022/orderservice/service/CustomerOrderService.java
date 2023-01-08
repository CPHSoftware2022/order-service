package com.exam2022.orderservice.service;

import com.exam2022.orderservice.kafka.ProducerService;
import com.exam2022.orderservice.model.CustomerOrder;
import com.exam2022.orderservice.model.EventModel;
import com.exam2022.orderservice.repository.CustomerOrderRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ProducerService producerService;

    public List<CustomerOrder> findAllCustomerOrders() {
        try {
            List<CustomerOrder> allOrders = customerOrderRepository.findAll();

            ResponseEntity response = new ResponseEntity<>(
                    allOrders,
                    HttpStatus.OK);

            String resultString = "CustomerOrderList{size="+allOrders.size()+"}";;
            EventModel eventModel = new EventModel("GET", response.getStatusCode(), resultString);
            producerService.sendMessage(eventModel.toString());

            return allOrders;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        // just return an empty list if nothing is found.
        return new ArrayList<>();
    }

    public Optional<CustomerOrder> findOneCustomerOrder(Long id) {
        try {
            Optional<CustomerOrder> customerOrder = customerOrderRepository.findById(id);

            ResponseEntity response = new ResponseEntity<>(
                    customerOrder,HttpStatus.OK);

            EventModel eventModel = new EventModel("GET", response.getStatusCode(), customerOrder.toString());
            producerService.sendMessage(eventModel.toString());

            return customerOrder;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return Optional.of(new CustomerOrder());
    }

    public CustomerOrder addCustomerOrder(
            String order_start,
            String order_end,
            Float total_price,
            Boolean accepted,
            String canceled_reason,
            Boolean delivered,
            Long customer_id,
            Long restaurant_id,
            Long feedback_id,
            Long employee_id
    ) {
        try {
            CustomerOrder customerOrder = customerOrderRepository.save(CustomerOrder.builder()
                    .order_start(new SimpleDateFormat("yyyy/MM/dd").parse(order_start))
                    .order_end(new SimpleDateFormat("yyyy/MM/dd").parse(order_end))
                    .total_price(total_price)
                    .accepted(accepted)
                    .canceled_reason(canceled_reason)
                    .delivered(delivered)
                    .customer_id(customer_id)
                    .restaurant_id(restaurant_id)
                    .feedback_id(feedback_id)
                    .employee_id(employee_id).build());

            ResponseEntity response = new ResponseEntity<>(
                    customerOrder,HttpStatus.OK);

            EventModel eventModel = new EventModel("POST", response.getStatusCode(), customerOrder.toString());
            producerService.sendMessage(eventModel.toString());

            return customerOrder;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new CustomerOrder();
    }

    public CustomerOrder updateCustomerOrder(
            Long id,
            String order_start,
            String order_end,
            Float total_price,
            Boolean accepted,
            String canceled_reason,
            Boolean delivered,
            Long customer_id,
            Long restaurant_id,
            Long feedback_id,
            Long employee_id
    ) {
        try {
            Optional<CustomerOrder> existing = customerOrderRepository.findById(id);
            if(existing.isPresent()) {
                CustomerOrder updated = CustomerOrder.builder()
                        .id(id)
                        .order_start(new SimpleDateFormat("dd/MM/yyyy").parse(order_start))
                        .order_end(new SimpleDateFormat("dd/MM/yyyy").parse(order_end))
                        .total_price(total_price)
                        .accepted(accepted)
                        .canceled_reason(canceled_reason)
                        .delivered(delivered)
                        .customer_id(customer_id)
                        .restaurant_id(restaurant_id)
                        .feedback_id(feedback_id)
                        .employee_id(employee_id).build();

                ResponseEntity response = new ResponseEntity<>(
                        updated,HttpStatus.OK);

                EventModel eventModel = new EventModel("POST", response.getStatusCode(), updated.toString());
                producerService.sendMessage(eventModel.toString());

                return customerOrderRepository.saveAndFlush(updated);
            } else {
                System.out.println("No existing customer found with the provided id.");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new CustomerOrder();
    }

    public CustomerOrder deleteCustomerOrder(Long id) {
        try {
            customerOrderRepository.deleteById(id);
        }catch (Exception ex) {
            System.out.println(ex);
        }
        return new CustomerOrder();
    }
}
