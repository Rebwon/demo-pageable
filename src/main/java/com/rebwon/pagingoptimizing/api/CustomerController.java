package com.rebwon.pagingoptimizing.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.rebwon.pagingoptimizing.domain.CustomerService;
import com.rebwon.pagingoptimizing.util.CustomPagedResourceAssembler;
import com.rebwon.pagingoptimizing.util.PagedModelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;
  private final CustomPagedResourceAssembler<CustomerDto> assembler;

  @GetMapping("/customers/{id}")
  public CustomerDto getOne(@PathVariable Long id) {
    log.info("Controller Place");
    return customerService.getCustomer(id);
  }

  @GetMapping("/customers")
  public ResponseEntity<Page<CustomerDto>> findAll(Pageable pageable,
      @RequestParam(required = false, name = "total_elements") Integer totalElements) {
    Page<CustomerDto> customer = customerService.findAll(pageable, totalElements);
    return ResponseEntity.ok(customer);
  }

  @GetMapping("/v1/customers")
  public ResponseEntity<PagedModel<EntityModel<CustomerDto>>> findAll(PageableDto dto) {
    Page<CustomerDto> customer = customerService.findAll(dto);
    PagedModel<EntityModel<CustomerDto>> entityModels = PagedModelUtils.getEntityModels(assembler, customer,
        linkTo(methodOn(this.getClass()).findAll(null)), CustomerDto::getCustomerId);
    return ResponseEntity.ok(entityModels);
  }
}
