//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.CreateSubnetRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;

@Service
public class AWSNetworkService {

    private final Ec2Client ec2;

    @Autowired
    public AWSNetworkService(Ec2Client ec2) {
        this.ec2 = ec2;
    }

    public List<Vpc> listAllVpcs() {
        DescribeVpcsResponse response = ec2.describeVpcs();
        return response.vpcs();
    }

    public Vpc getVpcById(String vpcId) {
        DescribeVpcsRequest request = DescribeVpcsRequest.builder()
                .vpcIds(vpcId)
                .build();
        DescribeVpcsResponse response = ec2.describeVpcs(request);
        if (response.vpcs().isEmpty()) {
            throw new RuntimeException("VPC not found with ID: " + vpcId);
        }
        return response.vpcs().get(0);
    }

    public Vpc createVpc(String cidrBlock) {
        CreateVpcRequest request = CreateVpcRequest.builder()
                .cidrBlock(cidrBlock)
                .build();
        CreateVpcResponse response = ec2.createVpc(request);
        String vpcId = response.vpc().vpcId();
        ec2.waiter().waitUntilVpcAvailable(DescribeVpcsRequest.builder().vpcIds(vpcId).build());
        return ec2.describeVpcs(DescribeVpcsRequest.builder().vpcIds(vpcId).build()).vpcs().get(0);
    }

    public Subnet createSubnet(String vpcId, CreateSubnetRequestDTO dto) {
        CreateSubnetRequest request = CreateSubnetRequest.builder()
                .vpcId(vpcId)
                .cidrBlock(dto.getCidrBlock())
                .availabilityZone(dto.getAvailabilityZone())
                .build();
        CreateSubnetResponse response = ec2.createSubnet(request);
        String subnetId = response.subnet().subnetId();
        ec2.waiter().waitUntilSubnetAvailable(
                DescribeSubnetsRequest.builder().subnetIds(subnetId).build()
        );
        return ec2.describeSubnets(
                DescribeSubnetsRequest.builder().subnetIds(subnetId).build()
        ).subnets().get(0);
    }

    public List<Subnet> getSubnetsForVpc(String vpcId) {
        DescribeSubnetsResponse response = ec2.describeSubnets();
        return response.subnets().stream()
                .filter(subnet -> subnet.vpcId().equals(vpcId))
                .toList();
    }

    public String getSubnetVisibility(String subnetId) {
        DescribeRouteTablesResponse routeTables = ec2.describeRouteTables();
        for (RouteTable table : routeTables.routeTables()) {
            boolean associated = table.associations().stream()
                    .anyMatch(assoc -> assoc.subnetId() != null && assoc.subnetId().equals(subnetId));
            if (associated) {
                for (Route route : table.routes()) {
                    if (route.gatewayId() != null && route.gatewayId().startsWith("igw-")) {
                        return "PUBLIC";
                    }
                }
            }
        }
        return "PRIVATE";
    }

    public Subnet getSubnetById(String subnetId) {
        DescribeSubnetsRequest request = DescribeSubnetsRequest.builder()
                .subnetIds(subnetId)
                .build();
        DescribeSubnetsResponse response = ec2.describeSubnets(request);
        if (response.subnets().isEmpty()) {
            throw new RuntimeException("Subnet not found: " + subnetId);
        }
        return response.subnets().get(0);
    }

    public void deleteSubnet(String subnetId) {
        DeleteSubnetRequest request = DeleteSubnetRequest.builder()
                .subnetId(subnetId)
                .build();
        ec2.deleteSubnet(request);
    }

}
