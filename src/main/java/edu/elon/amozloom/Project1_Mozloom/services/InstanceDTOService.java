//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.CreateInstanceRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.InstanceDTO;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.model.Instance;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceDTOService {

    private final AWSInstanceService awsService;

    public InstanceDTOService(AWSInstanceService awsService) {
        this.awsService = awsService;
    }

    public List<InstanceDTO> getAllInstances() {
        return awsService.getAllInstances().stream().map(instance -> {
            InstanceDTO dto = new InstanceDTO();
            dto.setInstanceId(instance.instanceId());
            dto.setVpcId(instance.vpcId());
            dto.setSubnetId(instance.subnetId());
            dto.setState(String.valueOf(instance.state().name()));
            dto.setPrivateIPAddress(instance.privateIpAddress());
            dto.setPrivateDNSName(instance.privateDnsName());
            dto.setPublicIPAddress(instance.publicIpAddress());
            dto.setPublicDNSName(instance.publicDnsName());
            dto.setPlatform(instance.platformAsString());
            dto.setInstanceType(instance.instanceTypeAsString());
            dto.setLaunchTime(instance.launchTime().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    public InstanceDTO getInstanceById(String instanceId) {
        Instance instance = awsService.getInstanceById(instanceId);
        InstanceDTO dto = new InstanceDTO();
        dto.setInstanceId(instance.instanceId());
        dto.setVpcId(instance.vpcId());
        dto.setSubnetId(instance.subnetId());
        dto.setState(String.valueOf(instance.state().name()));
        dto.setPrivateIPAddress(instance.privateIpAddress());
        dto.setPrivateDNSName(instance.privateDnsName());
        dto.setPublicIPAddress(instance.publicIpAddress());
        dto.setPublicDNSName(instance.publicDnsName());
        dto.setPlatform(instance.platformAsString());
        dto.setInstanceType(instance.instanceTypeAsString());
        dto.setLaunchTime(instance.launchTime().toString());
        return dto;
    }

    public InstanceDTO createInstance(CreateInstanceRequestDTO dto) {
        Instance instance = awsService.createInstance(dto);
        InstanceDTO result = new InstanceDTO();
        result.setInstanceId(instance.instanceId());
        result.setVpcId(instance.vpcId());
        result.setSubnetId(instance.subnetId());
        result.setState(String.valueOf(instance.state().name()));
        result.setPrivateIPAddress(instance.privateIpAddress());
        result.setPrivateDNSName(instance.privateDnsName());
        result.setPublicIPAddress(instance.publicIpAddress());
        result.setPublicDNSName(instance.publicDnsName());
        result.setPlatform(instance.platformAsString());
        result.setInstanceType(instance.instanceTypeAsString());
        result.setLaunchTime(instance.launchTime().toString());
        return result;
    }

    public InstanceDTO startInstance(String instanceId) {
        Instance instance = awsService.startInstance(instanceId);
        InstanceDTO dto = new InstanceDTO();
        dto.setInstanceId(instance.instanceId());
        dto.setVpcId(instance.vpcId());
        dto.setSubnetId(instance.subnetId());
        dto.setState(String.valueOf(instance.state().name()));
        dto.setPrivateIPAddress(instance.privateIpAddress());
        dto.setPrivateDNSName(instance.privateDnsName());
        dto.setPublicIPAddress(instance.publicIpAddress());
        dto.setPublicDNSName(instance.publicDnsName());
        dto.setPlatform(instance.platformAsString());
        dto.setInstanceType(instance.instanceTypeAsString());
        dto.setLaunchTime(instance.launchTime().toString());
        return dto;
    }

    public InstanceDTO stopInstance(String instanceId) {
        Instance instance = awsService.stopInstance(instanceId);
        InstanceDTO dto = new InstanceDTO();
        dto.setInstanceId(instance.instanceId());
        dto.setVpcId(instance.vpcId());
        dto.setSubnetId(instance.subnetId());
        dto.setState(String.valueOf(instance.state().name()));
        dto.setPrivateIPAddress(instance.privateIpAddress());
        dto.setPrivateDNSName(instance.privateDnsName());
        dto.setPublicIPAddress(instance.publicIpAddress());
        dto.setPublicDNSName(instance.publicDnsName());
        dto.setPlatform(instance.platformAsString());
        dto.setInstanceType(instance.instanceTypeAsString());
        dto.setLaunchTime(instance.launchTime().toString());
        return dto;
    }

    public InstanceDTO terminateInstance(String instanceId) {
        Instance instance = awsService.terminateInstance(instanceId);
        InstanceDTO dto = new InstanceDTO();
        dto.setInstanceId(instance.instanceId());
        dto.setVpcId(instance.vpcId());
        dto.setSubnetId(instance.subnetId());
        dto.setState(String.valueOf(instance.state().name()));
        dto.setPrivateIPAddress(instance.privateIpAddress());
        dto.setPrivateDNSName(instance.privateDnsName());
        dto.setPublicIPAddress(instance.publicIpAddress());
        dto.setPublicDNSName(instance.publicDnsName());
        dto.setPlatform(instance.platformAsString());
        dto.setInstanceType(instance.instanceTypeAsString());
        dto.setLaunchTime(instance.launchTime().toString());
        return dto;
    }

}
