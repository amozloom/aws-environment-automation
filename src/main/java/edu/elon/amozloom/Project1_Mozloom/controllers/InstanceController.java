//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.controllers;

import edu.elon.amozloom.Project1_Mozloom.models.CreateInstanceRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.InstanceDTO;
import edu.elon.amozloom.Project1_Mozloom.services.InstanceDTOService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ec2automation/instance")
public class InstanceController {

    private final InstanceDTOService dtoService;

    public InstanceController(InstanceDTOService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping("/list")
    public List<InstanceDTO> getAllInstances() {
        return dtoService.getAllInstances();
    }

    @GetMapping("/{instanceId}")
    public InstanceDTO getInstanceById(@PathVariable String instanceId) {
        return dtoService.getInstanceById(instanceId);
    }

    @PostMapping
    public InstanceDTO createInstance(@RequestBody CreateInstanceRequestDTO dto) {
        return dtoService.createInstance(dto);
    }

    @PostMapping("/{instanceId}/start")
    public InstanceDTO startInstance(@PathVariable String instanceId) {
        return dtoService.startInstance(instanceId);
    }

    @PostMapping("/{instanceId}/stop")
    public InstanceDTO stopInstance(@PathVariable String instanceId) {
        return dtoService.stopInstance(instanceId);
    }

    @PostMapping("/{instanceId}/terminate")
    public InstanceDTO terminateInstance(@PathVariable String instanceId) {
        return dtoService.terminateInstance(instanceId);
    }

}
