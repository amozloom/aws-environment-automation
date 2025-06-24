//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstanceDTO {
    private String instanceId;
    private String vpcId;
    private String subnetId;
    private String state;
    private String privateIPAddress;
    private String privateDNSName;
    private String publicIPAddress;
    private String publicDNSName;
    private String platform;
    private String instanceType;
    private String launchTime;
}
