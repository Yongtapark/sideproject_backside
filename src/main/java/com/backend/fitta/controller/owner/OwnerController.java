package com.backend.fitta.controller.owner;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.OwnerProfileInfo;
import com.backend.fitta.dto.ownermypage.OwnerAllView;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Tag(name = "오너", description = "오너 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerApiService ownerApiService;
    //
    /*@Operation(summary = "테스트 userdata")
    @GetMapping("/testuserdata")
    public ResponseEntity<OwnerProfileInfo> getTestMemberInfo(){
        OwnerProfileInfo byId1 = ownerApiService.findProfileById(1L);
        return ResponseEntity.ok(byId1);
    }*/


    @Operation(summary = "오너 등록 메서드", description = "오너 등록 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveOwner(@Valid @RequestBody SignUpOwnerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerApiService.save(request));
    }

    @Operation(summary = "오너 조회 메서드", description = "오너 id로 오너를 조회 할 수 있습니다.")
    @GetMapping("/{ownerId}")
    public ResponseEntity<BasicOwnerInfo> findMember(@PathVariable Long ownerId) {
        return ResponseEntity.ok(ownerApiService.findById(ownerId));
    }

    @Operation(summary = "전체 오너 조회 메서드", description = "전체 오너를 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicOwnerInfo>>> findAll() {
        return ResponseEntity.ok(ownerApiService.findAll());
    }

    @Operation(summary = "오너 정보 수정 메서드", description = "오너의 정보를 수정 할 수 있습니다.")
    @PutMapping("/{ownerId}")
    public ResponseEntity<Long> updateOwner(@PathVariable Long ownerId, @Valid @RequestPart UpdateOwnerRequest request,
                                            @RequestPart(value = "multipartFile", required = false) Optional<MultipartFile> multipartFile) throws IOException {
        return ResponseEntity.ok(ownerApiService.update(ownerId, request, multipartFile.orElse(null)));
    }

    @Operation(summary = "오너 삭제 메서드", description = "오너를 삭제할 수 있습니다.")
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long ownerId) {
        ownerApiService.deleteOwner(ownerId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 마이페이지 오너
     */
    /*@Operation(summary = "전체 체육관, 직원, 회원 수량 반환")
    @GetMapping("{ownerId}/total-count")
    public ResponseEntity<AllGymCount> ownerAllGymInfo(@PathVariable Long ownerId){
       return ResponseEntity.ok(ownerApiService.ownerAllGymInfo(ownerId));
    }

    @Operation(summary = "전체 오늘 가입한 회원 수 반환")
    @GetMapping("/{ownerId}/today-rate")
    public ResponseEntity<TodayMemberRate> memberTodaySignup(@PathVariable Long ownerId){
        return ResponseEntity.ok(ownerApiService.calculateSignupToday(ownerId));
    }

    @Operation(summary = "전체 남녀 수, 비율 반환")
    @GetMapping("/{ownerId}/gender-rate")
    public ResponseEntity<MemberRate> memberGenderLate(@PathVariable Long ownerId){
        return ResponseEntity.ok(ownerApiService.genderLate(ownerId));
    }*/
    @Operation(summary = "체육관, 회원, 직원, 성비, 나이대 표시" , description = "memberRate=총 인원 비율, todayMemberRate = 금일 가입 인원 비율, AllGymCount = 체육관,직원,회원 수, memberAgeRate = 총 나이 비율")
    @GetMapping("/{ownerId}/all-view")
    public ResponseEntity<OwnerAllView> ownerAllView(@PathVariable Long ownerId){
        return ResponseEntity.ok(ownerApiService.ownerAllView(ownerId));
    }

}
