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
}
