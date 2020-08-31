package com.rebwon.pagingoptimizing;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

  @Autowired
  private CustomerJdbcRepository repository;

  @BeforeEach
  void setUp() {
    List<Customer> customers = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      customers.add(new Customer("testName" + i));
    }
    repository.saveAll(customers);
  }

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("totalElements를 입력하지 않을 경우")
  void findAllCustomers_No_Param_TotalElements() throws Exception {
    mockMvc.perform(get("/customers"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("totalElements를 입력할 경우")
  void findAllCustomers_Param_TotalElements() throws Exception {
    mockMvc.perform(get("/customers")
      .param("total_elements", "100")
    )
        .andDo(print())
        .andExpect(status().isOk());
  }
}