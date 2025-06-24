//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.services;

import edu.elon.amozloom.Project1_Mozloom.models.CreateInstanceRequestDTO;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;

@Service
public class AWSInstanceService {

    private final Ec2Client ec2;

    public AWSInstanceService(Ec2Client ec2) {
        this.ec2 = ec2;
    }

    public List<Instance> getAllInstances() {
        DescribeInstancesResponse response = ec2.describeInstances();
        return response.reservations().stream()
                .flatMap(reservation -> reservation.instances().stream())
                .toList();
    }

    public Instance getInstanceById(String instanceId) {
        DescribeInstancesRequest request = DescribeInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        DescribeInstancesResponse response = ec2.describeInstances(request);
        if (response.reservations().isEmpty() || response.reservations().get(0).instances().isEmpty()) {
            throw new RuntimeException("Instance not found: " + instanceId);
        }
        return response.reservations().get(0).instances().get(0);
    }

    public Instance createInstance(CreateInstanceRequestDTO dto) {
        RunInstancesRequest runRequest = RunInstancesRequest.builder()
                .imageId(dto.getAmiId())
                .instanceType(dto.getInstanceType())
                .subnetId(dto.getSubnetId())
                .maxCount(dto.getInstanceCount())
                .minCount(1)
                .build();
        RunInstancesResponse response = ec2.runInstances(runRequest);
        Instance newInstance = response.instances().get(0);
        String instanceId = newInstance.instanceId();
        ec2.waiter().waitUntilInstanceRunning(r -> r.instanceIds(instanceId));
        return ec2.describeInstances(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        ).reservations().get(0).instances().get(0);
    }

    public Instance startInstance(String instanceId) {
        StartInstancesRequest request = StartInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        ec2.startInstances(request);
        ec2.waiter().waitUntilInstanceRunning(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        );
        return ec2.describeInstances(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        ).reservations().get(0).instances().get(0);
    }

    public Instance stopInstance(String instanceId) {
        StopInstancesRequest request = StopInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        ec2.stopInstances(request);
        ec2.waiter().waitUntilInstanceStopped(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        );
        return ec2.describeInstances(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        ).reservations().get(0).instances().get(0);
    }

    public Instance terminateInstance(String instanceId) {
        TerminateInstancesRequest request = TerminateInstancesRequest.builder()
                .instanceIds(instanceId)
                .build();
        ec2.terminateInstances(request);
        ec2.waiter().waitUntilInstanceTerminated(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        );
        return ec2.describeInstances(
                DescribeInstancesRequest.builder().instanceIds(instanceId).build()
        ).reservations().get(0).instances().get(0);
    }

}
