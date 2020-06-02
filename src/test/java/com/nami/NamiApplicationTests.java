package com.nami;

import com.nami.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NamiApplicationTests {

	private static Validator validator;

	@BeforeAll
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void whenNotBlankName_thenNoConstraintViolations() {
		Product product = new Product(UUID.randomUUID(), "TestProduct");
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isEqualTo(0);
	}

	@Test
	public void whenBlankName_thenOneConstraintViolation() {
		Product product = new Product(UUID.randomUUID(), " ");
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	public void whenEmptyName_thenOneConstraintViolation() {
		Product product = new Product(UUID.randomUUID(), "");
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	public void whenNullName_thenOneConstraintViolation() {
		Product product = new Product(UUID.randomUUID(), null);
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

		assertThat(violations.size()).isGreaterThanOrEqualTo(1);
	}
}
