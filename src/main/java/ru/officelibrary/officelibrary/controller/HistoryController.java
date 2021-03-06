package ru.officelibrary.officelibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.officelibrary.officelibrary.common.BookStatus;
import ru.officelibrary.officelibrary.entity.History;
import ru.officelibrary.officelibrary.service.HistoryService;

import java.util.List;

@Controller
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @RequestMapping("history")
    public ModelAndView historyHome() {
        List<History> listHistory = historyService.getAll();
        ModelAndView mav = new ModelAndView("historyPage");
        mav.addObject("listHistory", listHistory);
        return mav;
    }

    @GetMapping(value = "history/new")
    public ModelAndView newReservation(ModelAndView model) {
        History history = new History();
        model.addObject("history", history);
        model.setViewName("from_history");
        return model;
    }

    @GetMapping(value = "history/return/{id}")
    public ModelAndView returnBook(@PathVariable String id) {
        History history = historyService.getById(Long.parseLong(id));
        history.setStats(String.valueOf(BookStatus.FREE));
        history.setReturnDate(historyService.getCurrentDate());
        historyService.addHistory(history);
        return historyHome();
    }

}
