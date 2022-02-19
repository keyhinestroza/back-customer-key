package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.domain.DocumentType;
import com.vobi.bank.repository.DocumentTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class CustomerServiceIT {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(customerService);
		assertNotNull(documentTypeRepository);
	}
	
	@Test
	@Order(2)
	void debeCrearUnCustomer() throws Exception {
		//Arrange
		Integer idDocumentType=1;
		Integer idCustomer=148365541;
		
		Customer customer=null;
		DocumentType documentType=documentTypeRepository.findById(idDocumentType).get();
		
		customer= new Customer();
		customer.setAddress("Direccion");
		customer.setCustId(idCustomer);
		customer.setDocumentType(documentType);
		customer.setEmail("mail@mail.com");
		customer.setEnable("Y");
		customer.setName("El nombre");
		customer.setPhone("55555555555");
		customer.setToken("54845241654548421321sadasdsadkjsad5");
		
		//Act
		customer= customerService.save(customer);
		
		//Assert
		
		assertNotNull(customer,"El customer no debe ser null");
	}
	
	@Test
	@Order(3)
	void debeModificarUnCustomer() throws Exception {
		//Arrange
		Integer idCustomer=148365541;
		
		Customer customer=customerService.findById(idCustomer).get();		
		customer.setEnable("N");
		
		//Act
		customer= customerService.update(customer);
		
		//Assert
		
		assertNotNull(customer,"El customer no debe ser null");
	}


	@Test
	@Order(4)
	void debeBorrarUnCustomer() throws Exception {
		//Arrange
		Integer idCustomer=148365541;
		Customer customer=null;
		Optional<Customer> customerOptional=null;
		
		assertTrue(customerService.findById(idCustomer).isPresent(),"Customer para eliminar no encontrado");
		
		customer=customerService.findById(idCustomer).get();
		
		//Act
		customerService.delete(customer);
		customerOptional= customerService.findById(idCustomer);
		
		//Assert
		
		assertFalse(customerOptional.isPresent(),"No se pudo eliminar el customer");
	}
	
	@Test
	@Order(5)
	void debeConsultarTodosLosCustomers() {
		//Arrange
		List<Customer> customers=null;
		
		//Act
		customers=customerService.findAll();
		customers.forEach(customer->log.info(customer.getName()));
		//Assert
		
		assertFalse(customers.isEmpty(),"No se consulto Customers");
	}
}
