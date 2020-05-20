package com.moim.meet.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moim.meet.service.meet.MeetDto;
import com.moim.meet.service.meet.MeetService;

import lombok.AllArgsConstructor;

/**
 * MeetController.java
 * 
 * @author cdssw
 * @since Apr 6, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 6, 2020    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/meet")
public class MeetController {

	private MeetService meetService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public long createMeet(@RequestBody @Valid MeetDto.MeetReq dto) {
		return meetService.createMeet(dto);
	}
	
//	@GetMapping
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<MeetDto.Res> getMeetList() {
//		return meetService.getMeetList();
//	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MeetDto.Res> getMeetListByPage(Pageable pageable) {
		return meetService.getMeetListByPage(pageable);
	}
	
	@PostMapping("/search")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<MeetDto.Res> search(@RequestBody @Valid final MeetDto.SearchReq dto, Pageable pageable) {
		return meetService.search(dto, pageable);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public MeetDto.Res editMeet(@PathVariable final long id, @RequestBody @Valid final MeetDto.MeetReq dto) {
		return meetService.editMeet(id, dto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public MeetDto.Res getMeet(@PathVariable final long id) {
		return meetService.getMeet(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMeet(@PathVariable final long id) {
		meetService.deleteMeet(id);
	}
}
