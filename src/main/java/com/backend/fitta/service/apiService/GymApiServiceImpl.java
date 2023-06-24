package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.GymProfileInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.image.Image;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.file.FilePath;
import com.backend.fitta.repository.gym.GymQueryRepository;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.gym.GymSearchCond;
import com.backend.fitta.repository.image.ImageRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GymApiServiceImpl implements GymApiService {
    private final GymRepository gymRepository;
    private final GymQueryRepository gymQueryRepository;
    private final OwnerRepository ownerRepository;
    private final OwnerService ownerService;

    private final ImageRepository imageRepository;

    @Override
    public Long save(SaveGymRequest request,List<MultipartFile> images) throws IOException {
        Owner owner = ownerService.findById(request.getOwnerId());
        Gym gym = new Gym(request.getName(), owner, request.getPhoneNumber(), request.getAddress(), request.getGenderDivision(), request.getBusinessIdentificationNumber());
        saveImages(images, gym);
        return gymRepository.save(gym).getId();
    }

    @Override
    public BasicGymInfo findById(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        return new BasicGymInfo(gym);
    }

    /*@Override
    public Result<List<BasicGymInfo>> findAll() {
        List<Gym> all = gymRepository.findAll();
        List<BasicGymInfo> collect = all.stream()
                .map(G -> new BasicGymInfo(G))
                .collect(Collectors.toList());
        return new Result(collect);
    }*/

    @Override
    public Long update(Long id, UpdateGymRequest request, List<MultipartFile> images) throws IOException {
        Gym findGym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        findGym.changeGymInfo(request.getName(),request.getPhoneNumber(),request.getAddress(),request.getGenderDivision());
        saveImages(images, findGym);
        return findGym.getId();
    }

    @Override
    public void delete(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException());
        gymRepository.delete(gym);
    }

    @Override
    public void saveOwnerGym(long gymId, long ownerId) {
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException());
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException());
        gym.changeOwner(owner);
    }

    @Override
    public Page<GymProfileInfo> findAll(Pageable pageable) {
        Page<Gym> all = gymRepository.findAll(pageable);
        List<GymProfileInfo> gymInfoList = all.stream().map(g -> new GymProfileInfo(g)).collect(Collectors.toList());
        return new PageImpl<>(gymInfoList,pageable,all.getTotalElements());
    }


    @Override
    public Page<GymProfileInfo> findSearch(GymSearchCond cond, Pageable pageable) {
        Page<Gym> all = gymQueryRepository.findAll(cond,pageable);
        List<GymProfileInfo> gymInfoList = all.stream().map(g -> new GymProfileInfo(g)).collect(Collectors.toList());
        return new PageImpl<>(gymInfoList,pageable,all.getTotalElements());
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

    private void saveImages(List<MultipartFile> images, Gym findGym) throws IOException {
        for (MultipartFile multipartFile : images) {
            // 이미지 저장
            String originalFileName = multipartFile.getOriginalFilename();
            String storeFileName = createStoreFileName(originalFileName);
            multipartFile.transferTo(new File(FilePath.filePath + storeFileName));
            Image image = new Image(originalFileName, storeFileName, findGym);
            imageRepository.save(image);
        }
    }

}
