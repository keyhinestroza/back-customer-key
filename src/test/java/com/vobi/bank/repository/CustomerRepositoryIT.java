package com.vobi.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
	void debeValidarLasDependencias() {
		assertNotNull(customerRepository);
		assertNotNull(documentTypeRepository);
	}
	
	@Test
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
		customer.setToken("54845241654548421321sadasdsadkjsad5");
		
		//Act
		customer= customerRepository.save(customer);
		
		//Assert
		
		assertNotNull(customer,"El customer no debe ser null");
	}

}
