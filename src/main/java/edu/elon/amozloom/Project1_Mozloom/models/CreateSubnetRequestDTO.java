//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSubnetRequestDTO {
    private String cidrBlock;
    private String availabilityZone;
    private String visibility;
}
