package repositoryTest;

import com.springCustomer.cust.entity.Customer;
import com.springCustomer.cust.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer() {
        // Arrange: setting up the data required for the test case
        Customer customer = new Customer();
        customer.setName("rohan");
        customer.setEmail("rohan@gmail.com");
        customer.setPhoneno("9324572012");

        // Act: calling the method/unit under test
        Customer savedCustomer = customerRepository.save(customer);

        // Assert: verifying whether the expected result is correct or not
        assertNotNull(savedCustomer);
        assertThat(savedCustomer.getCustid()).isNotNull();
    }
}
