//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AWSOrchestrationService {
    private final VpcDTOService vpcService;
    private final InstanceDTOService instanceService;

    public AWSOrchestrationService(VpcDTOService vpcService, InstanceDTOService instanceService) {
        this.vpcService = vpcService;
        this.instanceService = instanceService;
    }

    public EnvironmentDTO createEnvironment(CreateEnvironmentRequestDTO request) {
        EnvironmentDTO environment = new EnvironmentDTO();
        VpcDTO vpc = vpcService.createVpc(new CreateVpcRequestDTO(request.getVpcCidrBlock()));
        environment.setVpcs(List.of(vpc));

        List<SubnetDTO> subnetResults = new ArrayList<>();
        for (CreateSubnetRequestDTO subnetRequest : request.getSubnets()) {
            SubnetDTO subnet = vpcService.createSubnet(vpc.getVpcId(), subnetRequest);
            subnetResults.add(subnet);
        }
        List<InstanceDTO> instanceResults = new ArrayList<>();
        for (CreateInstanceRequestDTO instanceRequest : request.getInstances()) {
            InstanceDTO instance = instanceService.createInstance(instanceRequest);
            instanceResults.add(instance);
        }
        environment.setSubnets(subnetResults);
        environment.setInstances(instanceResults);
        return environment;
    }
}

