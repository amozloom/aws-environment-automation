//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EnvironmentDTO {
    private List<VpcDTO> vpcs;
    private List<SubnetDTO> subnets;
    private List<InstanceDTO> instances;
}
