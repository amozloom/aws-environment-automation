//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.EnvironmentDTO;
import edu.elon.amozloom.Project1_Mozloom.models.InstanceDTO;
import edu.elon.amozloom.Project1_Mozloom.models.SubnetDTO;
import edu.elon.amozloom.Project1_Mozloom.models.VpcDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnvironmentDTOService {

    private final VpcDTOService vpcService;
    private final InstanceDTOService instanceService;

    public EnvironmentDTOService(VpcDTOService vpcService, InstanceDTOService instanceService) {
        this.vpcService = vpcService;
        this.instanceService = instanceService;
    }

    public EnvironmentDTO getEnvironment() {
        List<VpcDTO> vpcs = vpcService.getAllVpcs();
        List<SubnetDTO> allSubnets = new ArrayList<>();
        for (VpcDTO vpc : vpcs) {
            allSubnets.addAll(vpcService.getSubnetsForVpc(vpc.getVpcId()));
        }
        List<InstanceDTO> instances = instanceService.getAllInstances();
        EnvironmentDTO env = new EnvironmentDTO();
        env.setVpcs(vpcs);
        env.setSubnets(allSubnets);
        env.setInstances(instances);
        return env;
    }
}
