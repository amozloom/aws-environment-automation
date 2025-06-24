//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateVpcRequestDTO {
    private String cidrBlock;

    public CreateVpcRequestDTO(String cidrBlock) {
        this.cidrBlock = cidrBlock;
    }
}
