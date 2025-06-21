package com.jyotibroto.journalApp.cache;

import com.jyotibroto.journalApp.Repository.ConfigJournalAppRepository;
import com.jyotibroto.journalApp.entity.ConfigJournalAppEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

//    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public AppCache(ConfigJournalAppRepository configJournalAppRepository) {
        this.configJournalAppRepository = configJournalAppRepository;
    }

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        System.out.println("✅ AppCache initialized");
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        System.out.println(all.size());
        for(ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
