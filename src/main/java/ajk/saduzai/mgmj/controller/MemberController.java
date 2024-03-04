package ajk.saduzai.mgmj.controller;

import ajk.saduzai.mgmj.domain.Member;
import ajk.saduzai.mgmj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(
            path = "/getMessage"
    )
    public String test() {
        System.out.println("This is a controller.");
        return "Success";
    }

    @GetMapping("/getMembers")
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/findMember/{id}")
    public Member findMemberById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

    @PostMapping(
            path = "/createMember",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Member createMember(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PutMapping("/updateMember/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member currentMember = memberService.findMemberById(id);
        currentMember.setName(member.getName());
        return memberService.save(currentMember);
    }

    @DeleteMapping("/deleteMember/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
