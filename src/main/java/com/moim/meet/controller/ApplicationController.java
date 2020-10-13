package com.moim.meet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 지원자 목록
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ApplicationDto.ApplicationUserRes> getUserApplicationMeet(@PathVariable final long id) {
		return applicationService.applicationUser(id);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void application(@RequestBody @Valid ApplicationDto.ApplicationReq dto, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		applicationService.applicationMeet(dto, username);
	}
	
	@PostMapping(value = "/approval")
	@ResponseStatus(value = HttpStatus.OK)
	public void approval(@RequestBody @Valid ApplicationDto.ApprovalReq dto, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		applicationService.approval(dto, username);
	}
	
	@PostMapping(value = "/cancel")
	@ResponseStatus(value = HttpStatus.OK)
	public void cancel(@RequestBody @Valid ApplicationDto.ApprovalReq dto, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		applicationService.cancel(dto, username);
	}
}
