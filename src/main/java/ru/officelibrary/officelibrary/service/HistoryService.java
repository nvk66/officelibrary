package ru.officelibrary.officelibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.officelibrary.officelibrary.entity.History;
import ru.officelibrary.officelibrary.repository.HistoryRepository;

import java.util.List;

@Service
@Transactional
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History addHistory(History history){
        return historyRepository.save(history);
    }

    public History getById(long id){
        return historyRepository.findById(id).get();
    }

    public List<History> getAll(){
        return (List<History>) historyRepository.findAll();
    }

    public List<History> findAllByStatsEqualsBusy(){
        return historyRepository.findAllByStatsEquals("Busy");
    }
}
