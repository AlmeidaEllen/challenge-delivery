package br.com.ellenmiranda.delivery_registration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateDeliverySuccess() throws Exception {
        String deliveryJson = "{\"qtdPacotes\":5,\"dataLimiteEntrega\":\"2024-11-28\",\"nomeCliente\":\"Bianca Oliveira\",\"cpfCliente\":\"12345678901\",\"endereco\":{\"cep\":\"12345678\",\"uf\":\"SP\",\"cidade\":\"São Paulo\",\"bairro\":\"Centro\",\"rua\":\"Rua C\",\"numero\":\"225\"}}";

        mockMvc.perform(post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deliveryJson))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.nomeCliente").value("Bianca Oliveira")); 
    }

    @Test
    public void testCreateDeliveryFailure() throws Exception {
        String deliveryJson = "{\"qtdPacotes\":5,\"dataLimiteEntrega\":\"2024-11-28\",\"nomeCliente\":\"\",\"cpfCliente\":\"12345678901\",\"endereco\":{\"cep\":\"12345678\",\"uf\":\"SP\",\"cidade\":\"São Paulo\",\"bairro\":\"Centro\",\"rua\":\"Rua C\",\"numero\":\"225\"}}";

        mockMvc.perform(post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deliveryJson))
                .andExpect(status().isBadRequest()); 
    }
}
