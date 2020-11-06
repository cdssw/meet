package com.moim.meet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moim.meet.service.mypage.MyPageDto;
import com.moim.meet.service.mypage.MyPageService;

import lombok.AllArgsConstructor;

/**
 * MyPageController.java
 * 
 * @author cdssw
 * @since May 7, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 7, 2020   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/mypage")
public class MyPageController {

	private MyPageService myPageService;
	
	@PostMapping("/opened")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MyPageDto.OpenedRes> opened(@RequestBody @Valid final MyPageDto.OpenedReq dto, Pageable pageable, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		return myPageService.opened(username, dto, pageable);
	}

	@PostMapping("/application")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MyPageDto.ApplicationRes> application(@RequestBody @Valid final MyPageDto.ApplicationReq dto, Pageable pageable, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		return myPageService.chatAndApplication(username, dto, pageable);
	}
}
