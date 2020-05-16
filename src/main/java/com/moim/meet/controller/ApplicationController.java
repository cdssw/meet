package com.moim.meet.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moim.meet.service.application.ApplicationDto;
import com.moim.meet.service.application.ApplicationService;

import lombok.AllArgsConstructor;

/**
 * ApplicationController.java
 * 
 * @author cdssw
 * @since May 2, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 2, 2020   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/application")
public class ApplicationController {

	private ApplicationService applicationService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void application(@RequestBody @Valid ApplicationDto.ApplicationReq dto) {
		applicationService.applicationMeet(dto);
	}
	
	@PostMapping(value = "/approval")
	@ResponseStatus(value = HttpStatus.OK)
	public void approval(@RequestBody @Valid ApplicationDto.ApprovalReq dto) {
		applicationService.approval(dto);
	}
	
	@PostMapping(value = "/cancel")
	@ResponseStatus(value = HttpStatus.OK)
	public void cancel(@RequestBody @Valid ApplicationDto.ApprovalReq dto) {
		applicationService.cancel(dto);
	}
}
