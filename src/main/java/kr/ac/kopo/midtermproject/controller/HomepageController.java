package kr.ac.kopo.midtermproject.controller;

import kr.ac.kopo.midtermproject.dto.BoardDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/totoscoop/")
@RequiredArgsConstructor
public class HomepageController {
    @GetMapping("/main_page")
    public void mainPage() {
    }

    @GetMapping("/about_us")
    public void aboutUs() {
    }

    @GetMapping("/shop")
    public void shop() {
    }

    @GetMapping("/contact")
    public void contact() {
    }

    @GetMapping("/registration")
    public void registration() {
    }

    @GetMapping("/login")
    public void login() {
    }

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        Long bno = boardService.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/totoscoop/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
        BoardDTO boardDTO = boardService.get(bno);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/totoscoop/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/totoscoop/read";
    }
}
