package br.com.ceub.inventoryManagement.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductCreateRequest {
    @NotBlank(message = "Nome do produto não pode ser vazio.")
    @Size(max = 100, message = "O nome do produto deve ter no máximo 100 caracteres.")
    private String productName;

    @Positive(message = "Informe um preço unitário válido!")
    @NotNull(message = "O preço unitário é obrigatório!")
    private Double unitPrice;
}
