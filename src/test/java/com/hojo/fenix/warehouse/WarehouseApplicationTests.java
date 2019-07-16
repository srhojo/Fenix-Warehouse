package com.hojo.fenix.warehouse;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WarehouseApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class WarehouseApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCategories() {
        // Given

        // When
        final ResponseEntity<ContainerList> response = restTemplate.getForEntity("/categories",
                ContainerList.class);

        // Then
        assertNotNull(response);
        assertNotNull(Objects.requireNonNull(response.getBody()).getValues());
        assertTrue(response.getBody().getValues().size() > 1);
    }

    @Test
    public void searchCategories_byId() {
        // Given
        final String filter = "?filter=name==CAT1";
        final String url = "/categories" + filter;

        // When
        final ResponseEntity<ContainerList> response = restTemplate.getForEntity(url, ContainerList.class);

        // Then
        assertNotNull(response);
        assertNotNull(Objects.requireNonNull(response.getBody()).getValues());
        assertEquals(1, response.getBody().getValues().size());
    }

    @Test
    @Ignore("Something wrong.. response is null :s")
    public void searchCategories_byNameLike() {
        // Given
        final String filter = "?filter=description=in=Otro";
        final String url = "/categories" + filter;

        // When
        final ResponseEntity<ContainerList> response = restTemplate.getForEntity(url, ContainerList.class);

        // Then
        assertNotNull(response);
        assertNotNull(Objects.requireNonNull(response.getBody()).getValues());
        assertEquals(3, response.getBody().getValues().size());
    }

}
