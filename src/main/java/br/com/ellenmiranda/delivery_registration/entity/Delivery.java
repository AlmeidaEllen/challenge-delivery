package br.com.ellenmiranda.delivery_registration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qtdPacotes;
    private LocalDate dataLimiteEntrega;
    private String nomeCliente;
    private String cpfCliente;

    @Embedded
    private Endereco endereco;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Endereco {
        public Endereco(String string, String string2, String string3, String string4, String string5, String string6,
                Object object) {
        }
        private String cep;
        private String uf;
        private String cidade;
        private String bairro;
        private String rua;
        private int numero;
        private String complemento; 
    }
}
