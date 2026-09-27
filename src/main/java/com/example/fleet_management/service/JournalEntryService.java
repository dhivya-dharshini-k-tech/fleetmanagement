package com.example.fleet_management.service;

import com.example.fleet_management.entity.JournalEntry;
import com.example.fleet_management.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public JournalEntry createJournalEntry(JournalEntry entry) {

        if (entry.getEntryDate() == null) {
            entry.setEntryDate(LocalDateTime.now());
        }

        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(Long id) {
        return journalEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found"));
    }
}