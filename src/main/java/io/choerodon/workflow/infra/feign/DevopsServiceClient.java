package io.choerodon.workflow.infra.feign;

import io.swagger.annotations.ApiParam;
import org.hzero.core.util.Results;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.choerodon.workflow.infra.feign.fallback.DevopsServiceClientFallBack;

/**
 * Created by Sheep on 2019/4/15.
 */

@FeignClient(value = "devops-service", fallback = DevopsServiceClientFallBack.class)

public interface DevopsServiceClient {

    @GetMapping(value = "/workflow/auto_deploy")
    ResponseEntity autoDeploy(
            @RequestParam(value = "stage_record_id") Long stageRecordId,
            @RequestParam(value = "task_record_id") Long taskRecordId);

    @PutMapping(value = "/workflow/auto_deploy/status")
    ResponseEntity setAutoDeployTaskStatus(
            @RequestParam(value = "pipeline_record_id") Long pipelineRecordId,
            @RequestParam(value = "stage_record_id") Long stageRecordId,
            @RequestParam(value = "task_record_id") Long taskRecordId,
            @RequestParam(value = "status") Boolean status);

    @GetMapping(value = "/workflow/auto_deploy/status")
    ResponseEntity<String> getAutoDeployTaskStatus(
            @RequestParam(value = "stage_record_id") Long stageRecordId,
            @RequestParam(value = "task_record_id") Long taskRecordId);

    @PostMapping(value = "/v1/cd_pipeline/cd_host_deploy")
    ResponseEntity cdHostDeploy(@RequestParam(value = "pipeline_record_id") Long pipelineRecordId,
                                         @RequestParam(value = "stage_record_id") Long stageRecordId,
                                         @RequestParam(value = "job_record_id") Long jobRecordId);

    @PostMapping(value = "/v1/cd_pipeline/env_auto_deploy")
    ResponseEntity envAutoDeploy(@RequestParam(value = "pipeline_record_id") Long pipelineRecordId,
                                          @RequestParam(value = "stage_record_id") Long stageRecordId,
                                          @RequestParam(value = "job_record_id") Long jobRecordId);

    @PutMapping("/v1/cd_pipeline/auto_deploy/status")
    ResponseEntity setAppDeployStatus(
            @RequestParam(value = "pipeline_record_id") Long pipelineRecordId,
            @RequestParam(value = "stage_record_id") Long stageRecordId,
            @RequestParam(value = "job_record_id") Long jobRecordId,
            @RequestParam(value = "status") Boolean status);

    @GetMapping("/v1/cd_pipeline/job/status")
    ResponseEntity<String> getJobStatus(
            @RequestParam(value = "pipeline_record_id") Long pipelineRecordId,
            @RequestParam(value = "stage_record_id") Long stageRecordId,
            @RequestParam(value = "job_record_id") Long jobRecordId);
}
