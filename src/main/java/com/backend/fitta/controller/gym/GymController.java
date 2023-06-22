package com.backend.fitta.controller.gym;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.GymProfileInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.repository.gym.GymSearchCond;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import io.lettuce.core.output.ScanOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "헬스장", description = "헬스장 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gyms")
public class GymController {
    private final GymApiService gymApiService;

    @Operation(summary = "헬스장 추가 메서드", description = "헬스장 추가 메서드입니다.")
    @PostMapping
    public ResponseEntity<Long> saveGym(@Valid @RequestBody SaveGymRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gymApiService.save(request));
    }
    @Operation(summary = "헬스장 조회 메서드", description = "헬스장 id로 헬스장 정보를 조회 할 수 있습니다.")
    @GetMapping("/{gymId}")
    public ResponseEntity<BasicGymInfo> findGym(@PathVariable long gymId) {
        return ResponseEntity.ok(gymApiService.findById(gymId));
    }

    /*@Operation(summary = "전체 헬스장 조회 메서드", description = "전체 헬스장 정보를 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Result<List<BasicGymInfo>>> findAll() {
        return ResponseEntity.ok(gymApiService.findAll());
    }*/

    @Operation(summary = "헬스장 정보 수정 메서드", description = "헬스장 id로 헬스장 정보를 찾아 헬스장 정보를 수정 할 수 있습니다.")
    @PutMapping("/{gymId}")
    public ResponseEntity<Long> updateGym(@PathVariable Long gymId, @Valid @RequestBody UpdateGymRequest request) {
        return ResponseEntity.ok(gymApiService.update(gymId, request));
    }

    @Operation(summary = "헬스장 삭제 메서드", description = "헬스장 id로 헬스장을 삭제할 수 있습니다.")
    @DeleteMapping("/{gymId}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long gymId) {
        gymApiService.delete(gymId);
        return ResponseEntity.noContent().build();
    }

    /*@Operation(summary = "헬스장 오너 등록", description = "헬스장 id로 헬스장을 찾아 오너를 추가해줍니다.")
    @PostMapping("/owner/{gymId}/{ownerId}")
    public ResponseEntity<Void> saveOwnerGym(@PathVariable long gymId, @PathVariable long ownerId) {
        gymApiService.saveOwnerGym(gymId, ownerId);
        return ResponseEntity.noContent().build();
    }*/
    @Operation(summary = "전체 헬스장 조회 메서드", description = "전체 헬스장 정보를 조회 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Page<GymProfileInfo>> gymPaging(@RequestBody Optional<GymSearchCond> cond, @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GymProfileInfo> gymInfoPage;
        if (cond.isEmpty()) {
            gymInfoPage = gymApiService.findAll(pageable);
        } else {
            gymInfoPage = gymApiService.findSearch(cond.orElse(null), pageable);
            System.out.println(cond.get().getGymName());
        }
        return ResponseEntity.ok(gymInfoPage);
    }


}