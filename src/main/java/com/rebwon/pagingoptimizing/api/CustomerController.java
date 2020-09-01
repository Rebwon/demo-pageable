package com.rebwon.pagingoptimizing.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.rebwon.pagingoptimizing.domain.CustomerService;
import com.rebwon.pagingoptimizing.util.CustomPagedResourceAssembler;
import com.rebwon.pagingoptimizing.util.PagedModelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;
  private final CustomPagedResourceAssembler<CustomerDto> assembler;

  @GetMapping("/customers")
  public ResponseEntity<Page<CustomerDto>> getCustomer(Pageable pageable,
      @RequestParam(required = false, name = "total_elements") Integer totalElements) {
    Page<CustomerDto> customer = customerService.getCustomer(pageable, totalElements);
    return ResponseEntity.ok(customer);
  }

  @GetMapping("/v1/customers")
  public ResponseEntity<PagedModel<EntityModel<CustomerDto>>> getCustomer(PageableDto dto) {
    Page<CustomerDto> customer = customerService.getCustomer(dto);
    PagedModel<EntityModel<CustomerDto>> entityModels = PagedModelUtils.getEntityModels(assembler, customer,
        linkTo(methodOn(this.getClass()).getCustomer(null)), CustomerDto::getCustomerId);
    return ResponseEntity.ok(entityModels);
  }
}
