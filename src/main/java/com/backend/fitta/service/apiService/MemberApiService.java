package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.member.BasicMemberInfo;
import com.backend.fitta.dto.member.MemberProfileInfo;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.image.Image;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.exception.*;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.image.ImageRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberApiService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final GymRepository gymRepository;
    private final ImageRepository imageRepository;

    public Long save(SignUpRequest rq) {
        Optional<Member> findMember = memberRepository.findByEmail(rq.getEmail());
        if (!findMember.isEmpty()) { //중복 체크
            throw new AlreadyExistMemberException();
        }
       /* if (!rq.getPassword().equals(rq.getPasswordConfirm())) {
            throw new PWNotCorrespondException();
        }*/
        Member member = new Member(rq.getEmail(), rq.getPassword(), rq.getName(), rq.getBirthdate(), rq.getPhoneNumber(), rq.getAddress()
                , rq.getGender(), null, null, rq.getOccupation(), null, null, null);
        memberRepository.save(member);
        return member.getId();
    }


    public BasicMemberInfo findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return new BasicMemberInfo(member);
    }

    public MemberProfileInfo findProfileMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return new MemberProfileInfo(member);
    }

    public Result<List<BasicMemberInfo>> findAll() {
        List<Member> all = memberRepository.findAll();
        List<BasicMemberInfo> collect = all.stream()
                .map(m -> new BasicMemberInfo(m))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    public Long update(Long memberId, UpdateMemberRequest rq, MultipartFile multipartFile) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        String storeFileName = createStoreFileName(multipartFile.getOriginalFilename());
        Image image = new Image(multipartFile.getOriginalFilename(),storeFileName, member);
        member.changeMemberInfo(rq.getEmail(), rq.getPassword(),rq.getName(), rq.getBirthdate(), rq.getPhoneNumber(), rq.getAddress(), rq.getHeight(), rq.getWeight(), rq.getOccupation(), rq.getNote(),image);

        imageRepository.save(image);
        return member.getId();
    }


    public void saveTeamMember(long memberId, long teamId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException());
        findMember.changeTeam(team);
    }


    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void saveGymMember(long memberId, long gymId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException());
        findMember.changeGym(gym);
    }

    /**
     * 임시 추가분, 추후 정리 필요
     */
    /*로그인 후 */
    public BasicMemberInfo findByEmail(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException());
        BasicMemberInfo basicMemberInfo = new BasicMemberInfo(member);
        return basicMemberInfo;
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
