//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.controllers;

import edu.elon.amozloom.Project1_Mozloom.models.CreateEnvironmentRequestDTO;
import edu.elon.amozloom.Project1_Mozloom.models.EnvironmentDTO;
import edu.elon.amozloom.Project1_Mozloom.services.AWSOrchestrationService;
import edu.elon.amozloom.Project1_Mozloom.services.EnvironmentDTOService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ec2automation/environment")
public class EnvironmentController {

    private final EnvironmentDTOService envService;
    private final AWSOrchestrationService awsOrchestrationService;

    public EnvironmentController(EnvironmentDTOService envService, AWSOrchestrationService awsOrchestrationService) {
        this.envService = envService;
        this.awsOrchestrationService = awsOrchestrationService;
    }

    @GetMapping
    public EnvironmentDTO getEnvironment() {
        return envService.getEnvironment();
    }

    @PostMapping
    public EnvironmentDTO createEnvironment(@RequestBody CreateEnvironmentRequestDTO request) {
        return awsOrchestrationService.createEnvironment(request);
    }

}
