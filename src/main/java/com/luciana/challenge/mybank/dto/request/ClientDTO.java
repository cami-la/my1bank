package com.luciana.challenge.mybank.dto.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static lombok.Builder.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Default
    private Long id;

    @Default
    @NotEmpty
    @Size(min = 5, max = 100)
    private String firstName = "Daniel";

    @Default
    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName = "Morais";

    @Default
    @NotEmpty
    @CPF
    private String cpf = "999.555.222.11";;

    @Default
    @NotEmpty
    @Valid
    private List<PhoneDTO> phone = getPhone();

    public ClientDTO toClientDTO(){
        return new ClientDTO(
                id,
                firstName,
                lastName,
                cpf,
                phone);
    }

    public static String asJsonString(ClientDTO bookDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(bookDTO);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
