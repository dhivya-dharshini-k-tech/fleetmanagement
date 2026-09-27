package com.example.fleet_management.controller;

import com.example.fleet_management.entity.JournalEntry;
import com.example.fleet_management.service.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet/journal-entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @PostMapping
    public JournalEntry createJournalEntry(
            @RequestBody JournalEntry entry) {

        return journalEntryService.createJournalEntry(entry);
    }

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntry(@PathVariable Long id) {
        return journalEntryService.getJournalEntryById(id);
    }
}