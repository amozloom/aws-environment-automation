//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.controllers;

import edu.elon.amozloom.Project1_Mozloom.models.CreateSubnetRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.CreateVpcRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.SubnetDTO;
import edu.elon.amozloom.Project1_Mozloom.models.VpcDTO;
import edu.elon.amozloom.Project1_Mozloom.services.VpcDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ec2automation/network")
public class NetworkController {

    @Autowired
    private VpcDTOService dtoService;

    @GetMapping("/list")
    public List<VpcDTO> getAllVpcs() {
        return dtoService.getAllVpcs();
    }

    @GetMapping("/{vpcId}")
    public VpcDTO getVpcById(@PathVariable String vpcId) {
        return dtoService.getVpcById(vpcId);
    }

    @PostMapping
    public VpcDTO createVpc(@RequestBody CreateVpcRequestDTO requestDTO) {
        return dtoService.createVpc(requestDTO);
    }

    @PostMapping("/{vpcId}/subnet")
    public SubnetDTO createSubnet(
            @PathVariable String vpcId,
            @RequestBody CreateSubnetRequestDTO dto
    ) {
        return dtoService.createSubnet(vpcId, dto);
    }

    @GetMapping("/{vpcId}/subnet/list")
    public List<SubnetDTO> getSubnetsForVpc(@PathVariable String vpcId) {
        return dtoService.getSubnetsForVpc(vpcId);
    }

    @GetMapping("/{vpcId}/subnet/{subnetId}")
    public SubnetDTO getSubnetById(@PathVariable String vpcId, @PathVariable String subnetId) {
        return dtoService.getSubnetById(subnetId);
    }

    @DeleteMapping("/{vpcId}/subnet/{subnetId}")
    public void deleteSubnet(@PathVariable String vpcId, @PathVariable String subnetId) {
        dtoService.deleteSubnet(subnetId);
    }

}
