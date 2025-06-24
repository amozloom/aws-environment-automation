//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateInstanceRequestDTO {
    private String subnetId;
    private String instanceType;
    private String amiId;
    private int instanceCount;
}
