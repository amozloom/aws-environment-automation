//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.CreateVpcRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.SubnetDTO;
import edu.elon.amozloom.Project1_Mozloom.models.VpcDTO;
import edu.elon.amozloom.Project1_Mozloom.models.CreateSubnetRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.model.Subnet;
import software.amazon.awssdk.services.ec2.model.Vpc;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VpcDTOService {

    @Autowired
    private AWSNetworkService awsService;

    public List<VpcDTO> getAllVpcs() {
        return awsService.listAllVpcs().stream().map(vpc -> {
            VpcDTO dto = new VpcDTO();
            dto.setVpcId(vpc.vpcId());
            dto.setCidrBlock(vpc.cidrBlock());
            return dto;
        }).collect(Collectors.toList());
    }

    public VpcDTO getVpcById(String vpcId) {
        Vpc vpc = awsService.getVpcById(vpcId);
        VpcDTO dto = new VpcDTO();
        dto.setVpcId(vpc.vpcId());
        dto.setCidrBlock(vpc.cidrBlock());
        return dto;
    }

    public VpcDTO createVpc(CreateVpcRequestDTO dto) {
        Vpc vpc = awsService.createVpc(dto.getCidrBlock());
        VpcDTO result = new VpcDTO();
        result.setVpcId(vpc.vpcId());
        result.setCidrBlock(vpc.cidrBlock());
        return result;
    }

    public SubnetDTO createSubnet(String vpcId, CreateSubnetRequestDTO dto) {
        Subnet subnet = awsService.createSubnet(vpcId, dto);
        SubnetDTO subnetDTO = new SubnetDTO();
        subnetDTO.setSubnetId(subnet.subnetId());
        subnetDTO.setCidrBlock(subnet.cidrBlock());
        subnetDTO.setAvailabilityZone(subnet.availabilityZone());
        subnetDTO.setVisibility("UNKNOWN");
        return subnetDTO;
    }

    public List<SubnetDTO> getSubnetsForVpc(String vpcId) {
        List<Subnet> subnets = awsService.getSubnetsForVpc(vpcId);
        return subnets.stream().map(subnet -> {
            SubnetDTO dto = new SubnetDTO();
            dto.setSubnetId(subnet.subnetId());
            dto.setCidrBlock(subnet.cidrBlock());
            dto.setAvailabilityZone(subnet.availabilityZone());
            dto.setVisibility(awsService.getSubnetVisibility(subnet.subnetId()));
            return dto;
        }).toList();
    }

    public SubnetDTO getSubnetById(String subnetId) {
        Subnet subnet = awsService.getSubnetById(subnetId);
        SubnetDTO dto = new SubnetDTO();
        dto.setSubnetId(subnet.subnetId());
        dto.setCidrBlock(subnet.cidrBlock());
        dto.setAvailabilityZone(subnet.availabilityZone());
        dto.setVisibility(awsService.getSubnetVisibility(subnet.subnetId()));
        return dto;
    }

    public void deleteSubnet(String subnetId) {
        awsService.deleteSubnet(subnetId);
    }

}
