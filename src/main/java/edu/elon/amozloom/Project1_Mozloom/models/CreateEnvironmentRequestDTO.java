//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateEnvironmentRequestDTO {
    private String vpcCidrBlock;
    private List<CreateSubnetRequestDTO> subnets;
    private List<CreateInstanceRequestDTO> instances;
}
