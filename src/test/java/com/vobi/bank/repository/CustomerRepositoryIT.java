package com.vobi.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.domain.DocumentType;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryIT {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(customerRepository);
		assertNotNull(documentTypeRepository);
	}
	
	@Test
	@Order(2)
	void debeCrearUnCustomer() {
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
		customer= customerRepository.save(customer);
		
		//Assert
		
		assertNotNull(customer,"El customer no debe ser null");
	}
	
	@Test
	@Order(3)
	void debeModificarUnCustomer() {
		//Arrange
		Integer idCustomer=148365541;
		
		Customer customer=customerRepository.findById(idCustomer).get();		
		customer.setEnable("N");
		
		//Act
		customer= customerRepository.save(customer);
		
		//Assert
		
		assertNotNull(customer,"El customer no debe ser null");
	}


	@Test
	@Order(3)
	void debeBorrarUnCustomer() {
		//Arrange
		Integer idCustomer=148365541;
		Customer customer=null;
		Optional<Customer> customerOptional=null;
		
		customer=customerRepository.findById(idCustomer).get();
		
		//Act
		customerRepository.delete(customer);
		customerOptional= customerRepository.findById(idCustomer);
		
		//Assert
		
		assertFalse(customerOptional.isPresent(),"No se pudo eliminar el customer");
	}
}
