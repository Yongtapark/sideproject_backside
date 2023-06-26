package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.OwnerProfileInfo;
import com.backend.fitta.dto.ownermypage.*;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.exception.AlreadyExistOwnerException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.file.FilePath;
import com.backend.fitta.repository.owner.OwnerQueryRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OwnerApiServiceImpl implements OwnerApiService {
    private final OwnerRepository ownerRepository;
    private final OwnerQueryRepository ownerQueryRepository;
    @Override
    public Long save(SignUpOwnerRequest request) {

        Optional<Owner> findOwner = ownerRepository.findByEmail(request.getEmail());
        if (!findOwner.isEmpty()) { //중복 체크
            throw new AlreadyExistOwnerException();
        }
      /*  if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new PWNotCorrespondException();
        }*/
        log.info("request.getEmail()={}",request.getEmail());
        Owner owner = new Owner(request.getEmail(), request.getPassword(), request.getName(), request.getPhoneNumber(), request.getAddress(), request.getBusinessRegistrationNumber());
        ownerRepository.save(owner);
        return owner.getId();

    }

    @Override
    public OwnerProfileInfo findProfileById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        return new OwnerProfileInfo(owner);
    }

    @Override
    public BasicOwnerInfo findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        return new BasicOwnerInfo(owner);
    }

    @Override
    public Owner findByID(Long id) {
       return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
    }

    @Override
    public Result<List<BasicOwnerInfo>> findAll() {
        List<Owner> all = ownerRepository.findAll();
        List<BasicOwnerInfo> collect = all.stream()
                .map(o -> new BasicOwnerInfo(o))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Long update(Long id, UpdateOwnerRequest request, MultipartFile profileImage) throws IOException {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        String storeFileName = null;
        if(profileImage!=null){
            storeFileName = createStoreFileName(profileImage.getOriginalFilename());
            profileImage.transferTo(new File(FilePath.filePath + storeFileName));
        }
        owner.changeOwnerInfo(new Owner(request.getName(), storeFileName, request.getPassword(), request.getPhoneNumber(), request.getAddress(), request.getBusinessRegistrationNumber()));
        return id;
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException());
        ownerRepository.delete(owner);
    }

    /**
     * 오너 마이페이지
     */
    /*체육관, 직원, 회원 수 반환*/
    @Override
    public AllGymCount ownerAllGymInfo(Long ownerId) {
       return ownerQueryRepository.ownerAllGymInfoResponse(ownerId);
    }

    /*오늘 가입한 회원 수 반환*/
    @Override
    public MemberTodayRate calculateSignupToday(Long ownerId) {
        return ownerQueryRepository.calculateSignupToday(ownerId);
    }

    /*남녀 수, 비율 반환*/
    @Override
    public MemberRate genderLate(Long ownerId) {
        return ownerQueryRepository.calculateGenderRate(ownerId);
    }
    /*체육관,회원,직원, 성비 표시*/
    @Override
    public OwnerAllView ownerAllView(Long ownerId) {
        return ownerQueryRepository.ownerAllView(ownerId);
    }

    @Override
    public MemberAgeRate memberAgeRate(Long ownerId) {
        return ownerQueryRepository.calculateAgeRate(ownerId);
    }


    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
